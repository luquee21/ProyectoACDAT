package com.proyecto.acdat.model;


import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Disco")
@NamedQueries({
        @NamedQuery(name = "Disc.selectAll", query = "SELECT d FROM Disc d"),
        @NamedQuery(name = "Disc.selectByName", query = "SELECT d FROM Disc d WHERE d.name = :name"),
        @NamedQuery(name = "Disc.selectById", query = "SELECT d FROM Disc d WHERE d.id = :id"),
        @NamedQuery(name = "Disc.selectByArtist", query = "SELECT d FROM Disc d WHERE d.artist = :id_artist")
})
public class Disc implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected int id;
    @Column(name = "nombre", nullable = false)
    protected String name;
    @Column(name = "foto", nullable = false)
    protected String photo;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_artista", nullable = false)
    protected Artist artist;
    @Column(name = "fecha_prod", nullable = false)
    protected Date date;
    @OneToMany(mappedBy = "disc", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    protected List<Song> songs;

    public Disc() {
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
        if (songs != null) {
            for (Song s : songs) {
                s.setDisc(this);
            }
        }
    }

    public Disc(int id, String name, String photo, Date date) {
        this.id = id;
        this.name = name;
        this.photo = photo;
        this.date = date;
    }

    public Disc(String name, String photo, Date date) {
        this.name = name;
        this.photo = photo;
        this.date = date;
    }

    public Disc(String name, String photo) {
        this.name = name;
        this.photo = photo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
        List<Disc> discs = this.artist.getDisc();
        if (discs == null) {
            discs = new ArrayList<>();
        }
        if (!discs.contains(this)) {
            discs.add(this);
        }
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
