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
    private String addSub = "INSERT INTO subscribers VALUES (?, ?)";

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
            manager.remove(playList);
            manager.getTransaction().commit();
            flag = true;
        } catch (Exception e) {
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
            Query query = manager.createNativeQuery("Playlist.selectBySub", User.class);
            query.setParameter("id_playlist", id);
            query.executeUpdate();
            subs = (List<User>) query.getResultList();
            for(User u : subs){
                for(PlayList p : u.getPlayLists()){
                    Hibernate.initialize(p.getSubscribers());
                }
            }
            manager.getTransaction().commit();
        } catch (Exception e) {
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
        }
        manager.close();
        return flag;
    }

    public boolean addSubOnPlaylist(int id_sub, int id_playlist) {
        boolean flag = false;
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            PlayList pl = getPlaylistById(id_playlist);
            User u = MyInstance.getInstance().selectUserById(id_sub, true);

            List<User> users = pl.getSubscribers();
            users.add(u);
            pl.setSubscribers(users);

            manager.getTransaction().commit();
            flag = true;
        } catch (Exception e) {
            System.out.println(e);
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
        }
        manager.close();
        return flag;
    }

}
