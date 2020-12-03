package com.proyecto.acdat.model;

import com.proyecto.acdat.utils.Connection;

import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.Query;
import java.util.List;


@NamedQuery(name = "selectAll", query = "SELECT * FROM Artista")
public class ArtistDAO extends Artist {


    private static EntityManager manager;
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


    public void addArtist() {
        manager = Connection.getEmf().createEntityManager();
        manager.getTransaction().begin();
        try {
            manager.persist(this);
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
        manager.getTransaction().commit();
        manager.close();
    }

    public void deleteArtist() {
        manager = Connection.getEmf().createEntityManager();
        manager.getTransaction().begin();
        try {
            manager.remove(this);
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
        manager.getTransaction().commit();
        manager.close();
    }

    public void updateArtist() {
        manager = Connection.getEmf().createEntityManager();
        manager.getTransaction().begin();
        try {
            manager.merge(this);
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
        manager.getTransaction().commit();
        manager.close();
    }

    public void getArtist() {
        manager = Connection.getEmf().createEntityManager();
        manager.getTransaction().begin();
        try {
            Query query = manager.createNamedQuery(".findAll");
            List results = query.getResultList();
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
        manager.getTransaction().commit();
        manager.close();
    }


}
