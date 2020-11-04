package com.proyecto.acdat.model;

import java.sql.Blob;

public class Artist {
    private int id;
    private String name;
    private String nationality;
    private String photo;
    private Disc[] discs;

    public Artist() {
    }

    public Artist(int id, String name, String nationality, String photo) {
        this.id = id;
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

    public Disc[] getDiscs() {
        return discs;
    }

    public void setDiscs(Disc[] discs) {
        this.discs = discs;
    }
}
