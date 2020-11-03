package com.proyecto.acdat.model;

public class Artist {
    int id;
    String name;
    String nationality;
    Disc[] discs;

    public Artist() {
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

    public Disc[] getDiscs() {
        return discs;
    }

    public void setDiscs(Disc[] discs) {
        this.discs = discs;
    }
}
