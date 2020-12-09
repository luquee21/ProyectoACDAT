package com.proyecto.acdat.model;

import com.proyecto.acdat.instance.MyInstance;
import com.proyecto.acdat.utils.Connection;
import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class PlayListDAO extends PlayList {

    private EntityManager manager;
    private String addSub = "INSERT INTO Suscripcion (id_lista,id_usuario) VALUES (?, ?)";
    private String deleteSub = "DELETE FROM Suscripcion WHERE id_lista=? and id_usuario=?";
    private String addSong = "INSERT INTO Lista_cancion (id_lista,id_cancion) VALUES (?, ?)";
    private String deleteSong = "DELETE FROM Lista_cancion WHERE id_lista=? and id_cancion=?";
    private String selectSubOfPlaylist = "SELECT * FROM Suscripcion s INNER JOIN Usuario u ON s.id_usuario=u.id WHERE s.id_lista= ?";

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
        }
        manager.close();
        return flag;
    }

    public boolean deletePlaylist(PlayList playList) {
        boolean flag = false;
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            playList = manager.find(PlayList.class, playList.id);
            manager.remove(playList);
            manager.getTransaction().commit();
            flag = true;
        } catch (Exception e) {
            System.out.println(e);
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
            for(PlayList p : playLists){
                Hibernate.initialize(p.getSubscribers());
                Hibernate.initialize(p.getCreator());
                Hibernate.initialize(p.getSongs());
            }
            manager.getTransaction().commit();

        } catch (Exception e) {
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
            for(PlayList p : playLists){
                Hibernate.initialize(p.getSubscribers());
                Hibernate.initialize(p.getCreator());
                Hibernate.initialize(p.getSongs());
            }
            manager.getTransaction().commit();
        } catch (Exception e) {
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
            Hibernate.initialize(playList.getSubscribers());
            Hibernate.initialize(playList.getSongs());
            Hibernate.initialize(playList.getCreator());
            manager.getTransaction().commit();
        } catch (Exception e) {
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
            for(PlayList p : playLists){
                Hibernate.initialize(p.getSubscribers());
                Hibernate.initialize(p.getCreator());
                Hibernate.initialize(p.getSongs());
            }
            manager.getTransaction().commit();
        } catch (Exception e) {
        }
        manager.close();
        return playLists;
    }

    public List<User> getSubOfPlaylist(int id) {
        List<User> subs = null;
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            Query query = manager.createNativeQuery(selectSubOfPlaylist, User.class);
            query.setParameter(1, id);
            subs = (List<User>) query.getResultList();
            for(User u : subs){
                for(PlayList p : u.getPlayLists()){
                    Hibernate.initialize(p.getSubscribers());
                }
            }
            manager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
        }
        manager.close();
        return subs;
    }

    public boolean addSongOnPlaylist(int id_song, int id_playlist) {
        boolean flag = false;
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            Query query = manager.createNativeQuery(addSong);
            query.setParameter(1, id_playlist);
            query.setParameter(2, id_song);
            if(query.executeUpdate()>0){
                flag = true;
            }
            manager.getTransaction().commit();
        } catch (Exception e) {
        }
        manager.close();
        return flag;
    }

    public boolean addSubOnPlaylist(int id_sub, int id_playlist) {
        boolean flag = false;
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            Query query = manager.createNativeQuery(addSub);
            query.setParameter(1,id_playlist);
            query.setParameter(2, id_sub);
            if(query.executeUpdate()>0){
                flag = true;
            }
            manager.getTransaction().commit();
        } catch (Exception e) {
        }
        manager.close();
        return flag;
    }

    public boolean deleteSongOfPlaylist(int id_song, int id_playlist) {
        boolean flag = false;
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            Query query = manager.createNativeQuery(deleteSong);
            query.setParameter(1, id_playlist);
            query.setParameter(2, id_song);
            if(query.executeUpdate()>0){
                flag = true;
            }
            manager.getTransaction().commit();
        } catch (Exception e) {
        }
        manager.close();
        return flag;
    }

    public boolean deleteSubOfPlaylist(User user, int id) {
        boolean flag = false;
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            Query query = manager.createNativeQuery(deleteSub);
            query.setParameter(1, id);
            query.setParameter(2, user.id);
            if(query.executeUpdate()>0){
                flag = true;
            }
            manager.getTransaction().commit();

        } catch (Exception e) {
        }
        manager.close();
        return flag;
    }

}
