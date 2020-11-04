package com.proyecto.acdat.model;

import java.sql.Blob;
import java.sql.Date;

public class Disc {
    private int id;
    private String name;
    private String photo;
    private Song[] songs;
    private Artist artist;
    private Date date;

    public Disc() {
    }

    public Disc(int id, String name, String photo) {
        this.id = id;
        this.name = name;
        this.photo = photo;
    }

    public Date getDate() { return date; }

    public void setDate(Date date) { this.date = date; }

    public String getPhoto() { return photo; }

    public void setPhoto(String photo) { this.photo = photo; }

    public Artist getArtist() { return artist; }

    public void setArtist(Artist artist) { this.artist = artist; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Song[] getSongs() {
        return songs;
    }

    public void setSongs(Song[] songs) {
        this.songs = songs;
    }
}
