package com.proyecto.acdat.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Cancion")
@NamedQueries({
        @NamedQuery(name = "Song.selectAll", query = "SELECT * FROM Cancion"),
        @NamedQuery(name = "Song.selectByName", query = "SELECT * FROM Cancion WHERE nombre = :name"),
        @NamedQuery(name = "Song.selectById", query = "SELECT * FROM Cancion WHERE id = :id"),
        @NamedQuery(name = "Song.selectByArtist", query = "SELECT * FROM Cancion INNER JOIN Disco ON Cancion.id_disco = Disco.id INNER JOIN Artista ON Artista.id=Disco.id_artista WHERE Artista.nombre= :artist_name"),
        @NamedQuery(name = "Song.selectByDisc", query = "SELECT * FROM Cancion INNER JOIN Disco ON Cancion.id_disco=Disco.id WHERE Disco.id= :id_disc"),
        @NamedQuery(name = "Song.selectByPlaylist", query = "SELECT * FROM Cancion INNER JOIN Lista_cancion ON Cancion.id = Lista_cancion.id_cancion WHERE Lista_cancion.id_lista = :id_list")

})
public class Song implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected int id;
    @Column(name = "nombre")
    protected String name;
    @Column(name = "duracion")
    protected int duration;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_disco")
    protected Disc disc;

    public Song() {
    }

    public Song(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }

    public Song(int id, String name, int duration) {
        this.id = id;
        this.name = name;
        this.duration = duration;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Disc getDisc() {
        return disc;
    }

    public void setDisc(Disc disc) {
        this.disc = disc;
    }

    @Override
    public String toString() {
        return "[Cancion] = id: " + id + ", nombre: " + name + ", duracion: " + duration;
    }

    @Override
    public boolean equals(Object o) {
        Song song = (Song) o;
        return id == song.id && name.equals(song.getName()) && duration == song.getDuration();
    }
}
