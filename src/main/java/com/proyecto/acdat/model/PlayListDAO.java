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

    public boolean addplaylist(PlayList a) {
        boolean flag = false;
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            manager.persist(a);
            manager.getTransaction().commit();
            flag = true;
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
        manager.close();
        return flag;
    }

    public boolean deletePlaylist(PlayList playList) {
        boolean flag = false;
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            PlayList a = manager.find(PlayList.class, playList.id);
            manager.remove(a);
            manager.getTransaction().commit();
            flag = true;
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
        manager.close();
        return flag;
    }

    public boolean updatePlaylist(PlayList playList) {
        boolean flag = false;
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            PlayList a = manager.find(PlayList.class, playList.id);
            a.setName(playList.getName());
            a.setDescription(playList.getDescription());
            manager.merge(a);
            manager.getTransaction().commit();
            flag = true;
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
        manager.close();
        return flag;
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
            playList = manager.find(PlayList.class, id);
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

    public List<User> getSubOfPlaylist(int id) {
        List<User> subs = null;
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            Query query = manager.createNativeQuery("Playlist.selectBySub", User.class);
            query.setParameter("id_playlist", id);
            query.executeUpdate();
            subs = (List<User>) query.getResultList();
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
        manager.close();
        return subs;
    }

    public boolean addSongOnPlaylist(int id_song, int id_playlist) {
        boolean flag = false;
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            Query query = manager.createNativeQuery("Playlist.addSong");
            query.setParameter("id_playlist", id_playlist);
            query.setParameter("id_song", id_song);
            query.executeUpdate();
            manager.getTransaction().commit();
            flag = true;
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
        manager.close();
        return flag;
    }

    public boolean addSubOnPlaylist(int id_sub, int id_playlist) {
        boolean flag = false;
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            Query query = manager.createNativeQuery("Playlist.addSong");
            query.setParameter("id_playlist", id_playlist);
            query.setParameter("id_song", id_sub);
            query.executeUpdate();
            manager.getTransaction().commit();
            flag = true;
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
        manager.close();
        return flag;
    }

    public boolean deleteSongOfPlaylist(int id_song, int id_playlist) {
        boolean flag = false;
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            Query query = manager.createNativeQuery("Playlist.deleteSong");
            query.setParameter("id_playlist", id_playlist);
            query.setParameter("id_song", id_song);
            query.executeUpdate();
            manager.getTransaction().commit();
            flag = true;
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
        manager.close();
        return flag;
    }

    public boolean deleteSubOfPlaylist(User user, int id) {
        boolean flag = false;
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            Query query = manager.createNativeQuery("Playlist.deleteSub");
            query.setParameter("id_playlist", id);
            query.setParameter("id_user", user.id);
            query.executeUpdate();
            manager.getTransaction().commit();
            flag = true;
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
        manager.close();
        return flag;
    }

}
