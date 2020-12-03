package com.proyecto.acdat.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Disco")
public class Disc implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected int id;
    @Column(name = "nombre")
    protected String name;
    @Column(name = "foto")
    protected String photo;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_artista")
    protected Artist artist;
    @Column(name = "fecha_prod")
    protected String date;


    @OneToMany(mappedBy = "disc", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    protected List<Song> songs;

    public Disc() {
    }

    public Disc(int id, String name, String photo, Artist artist, String date) {
        this.id = id;
        this.name = name;
        this.photo = photo;
        this.artist = artist;
        this.date = date;
    }

    public Disc(String name, String photo, Artist artist, String date) {
        this.name = name;
        this.photo = photo;
        this.artist = artist;
        this.date = date;
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
