package com.proyecto.acdat.model;

import com.proyecto.acdat.utils.Connection;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;



public class ArtistDAO extends Artist {

    private EntityManager manager;
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
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
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

    public Artist getArtistByName(String name) {
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


    public Artist getArtistById(int id) {
        Artist artist = null;
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            TypedQuery query = manager.createNamedQuery("Artist.selectById", Artist.class);
            query.setParameter("id", id);
            artist = (Artist) query.getSingleResult();
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
        manager.close();
        return artist;
    }

    public Artist getArtistByNationality(String nationality) {
        Artist artist = null;
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            TypedQuery query = manager.createNamedQuery("Artist.selectByNationality", Artist.class);
            query.setParameter("nationality", nationality);
            artist = (Artist) query.getSingleResult();
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
        manager.close();
        return artist;
    }


}
