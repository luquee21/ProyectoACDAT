package com.proyecto.acdat.model;


import com.proyecto.acdat.utils.Connection;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.Date;
import java.util.List;

public class DiscDAO extends Disc {

    private EntityManager manager;

    public DiscDAO(String name, String photo, Date date) {
        super(name, photo, date);
    }

    public DiscDAO() {
    }

    public void addDisc(Disc d) {
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            manager.persist(d);
            manager.getTransaction().commit();

        } catch (Exception e) {
            manager.getTransaction().rollback();
        }

        manager.close();
    }

    public void deleteDisc(Disc disc) {
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            Disc d = manager.find(Disc.class, disc.id);
            manager.remove(d);
            manager.getTransaction().commit();

        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
        manager.close();
    }

    public void updateDisc(Disc disc) {
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            Disc d = manager.find(Disc.class, disc.id);
            d.setName(disc.getName());
            d.setPhoto(disc.getPhoto());
            d.setDate(disc.getDate());
            manager.merge(d);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
        manager.close();
    }

    public List<Disc> getAllDisc() {
        List<Disc> disc = null;
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            TypedQuery query = manager.createNamedQuery("Disc.selectAll", Disc.class);
            disc = (List<Disc>) query.getResultList();
            manager.getTransaction().commit();

        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
        manager.close();
        return disc;
    }

    public List<Disc> getDisc(String name) {
        List<Disc> disc = null;
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            TypedQuery query = manager.createNamedQuery("Disc.selectByName", Disc.class);
            query.setParameter("name", name);
            disc = (List<Disc>) query.getResultList();
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
        manager.close();
        return disc;
    }

    public Disc getDiscById(int id) {
        Disc disc = null;
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            disc = manager.find(Disc.class,id);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
        manager.close();
        return disc;
    }

    public Disc getDiscByArtist(Artist artist) {
        Disc disc = null;
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            TypedQuery query = manager.createNamedQuery("Disc.selectByArtist", Disc.class);
            query.setParameter("id_artist", artist.id);
            disc = (Disc) query.getResultList();
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
        manager.close();
        return disc;
    }
}
