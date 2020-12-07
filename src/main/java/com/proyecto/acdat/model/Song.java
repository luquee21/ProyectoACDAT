package com.proyecto.acdat.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Cancion")
@NamedQueries({
        @NamedQuery(name = "Song.selectAll", query = "SELECT s FROM Song s"),
        @NamedQuery(name = "Song.selectByName", query = "SELECT s FROM Song s WHERE s.name = :name"),
        @NamedQuery(name = "Song.selectById", query = "SELECT s FROM Song s WHERE s.id = :id")
})
@NamedNativeQueries({
        @NamedNativeQuery(name = "Song.selectByArtist", query = "SELECT s FROM Song s INNER JOIN Disc ON Song.id_disco = Disc.id INNER JOIN Artist ON Artist.id=Disc.id_artista WHERE Artist.name= :artist_name"),
        @NamedNativeQuery(name = "Song.selectByDisc", query = "SELECT s FROM Song s INNER JOIN Disc ON Song.id_disco=Disc.id WHERE Disc.id= :id_disc"),
        @NamedNativeQuery(name = "Song.selectByPlaylist", query = "SELECT s FROM Song s INNER JOIN Lista_cancion ON Song.id = Lista_cancion.id_cancion WHERE Lista_cancion.id_lista = :id_list")

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
        List<Song> songs = this.disc.getSongs();
        if (songs == null) {
            songs = new ArrayList<>();
        }
        if (!songs.contains(this)) {
            songs.add(this);
        }
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
