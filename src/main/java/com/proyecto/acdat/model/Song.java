package com.proyecto.acdat.model;

public class Song {
    private int id;
    private String name;
    private int duration;
    private Disc disc;
    private Artist artist;
    private int id_disc;

    public int getId_disc() {
        return id_disc;
    }

    public void setId_disc(int id_disc) {
        this.id_disc = id_disc;
    }

    public Song(int id, String name, int duration) {
        this.id = id;
        this.name = name;
        this.duration = duration;
    }

    public Song(String name, int duration, int id_disc) {
        this.name = name;
        this.duration = duration;
        this.id_disc= id_disc;
    }

    public Song(int id, String name, int duration, int id_disco) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.id_disc= id_disc;
    }


    public Artist getArtist() {
        if (artist == null) {
            artist = ArtistDAO.selectByIdSong(id);
        }
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
        if (disc == null) {
            disc = DiscDAO.selectById(id_disc);
        }
        return disc;
    }

    public void setDisc(Disc disc) {
        this.disc = disc;
    }

    @Override
    public String toString() {
        String print;
        if(disc == null && artist == null){
            print = "[Cancion] = id: " + id + ", nombre: " + name + ", duracion: " + duration;
        } else {
            print = "[Cancion] = id: " + id + ", nombre: " + name + ", duracion: " + duration + " |"  + disc + " " + artist;
        }
        return print;
    }

    @Override
    public boolean equals(Object o) {
        Song song = (Song) o;
        return id == song.id && name.equals(song.getName()) && duration == song.getDuration();
    }
}
