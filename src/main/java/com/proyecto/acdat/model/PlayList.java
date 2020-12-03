package com.proyecto.acdat.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Lista")
public class PlayList {
    @Id
    @Column(name = "id")
    int id;
    @Column(name = "nombre")
    String name;
    @Column(name = "descripcion")
    String description;
    List<Song> songs;
    List<User> subscribers;
    int id_creator;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario")
    User creator;

    public PlayList() {
    }

    public PlayList(int id, String name, String description, int id_creator) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.id_creator = id_creator;
    }

    public PlayList(String name, String description, User creator) {
        this.name = name;
        this.description = description;
        this.creator = creator;
    }

    public PlayList(String name, String description, int id) {
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
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public List<User> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<User> subscribers) {
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayList playList = (PlayList) o;
        return id == playList.id &&
                name.equals(playList.name) &&
                description.equals(playList.description);
    }

    @Override
    public String toString() {
        return "[PlayList] = id: " + id + ", nombre: " + name + ", descripcion: " + description;

    }
}
