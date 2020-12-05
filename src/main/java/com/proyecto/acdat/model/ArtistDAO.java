package com.proyecto.acdat.model;

import com.proyecto.acdat.utils.Connection;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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

    public void deleteArtist(Artist a) {
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            manager.remove(a);
            manager.getTransaction().commit();

        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
        manager.close();
    }

    public void updateArtist(Artist a) {
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
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
            Query query = manager.createNamedQuery("Artist.selectAll", Artist.class);
            artists = (List<Artist>) query.getResultList();
            manager.getTransaction().commit();

        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
        manager.close();
        return artists;
    }


}
