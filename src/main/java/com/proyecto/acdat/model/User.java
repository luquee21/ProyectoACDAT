package com.proyecto.acdat.model;

public class User {
    private int id;
    private String name;
    private String email;
    private String photo;
    private PlayList[] playlists;

    public User() {
    }

    public User(String name, String email, String photo) {
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

    @Override
    public String toString() {
        return "[Usuario] = id: " + id + ", nombre: " + name + ", email: " + email;
    }

    @Override
    public boolean equals(Object o) {
        User user = (User) o;
        return id == user.id && email.equals(user.getEmail()) && photo.equals(user.getPhoto());
    }
}
