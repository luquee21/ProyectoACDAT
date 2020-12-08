package com.proyecto.acdat.model;


import com.proyecto.acdat.utils.Connection;
import org.hibernate.Hibernate;

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

    public boolean addDisc(Disc d) {
        boolean flag = false;
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            manager.persist(d);
            manager.getTransaction().commit();
            flag = true;
        } catch (Exception e) {
            System.out.println(e);
        }

        manager.close();
        return flag;
    }

    public boolean deleteDisc(Disc disc) {
        boolean flag = false;
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            Disc d = manager.find(Disc.class, disc.id);
            manager.remove(d);
            manager.getTransaction().commit();
            flag = true;

        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
        manager.close();
        return flag;
    }

    public boolean updateDisc(Disc disc) {
        boolean flag = false;
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            Disc d = manager.find(Disc.class, disc.id);
            d.setName(disc.getName());
            d.setPhoto(disc.getPhoto());
            d.setDate(disc.getDate());
            manager.merge(d);
            manager.getTransaction().commit();
            flag = true;
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
        manager.close();
        return flag;
    }

    public List<Disc> getAllDisc(Boolean flag) {
        List<Disc> disc = null;
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            TypedQuery query = manager.createNamedQuery("Disc.selectAll", Disc.class);
            disc = (List<Disc>) query.getResultList();
            if(flag){
                for(Disc d : disc){
                    Hibernate.initialize(d.getArtist());
                }
            }
            manager.getTransaction().commit();

        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
        manager.close();
        return disc;
    }

    public List<Disc> getDisc(String name, Boolean flag) {
        List<Disc> disc = null;
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            TypedQuery query = manager.createNamedQuery("Disc.selectByName", Disc.class);
            query.setParameter("name", name);
            disc = (List<Disc>) query.getResultList();
            if(flag){
                for(Disc d : disc){
                    Hibernate.initialize(d.getArtist());
                }
            }
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
        manager.close();
        return disc;
    }

    public Disc getDiscById(int id, Boolean flag) {
        Disc disc = null;
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            disc = manager.find(Disc.class,id);
            if(flag){
                    Hibernate.initialize(disc.getArtist());
            }
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
        manager.close();
        return disc;
    }

    public List<Disc> getDiscByArtist(Artist artist, Boolean flag) {
        List<Disc>disc = null;
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            TypedQuery query = manager.createNamedQuery("Disc.selectByArtist", Disc.class);
            query.setParameter("id_artist", artist.id);
            disc = query.getResultList();
            if(flag){
                for(Disc d : disc){
                    Hibernate.initialize(d.getArtist());
                }
            }
            manager.getTransaction().commit();
        } catch (Exception e) {
        }
        manager.close();
        return disc;
    }
    public boolean deleteAllDiscOfArtist(int id) {
        boolean flag = false;
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            TypedQuery query = manager.createNamedQuery("Disc.deleteAllDiscOfArtist", Disc.class);
            query.setParameter("id_artist",id);
            query.executeUpdate();
            manager.getTransaction().commit();
            flag = true;
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
        manager.close();
        return flag;
    }

    public boolean removeSongs(Disc d){
        boolean flag = false;
        manager = Connection.getManager();
        try{
            manager.getTransaction().begin();
            d.songs = null;
            manager.getTransaction().commit();
            flag=true;
        }catch (Exception e){
            System.out.println(e);
        }
        manager.close();
        return flag;
    }
}
