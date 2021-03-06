package com.proyecto.acdat.model;

import java.util.List;

public class User {
    private int id;
    private String name;
    private String email;
    private String photo;
    private List<PlayList> playLists;

    public User() {
    }

    public User(String name, String email, String photo) {
        this.name = name;
        this.email = email;
        this.photo = photo;
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

    public List<PlayList> getPlayLists() {
        if (playLists == null) {
            playLists = PlayListDAO.selectPlayListByName(name);
        }
        return playLists;
    }

    public void setPlayLists(List<PlayList> playLists) {
        this.playLists = playLists;
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
