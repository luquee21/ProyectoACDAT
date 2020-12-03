package com.proyecto.acdat.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Cancion")
public class Song implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "nombre")
    private String name;
    @Column(name = "duracion")
    private int duration;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_disco")
    private Disc disc;

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
