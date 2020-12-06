package com.proyecto.acdat.model;

import com.proyecto.acdat.utils.Connection;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class PlayListDAO extends PlayList {

    private EntityManager manager;
    public PlayListDAO(String name, String description) {
        super(name, description);
    }

    public PlayListDAO() {
    }

    public void addplaylist(PlayList a) {
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

    public void deletePlaylist(PlayList playList) {
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            PlayList a = manager.find(PlayList.class, playList.id);
            manager.remove(a);
            manager.getTransaction().commit();

        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
        manager.close();
    }

    public void updatePlaylist(PlayList playList) {
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            PlayList a = manager.find(PlayList.class, playList.id);
            a.setName(playList.getName());
            a.setDescription(playList.getDescription());
            manager.merge(a);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
        manager.close();
    }

    public List<PlayList> getAllPlaylist() {
        List<PlayList> playLists = null;
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            TypedQuery query = manager.createNamedQuery("Playlist.selectAll", PlayList.class);
            playLists = (List<PlayList>) query.getResultList();
            manager.getTransaction().commit();

        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
        manager.close();
        return playLists;
    }

    public List<PlayList> getPlaylistByName(String name) {
        List<PlayList> playLists = null;
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            TypedQuery query = manager.createNamedQuery("Playlist.selectByName", PlayList.class);
            query.setParameter("name", name);
            playLists = (List<PlayList>) query.getResultList();
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
        manager.close();
        return playLists;
    }

    public PlayList getPlaylistById(int id) {
        PlayList playList = null;
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            TypedQuery query = manager.createNamedQuery("Playlist.selectById", PlayList.class);
            query.setParameter("id", id);
            playList = (PlayList) query.getSingleResult();
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
        manager.close();
        return playList;
    }

    public List<PlayList> getPlaylistByUser(User user) {
        List<PlayList> playLists = null;
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            TypedQuery query = manager.createNamedQuery("Playlist.selectByIdUser", PlayList.class);
            query.setParameter("id_user", user);
            playLists = (List<PlayList>) query.getResultList();
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
        manager.close();
        return playLists;
    }

    public List<PlayList> getPlaylistBySub(int id) {
        List<PlayList> playLists = null;
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            Query query = manager.createNativeQuery("Playlist.selectBySub", PlayList.class);
            query.setParameter("id_playlist", id);
            playLists = (List<PlayList>) query.getResultList();
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
        manager.close();
        return playLists;
    }






    //funciones del sub
}
