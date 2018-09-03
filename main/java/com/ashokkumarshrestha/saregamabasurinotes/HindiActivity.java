package com.ashokkumarshrestha.saregamabasurinotes;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HindiActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private List<Song> songList = new ArrayList<>();
    private List<String> list = new ArrayList<>();
    private RecyclerView recyclerView;
    private SongAdapter mAdapter;
    private int fileIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hindi);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String page = getIntent().getStringExtra("Message");
        String toolbarName = setUpPage(page);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(toolbarName);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new SongAdapter(songList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        saveData();
        mAdapter.notifyDataSetChanged();
    }

    private String setUpPage(String page) {

        int[] arrFile = {R.raw.hindi,R.raw.english,R.raw.nepali,R.raw.devotional, R.raw.practice};
        String[] arrActivity = {"Hindi","English", "Nepali", "Devotional", "Practice"};

        int index = Integer.parseInt(page);
        if(index>4||index<0)    index = 0;
        fileIndex = arrFile[index];

        return arrActivity[index];
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        final SearchView.SearchAutoComplete searchAutoComplete = (SearchView.SearchAutoComplete) mSearchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchAutoComplete.setTextColor(Color.WHITE);

        int autoCompleteTextViewID = R.id.search_src_text;
        final AutoCompleteTextView searchAutoCompleteTextView = (AutoCompleteTextView) mSearchView.findViewById(autoCompleteTextViewID);
        searchAutoCompleteTextView.setThreshold(1);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, list);
        searchAutoComplete.setAdapter(adapter);

        SearchManager searchManager =
                (SearchManager) getSystemService(this.SEARCH_SERVICE);
        mSearchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(searchAutoComplete.getWindowToken(), 0);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return true;
            }
        });
        searchAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                // TODO Auto-generated method stub

                String searchString = (String) parent.getItemAtPosition(position);
                //Toast.makeText(HindiActivity.this,"search string : " + searchString,Toast.LENGTH_SHORT).show();

                searchAutoComplete.setText("" + searchString);
                searchAutoCompleteTextView.setSelection(searchString.length());

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(searchAutoComplete.getWindowToken(), 0);

                Intent intent=new Intent(view.getContext(),NotesActivity.class);
                Song song = new Song();
                int index = list.indexOf(searchString);
                song = songList.get(index);
                String str = song.getTitle()+"#"+song.getDesc()+"#"+song.getLyrics();
                intent.putExtra("Message",str);
                view.getContext().startActivity(intent);

            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            finish();
            startActivity(new Intent(HindiActivity.this, MainActivity.class));
        } else if (id == R.id.nav_hindi) {
            if(fileIndex != R.raw.hindi) {
                finish();
                Intent intent = new Intent(HindiActivity.this, HindiActivity.class);
                String str = "0";
                intent.putExtra("Message", str);
                startActivity(intent);
            }
        } else if (id == R.id.nav_english) {
            if(fileIndex != R.raw.english) {
                finish();
                Intent intent = new Intent(HindiActivity.this, HindiActivity.class);
                String str = "1";
                intent.putExtra("Message", str);
                startActivity(intent);
            }
        } else if (id == R.id.nav_nepali) {
            if(fileIndex != R.raw.nepali) {
                finish();
                Intent intent = new Intent(HindiActivity.this, HindiActivity.class);
                String str = "2";
                intent.putExtra("Message", str);
                startActivity(intent);
            }
        } else if (id == R.id.nav_devotional) {
            if(fileIndex != R.raw.devotional) {
                finish();
                Intent intent = new Intent(HindiActivity.this, HindiActivity.class);
                String str = "3";
                intent.putExtra("Message", str);
                startActivity(intent);
            }
        }else if (id == R.id.nav_practice) {
            if(fileIndex != R.raw.practice) {
                finish();
                Intent intent = new Intent(HindiActivity.this, HindiActivity.class);
                String str = "4";
                intent.putExtra("Message", str);
                startActivity(intent);
            }
        } else if (id == R.id.nav_instruction) {
            startActivity(new Intent(HindiActivity.this, InstructionsActivity.class));
        } else if (id == R.id.nav_share) {
            startActivity(createShareIntent());
        } else if (id == R.id.nav_rate) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public String readJSONFile() {
        InputStream inputStream = getResources().openRawResource(fileIndex);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int ctr;
        try {
            ctr = inputStream.read();
            while (ctr != -1) {
                byteArrayOutputStream.write(ctr);
                ctr = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toString();
    }

    public void saveData() {
        String jsonData = readJSONFile();
        int count = 1;
        try {
            // Parse the data into jsonobject to get original data in form of json.
            JSONObject jObject = new JSONObject(jsonData);
            Iterator iterator = jObject.keys();
            while (iterator.hasNext()) {
                String alphabet = iterator.next().toString();
                JSONArray jsonSongNames = jObject.getJSONArray(alphabet);

                for (int i = 0; i < jsonSongNames.length(); i++) {
                    Song song = new Song();

                    song.setTitle(jsonSongNames.getJSONObject(i).getString("title"));
                    song.setDesc(jsonSongNames.getJSONObject(i).getString("desc"));
                    song.setLyrics(jsonSongNames.getJSONObject(i).getString("song"));

                    songList.add(song);
                    list.add(jsonSongNames.getJSONObject(i).getString("title"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Intent createShareIntent() {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_SUBJECT, "Saregama Bansuri Notes");
        i.putExtra(Intent.EXTRA_TEXT, "\n\n" + "https://play.google.com/store/apps/details?id=com.ashokkumarshrestha.saregamabasurinotes");

        return i;
    }
}
