package com.proyecto.acdat.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Lista")
@NamedQueries({
        @NamedQuery(name = "Playlist.selectAll", query = "SELECT l FROM PlayList l"),
        @NamedQuery(name = "Playlist.selectByName", query = "SELECT l FROM PlayList l WHERE l.name = :name"),
        @NamedQuery(name = "Playlist.selectById", query = "SELECT l FROM PlayList l WHERE l.id = :id"),
        @NamedQuery(name = "Playlist.selectByIdUser", query = "SELECT l FROM PlayList l WHERE l.creator= :id_user")
})
@NamedNativeQueries({
        @NamedNativeQuery(name = "Playlist.selectBySub", query = "SELECT s FROM Suscripcion s INNER JOIN Usuario ON Suscripcion.id_usuario=Usuario.id WHERE Suscripcion.id_lista= :id_playlist"),
})
public class PlayList implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected int id;
    @Column(name = "nombre")
    protected String name;
    @Column(name = "descripcion")
    protected String description;
    @ManyToMany
    @JoinTable(name = "Lista_cancion",
            joinColumns = @JoinColumn(name = "id_lista"), inverseJoinColumns = @JoinColumn(name = "id_cancion"))
    protected List<Song> songs;

    @ManyToMany
    @JoinTable(name = "Suscripcion",
            joinColumns = @JoinColumn(name = "id_lista"), inverseJoinColumns = @JoinColumn(name = "id_usuario"))
    protected List<User> subscribers;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario")
    protected User creator;

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
        if (creator != null) {
            List<PlayList> playlists = new ArrayList<>();
            playlists.add(this);
            creator.setPlayLists(playlists);
        }

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
