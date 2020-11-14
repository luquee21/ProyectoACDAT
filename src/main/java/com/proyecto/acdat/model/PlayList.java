package com.proyecto.acdat.model;

import java.util.List;
import java.util.Objects;

public class PlayList {
    int id;
    String name;
    String description;
    List<Song> songs;
    List<User> subscribers;
    int id_creator;
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
    public PlayList(String name, String description, int id){
        this.name = name;
        this.description = description;
        this.id = id;
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

    public List<Song> getSongs() {
        if(songs==null){
            songs=SongDAO.selectAllSongOfPlaylist(id);
        }
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public List<User> getSubscribers() {
       if(subscribers==null){
           subscribers=PlayListDAO.selectSubOfPlaylist(id);
       }
        return subscribers;
    }

    public void setSubscribers(List<User> subscribers) {
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayList playList = (PlayList) o;
        return id == playList.id &&
                name.equals(playList.name) &&
                description.equals(playList.description);
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
