package com.ashokkumarshrestha.saregamabasurinotes;

/**
 * Created by Uchiha Ashuke on 3/5/2017.
 */

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.MyViewHolder> {

    private List<Song> songList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;//, date, message;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            /*date = (TextView) view.findViewById(R.id.desc);
            message = (TextView) view.findViewById(R.id.lyrics);*/
        }
    }


    public SongAdapter(List<Song> moviesList) {
        this.songList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.song_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Song song = songList.get(position);
        holder.title.setText(song.getTitle());
        //holder.date.setText(song.getDesc());
        //holder.message.setText(song.getLyrics());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(view.getContext(),song.getLyrics(),Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(view.getContext(),NotesActivity.class);
                String str = song.getTitle()+"#"+song.getDesc()+"#"+song.getLyrics();
                intent.putExtra("Message",str);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }
}