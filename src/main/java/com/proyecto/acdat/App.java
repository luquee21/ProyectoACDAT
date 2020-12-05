package com.proyecto.acdat;

import com.proyecto.acdat.model.Artist;
import com.proyecto.acdat.model.ArtistDAO;
import com.proyecto.acdat.model.User;

import java.util.List;


public class App {
    public static void main(String[] args) {

        User u = new User("pepe", "123@", "12323");
        ArtistDAO dao = new ArtistDAO();


        List<Artist> artists = dao.getAllArtists();

        System.out.println(artists);
    }
}
