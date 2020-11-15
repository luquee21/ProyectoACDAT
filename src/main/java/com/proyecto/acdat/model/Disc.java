package com.proyecto.acdat.model;


import java.util.List;

public class Disc {
    private int id;
    private String name;
    private String photo;
    private List<Song> songs;
    private int id_artista;
    private Artist artist;
    private String date;

    public Disc() {
    }

    public Disc(int id, String name, String photo) {
        this.id = id;
        this.name = name;
        this.photo = photo;
    }

    public Disc(String name, String photo, String date, int id_artista) {
        this.name = name;
        this.photo = photo;
        this.date = date;
        this.id_artista = id_artista;
    }

    public Disc(int id, String name, String photo, String date, int id_artista) {
        this.id = id;
        this.id_artista = id_artista;
        this.name = name;
        this.photo = photo;
        this.date = date;
    }

    public int getId_artista() {
        return id_artista;
    }

    public void setId_artista(int id_artista) {
        this.id_artista = id_artista;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Artist getArtist() {
        if (artist == null) {
            artist = ArtistDAO.selectById(id_artista);
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

    public List<Song> getSongs() {
        if (songs == null) {
            songs = SongDAO.selectAllSongByDisc(id);
        }
        return SongDAO.selectAllSongByDisc(this.id);
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    @Override
    public String toString() {
        return "[Disco] = id: " + id + ", nombre: " + name + ", fecha: " + date;

    }

    @Override
    public boolean equals(Object o) {
        Disc disc = (Disc) o;
        return id == disc.id && name.equals(disc.getName()) && photo.equals(disc.getPhoto());
    }
}
