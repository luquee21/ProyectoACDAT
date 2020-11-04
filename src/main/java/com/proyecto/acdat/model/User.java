package com.proyecto.acdat.model;

public class User {
    private int id;
    private String name;
    private String email;
    private String photo;
    private PlayList[] playlists;

    public User() {
    }

    public User(int id, String name, String email, String photo) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.photo = photo;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PlayList[] getPlaylists() {
        return playlists;
    }

    public void setPlaylists(PlayList[] playlists) {
        this.playlists = playlists;
    }
}
