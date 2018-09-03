package com.ashokkumarshrestha.saregamabasurinotes;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class NotesActivity extends AppCompatActivity {


    private TextView txtTitle, txtDesc, txtLyrics;
    private String lyrics, lyricsTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtTitle = (TextView)findViewById(R.id.txtTitle);
        txtDesc = (TextView)findViewById(R.id.txtDesc);
        txtLyrics = (TextView)findViewById(R.id.txtLyrics);

        String lyric = getIntent().getStringExtra("Message");
        //Toast.makeText(NotesActivity.this,lyric,Toast.LENGTH_SHORT).show();

        String[] str = lyric.split("#");

        if(str.length==3){
            txtTitle.setText(str[0]);
            txtDesc.setText(str[1]);
            txtLyrics.setText(str[2]);
            //Toast.makeText(NotesActivity.this,str[1],Toast.LENGTH_SHORT).show();

            String desc = str[1].trim();
            if(desc== null || desc == "null" || desc.length()<5){
                txtDesc.setVisibility(View.GONE);
                TextView txt = (TextView)findViewById(R.id.labelDesc);
                txt.setVisibility(View.GONE);
                //Toast.makeText(NotesActivity.this,lyric,Toast.LENGTH_SHORT).show();
            }

            lyrics = str[2];
            lyricsTitle = str[0];
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.action_share:
                startActivity(createShareIntent());
                break;
            case R.id.action_fullscreen:
                showDialog();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    private void showDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog);

        TextView text = (TextView) dialog.findViewById(R.id.alertText);
        text.setText(lyrics);

        Button btnClose = (Button) dialog.findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

        dialog.show();
    }

    private Intent createShareIntent() {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_SUBJECT, "Saregama Bansuri Notes");
        i.putExtra(Intent.EXTRA_TEXT, lyricsTitle + ":\n\n" + lyrics + "\n\n" + "https://play.google.com/store/apps/details?id=com.ashokkumarshrestha.saregamabasurinotes");

        return i;
    }
}
