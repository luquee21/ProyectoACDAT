package com.proyecto.acdat.model;
import java.util.List;
import java.util.Objects;

public class Artist {
    private int id;
    private String name;
    private String nationality;
    private String photo;
    private List<Disc> disc;

    public Artist() {
    }

    public Artist(int id,String name, String nationality, String photo) {
        this.id=id;
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
        String print;
        if(disc == null){
            print =  "[Artista] = id: " + id + ", nombre: " + name + ", nacionalidad: " + nationality;
        } else {
            print = "[Artista] = id: " + id + ", nombre: " + name + ", nacionalidad: " + nationality + " | " + disc;
        }

        return print;
    }

    @Override
    public boolean equals(Object o) {
        Artist artist = (Artist) o;
        return name.equals(artist.getName()) && nationality.equals(artist.getNationality()) && photo.equals(artist.getPhoto());
    }
}
