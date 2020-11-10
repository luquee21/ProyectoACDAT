package com.proyecto.acdat.model;

public class PlayList {
    int id;
    String name;
    String description;
    Song[] songs;
    User[] subscribers;
    User creator;

    public PlayList(int id,String name,String description) {
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
        return songs;
    }

    public void setSongs(Song[] songs) {
        this.songs = songs;
    }

    public User[] getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(User[] subscribers) {
        this.subscribers = subscribers;
    }

    public User getCreator() {
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
}
