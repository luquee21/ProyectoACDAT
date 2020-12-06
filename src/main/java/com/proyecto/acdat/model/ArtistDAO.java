package com.proyecto.acdat.model;

import com.proyecto.acdat.utils.Connection;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;



public class ArtistDAO extends Artist {

    private EntityManager manager;
    private static final String SELECTALL = "SELECT * FROM Artista";
    private static final String SELECTBYNAME = "SELECT * FROM Artista WHERE nombre=?";
    private static final String SELECTBYIDSONG = "SELECT * FROM Artista INNER JOIN Disco ON Artista.id = Disco.id_artista INNER JOIN Cancion ON Cancion.id_disco = Disco.id WHERE Cancion.id = ?";
    private static final String SELECTBYID = "SELECT * FROM Artista WHERE id=?";
    private static final String SELECTBYNATIONALITY = "SELECT * FROM Artista WHERE nacionalidad=?";
    private static final String INSERTARTIST = "INSERT INTO Artista (nombre,nacionalidad,foto) VALUES(?,?,?)";
    private static final String DELETEARTIST = "DELETE FROM Artista WHERE nombre=?";
    private static final String UPDATEARTIST = "UPDATE Artista SET nombre=?, nacionalidad=?, foto=? WHERE id=?";

    public ArtistDAO(String name, String nationality, String photo) {
        super(name, nationality, photo);
    }

    public ArtistDAO() {
    }

    public void addArtist(Artist a) {
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            manager.persist(a);

        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
        manager.getTransaction().commit();
        manager.close();
    }

    public void deleteArtist(Artist artist) {
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            Artist a = manager.find(Artist.class, artist.id);
            manager.remove(a);
            manager.getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e);
            manager.getTransaction().rollback();
        }
        manager.close();
    }

    public void updateArtist(Artist artist) {
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            Artist a = manager.find(Artist.class, artist.id);
            a.setName(artist.getName());
            a.setNationality(artist.getNationality());
            a.setPhoto(artist.getPhoto());
            manager.merge(a);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
        manager.close();
    }

    public List<Artist> getAllArtists() {
        List<Artist> artists = null;
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            TypedQuery query = manager.createNamedQuery("Artist.selectAll", Artist.class);
            artists = (List<Artist>) query.getResultList();
            manager.getTransaction().commit();

        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
        manager.close();
        return artists;
    }

    public Artist getArtist(String name) {
        Artist artist = null;
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            TypedQuery query = manager.createNamedQuery("Artist.selectByName", Artist.class);
            query.setParameter("name", name);
            artist = (Artist) query.getSingleResult();
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
        manager.close();
        return artist;
    }


}
