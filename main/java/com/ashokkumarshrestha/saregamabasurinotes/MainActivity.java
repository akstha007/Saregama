package com.ashokkumarshrestha.saregamabasurinotes;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private Button btnHindi, btnEnglish, btnNepali, btnDevotional, btnPractice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        btnHindi = (Button) findViewById(R.id.btnHindi);
        btnEnglish = (Button) findViewById(R.id.btnEnglish);
        btnNepali = (Button) findViewById(R.id.btnNepali);
        btnDevotional = (Button) findViewById(R.id.btnDevotional);
        btnPractice = (Button) findViewById(R.id.btnPractice);

        btnHindi.setOnClickListener(this);
        btnEnglish.setOnClickListener(this);
        btnNepali.setOnClickListener(this);
        btnDevotional.setOnClickListener(this);
        btnPractice.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
            doExit();
        }
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/

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

        } else if (id == R.id.nav_hindi) {
            Intent intent=new Intent(MainActivity.this,HindiActivity.class);
            String str = "0";
            intent.putExtra("Message",str);
            startActivity(intent);
        } else if (id == R.id.nav_english) {
            Intent intent=new Intent(MainActivity.this,HindiActivity.class);
            String str = "1";
            intent.putExtra("Message",str);
            startActivity(intent);
        } else if (id == R.id.nav_nepali) {
            Intent intent=new Intent(MainActivity.this,HindiActivity.class);
            String str = "2";
            intent.putExtra("Message",str);
            startActivity(intent);
        } else if (id == R.id.nav_devotional) {
            Intent intent=new Intent(MainActivity.this,HindiActivity.class);
            String str = "3";
            intent.putExtra("Message",str);
            startActivity(intent);
        }else if (id == R.id.nav_practice) {
            Intent intent=new Intent(MainActivity.this,HindiActivity.class);
            String str = "4";
            intent.putExtra("Message",str);
            startActivity(intent);
        } else if (id == R.id.nav_instruction) {
            startActivity(new Intent(MainActivity.this, InstructionsActivity.class));
        } else if (id == R.id.nav_share) {
            startActivity(createShareIntent());
        } else if (id == R.id.nav_rate) {
            rateApp();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnHindi:
                //finish();
                //startActivity(new Intent(MainActivity.this, HindiActivity.class));
                Intent intent=new Intent(view.getContext(),HindiActivity.class);
                String str = "0";
                intent.putExtra("Message",str);
                startActivity(intent);
                break;
            case R.id.btnEnglish:
                intent=new Intent(view.getContext(),HindiActivity.class);
                str = "1";
                intent.putExtra("Message",str);
                startActivity(intent);
                break;
            case R.id.btnNepali:
                intent=new Intent(view.getContext(),HindiActivity.class);
                str = "2";
                intent.putExtra("Message",str);
                startActivity(intent);
                break;
            case R.id.btnDevotional:
                intent=new Intent(view.getContext(),HindiActivity.class);
                str = "3";
                intent.putExtra("Message",str);
                startActivity(intent);
                break;
            case R.id.btnPractice:
                intent=new Intent(view.getContext(),HindiActivity.class);
                str = "4";
                intent.putExtra("Message",str);
                startActivity(intent);
                break;
        }
    }

    private Intent createShareIntent() {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_SUBJECT, "Saregama Bansuri Notes");
        i.putExtra(Intent.EXTRA_TEXT, "\n\n" + "https://play.google.com/store/apps/details?id=com.ashokkumarshrestha.saregamabasurinotes");

        return i;
    }

    private void doExit() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                MainActivity.this);

        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        alertDialog.setNegativeButton("No", null);

        alertDialog.setMessage("Do you want to exit?");
        alertDialog.setTitle("Sa Re Ga Ma");
        alertDialog.show();
    }

    public void rateApp(){
        Uri uri = Uri.parse("market://details?id=" + this.getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + this.getPackageName())));
        }
    }
}
