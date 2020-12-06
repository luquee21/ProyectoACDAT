package com.proyecto.acdat;

import com.proyecto.acdat.model.Artist;
import com.proyecto.acdat.model.ArtistDAO;
import com.proyecto.acdat.utils.Connection;

import javax.persistence.EntityManager;
import java.util.List;


public class App {
    public static void main(String[] args) {
        EntityManager manager = Connection.getManager();
        manager.getTransaction().begin();
        ArtistDAO dao = new ArtistDAO();
        Artist artist = dao.getArtist("ER KILLO");
        dao.updateArtist(artist);
        List<Artist> artists = dao.getAllArtists();
        manager.getTransaction().commit();
        System.out.println(artists);


    }
}
