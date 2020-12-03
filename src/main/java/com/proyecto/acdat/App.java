package com.proyecto.acdat;

import com.proyecto.acdat.model.ArtistDAO;

public class App {
    public static void main(String[] args) {

        ArtistDAO dao = new ArtistDAO("Pepe", "Jamaica", "123");
        System.out.println(dao.toString());
        dao.addArtist();


    }
}
