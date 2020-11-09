package com.proyecto.acdat.model;

public class Song {
    private int id;
    private String name;
    private int duration;
    private Disc disc;
    private Artist artist;

    public Song() {
    }

    public Song(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }

    public Song(int id, String name, int duration) {
        this.id = id;
        this.name = name;
        this.duration = duration;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Disc getDisc() {
        return disc;
    }

    public void setDisc(Disc disc) {
        this.disc = disc;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", duration=" + duration +
                ", disc=" + disc +
                ", artist=" + artist +
                '}';
    }
}
