package com.proyecto.acdat.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Artista")
@NamedQueries({
        @NamedQuery(name = "Artist.selectAll", query = "SELECT a FROM Artist a"),
        @NamedQuery(name = "Artist.selectByName", query = "SELECT a FROM Artist a WHERE a.name = :name"),
        @NamedQuery(name = "Artist.selectById", query = "SELECT a FROM Artist a WHERE a.id = :id"),
        @NamedQuery(name = "Artist.selectByNationality", query = "SELECT a FROM Artist a WHERE a.nationality = :nationality")
})

@NamedNativeQueries({
        @NamedNativeQuery(name = "Artist.selectByIdSong", query = "SELECT a FROM Artist a INNER JOIN Disco ON a.id = Disc.id_artista INNER JOIN Song ON Song.id_disco = Disc.id WHERE Song.id = :id_song")
})
public class Artist implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected int id;
    @Column(name = "nombre", unique = true)
    protected String name;
    @Column(name = "nacionalidad")
    protected String nationality;
    @Column(name = "foto")
    protected String photo;
    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    protected List<Disc> disc;

    public Artist() {
    }

    public Artist(int id, String name, String nationality, String photo) {
        this.id = id;
        this.name = name;
        this.nationality = nationality;
        this.photo = photo;
    }

    public Artist(String name, String nationality, String photo) {
        this.name = name;
        this.nationality = nationality;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public List<Disc> getDisc() {
        return disc;
    }

    public void setDisc(List<Disc> disc) {
        this.disc = disc;
    }

    @Override
    public String toString() {
        return "[Artista] = id: " + id + ", nombre: " + name + ", nacionalidad: " + nationality;

    }

    @Override
    public boolean equals(Object o) {
        Artist artist = (Artist) o;
        return name.equals(artist.getName()) && nationality.equals(artist.getNationality()) && photo.equals(artist.getPhoto());
    }
}
