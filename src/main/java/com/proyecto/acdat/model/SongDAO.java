package com.proyecto.acdat.model;

import com.proyecto.acdat.utils.Connection;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.swing.text.html.parser.Entity;
import java.util.List;

public class SongDAO extends Song {

    private EntityManager manager;

    public SongDAO(String name, int duration) {
        super(name, duration);
    }

    public SongDAO() {
    }

    public boolean addSong(Song s) {
        boolean flag=false;
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            manager.persist(s);
            manager.getTransaction().commit();
            flag = true;
        } catch (Exception e) {
            System.out.println("dao" + e);
        }

        manager.close();
        return flag;
    }

    public boolean deleteSong(Song song) {
        boolean flag = false;
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            Song s = manager.find(Song.class, song.id);
            manager.remove(s);
            manager.getTransaction().commit();
            flag = true;

        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
        manager.close();
        return flag;
    }

    public boolean updateSong(Song song) {
        boolean flag = false;
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            Song s = manager.find(Song.class, song.id);
            s.setName(s.getName());
            s.setDuration(s.getDuration());
            manager.merge(s);
            manager.getTransaction().commit();
            flag = true;
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
        manager.close();
        return flag;
    }

    public List<Song> getAllSong() {
        List<Song> song = null;
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            TypedQuery query = manager.createNamedQuery("Song.selectAll", Song.class);
            song = (List<Song>) query.getResultList();
            manager.getTransaction().commit();

        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
        manager.close();
        return song;
    }

    public List<Song> getSongByName(String name) {
        List<Song> song = null;
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            TypedQuery query = manager.createNamedQuery("Song.selectByName", Song.class);
            query.setParameter("name", name);
            song = (List<Song>) query.getResultList();
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
        manager.close();
        return song;
    }

    public Song getSongById(int id) {
        Song song = null;
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            song = manager.find(Song.class,id);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
        manager.close();
        return song;
    }

    public List<Song> getSongByArtist(Artist artist) {
        List<Song> song = null;
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            TypedQuery query = manager.createNamedQuery("Song.selectByArtist", Song.class);
            query.setParameter("artist_name", artist.name);
            song = query.getResultList();
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
        manager.close();
        return song;
    }

    public List<Song> getSongByDisc(Disc disc) {
        List<Song> song = null;
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            TypedQuery query = manager.createNamedQuery("Song.selectByDisc", Song.class);
            query.setParameter("id_disc", disc.id);
            song = query.getResultList();
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
        manager.close();
        return song;
    }

    public Song getSongByPlayList(PlayList playList) {
        Song song = null;
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            TypedQuery query = manager.createNamedQuery("Song.selectByPlaylist", Song.class);
            query.setParameter("id_list", playList.id);
            song = (Song) query.getResultList();
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
        manager.close();
        return song;
    }

    public boolean deleteAllSongOfDisc(int id) {
        boolean flag = false;
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            TypedQuery query = manager.createNamedQuery("Song.deleteAllSongOfDisc", Song.class);
            query.setParameter("id_disc",id);
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
