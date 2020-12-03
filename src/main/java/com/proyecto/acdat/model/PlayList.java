package com.proyecto.acdat.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Lista")
public class PlayList implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "nombre")
    private String name;
    @Column(name = "descripcion")
    private String description;
    @ManyToMany
    @JoinTable(name = "Lista_cancion",
            joinColumns = @JoinColumn(name = "id_lista"), inverseJoinColumns = @JoinColumn(name = "id_cancion"))
    private List<Song> songs;

    @ManyToMany
    @JoinTable(name = "Suscripcion",
            joinColumns = @JoinColumn(name = "id_lista"), inverseJoinColumns = @JoinColumn(name = "id_usuario"))
    private List<User> subscribers;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario")
    private User creator;

    public PlayList() {
    }

    public PlayList(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public PlayList(String name, String description) {
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
