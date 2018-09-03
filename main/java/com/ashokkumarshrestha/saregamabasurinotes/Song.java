package com.ashokkumarshrestha.saregamabasurinotes;

/**
 * Created by Uchiha Ashuke on 3/19/2017.
 */

public class Song {
    private String title, desc, lyrics;

    public Song() {
    }

    public Song(String title, String desc, String lyrics) {
        this.title = title;
        this.desc = desc;
        this.lyrics = lyrics;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public String getLyrics() {
        return lyrics;
    }
}
