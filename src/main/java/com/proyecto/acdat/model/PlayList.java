package com.proyecto.acdat.model;

public class PlayList {
    int id;
    String name;
    String description;
    Song[] songs;
    User[] subscribers;
    int id_creator; //FALTA
    User creator;

    public PlayList() {
    }

    public PlayList(int id, String name, String description, int id_creator) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.id_creator = id_creator;
    }
    public PlayList(String name,String description,User creator){
        this.name = name;
        this.description = description;
        this.creator = creator;
    }
    public PlayList(String name, String description){
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Song[] getSongs() {
        //FALTA
        return songs;
    }

    public void setSongs(Song[] songs) {
        this.songs = songs;
    }

    public User[] getSubscribers() {
        //FALTA
        return subscribers;
    }

    public void setSubscribers(User[] subscribers) {
        this.subscribers = subscribers;
    }

    public User getCreator() {
        if (creator == null) {
            creator = UserDAO.selectById(id_creator);
        }
        return creator;

    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    @Override
    public boolean equals(Object o) {
        PlayList playList = (PlayList) o;
        return id == playList.id;
    }

    @Override
    public String toString() {
        String print;

        if (songs == null && subscribers == null) {
            print = "[PlayList] = id: " + id + ", nombre: " + name + ", descripcion: " + description + " | " + creator;
        } else if (songs == null && subscribers != null) {
            print = "[PlayList] = id: " + id + ", nombre: " + name + ", descripcion: " + description + " | " + creator + " | " + subscribers;
        } else if (songs != null && subscribers == null) {
            print = "[PlayList] = id: " + id + ", nombre: " + name + ", descripcion: " + description + " | " + creator + " | " + songs;
        } else {
            print = "[PlayList] = id: " + id + ", nombre: " + name + ", descripcion: " + description + " | " + creator + " | " + subscribers + " | " + songs;
        }
        return print;
    }
}
