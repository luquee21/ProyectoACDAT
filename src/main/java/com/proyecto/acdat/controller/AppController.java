package com.proyecto.acdat.controller;

import com.proyecto.acdat.model.*;
import com.proyecto.acdat.utils.Connection;

import javax.persistence.EntityManager;
import java.util.List;

public class AppController implements IAppController {
    private final ArtistDAO adao = new ArtistDAO();
    private final UserDAO udao = new UserDAO();
    private EntityManager manager;
    private final PlayListDAO playListDAO = new PlayListDAO();
    private final SongDAO songDAO = new SongDAO();
    private final DiscDAO discDAO = new DiscDAO();


    @Override
    public boolean addArtist(Artist artist) {
        return adao.addArtist(artist);
    }

    @Override
    public boolean deleteArtist(Artist artist) {
        return adao.deleteArtist(artist);
    }

    @Override
    public boolean updateArtist(Artist artist) {
        return adao.updateArtist(artist);
    }

    @Override
    public List<Artist> selectAllArtist() {
        return adao.getAllArtists();
    }

    @Override
    public Artist selectArtistByName(String name) {
        return adao.getArtistByName(name);
    }

    @Override
    public Artist selectArtistById(int id) {
        return adao.getArtistById(id);
    }


    @Override
    public List<Artist> selectArtistByNationality(String nationality) {
        return adao.getArtistByNationality(nationality);
    }

    @Override
    public boolean addDisc(Disc disc,String artist) {
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            Artist aux = adao.getArtistByName(artist);
            disc.setArtist(aux);
            manager.getTransaction().commit();

        }catch (Exception ex){
        }
        manager.close();
        return discDAO.addDisc(disc);
    }

    @Override
    public boolean deleteDisc(int id) {
        Disc disc = discDAO.getDiscById(id);
        return discDAO.deleteDisc(disc);
    }


    @Override
    public boolean deleteAllDiscOfArtist(int id) {
        return discDAO.deleteAllDiscOfArtist(id);
    }

    @Override
    public boolean updateDisc(Disc disc) {
        return discDAO.updateDisc(disc);
    }

    @Override
    public List<Disc> selectAllDisc() {
        return discDAO.getAllDisc();
    }

    @Override
    public List<Disc> selectDiscByName(String name) {
        return discDAO.getDisc(name);
    }

    @Override
    public List<Disc> selectDiscByArtist(int id) {
        Artist artist = adao.getArtistById(id);
        return discDAO.getDiscByArtist(artist);
    }

    @Override
    public Disc selectDiscById(int id) {
        return discDAO.getDiscById(id);
    }

    @Override
    public boolean addPlayList(PlayList playList, User user) {
        manager = Connection.getManager();
        try{
            manager.getTransaction().begin();
            playList.setCreator(user);
            manager.getTransaction().commit();
        }catch (Exception e){
            manager.getTransaction().rollback();
        }
        manager.close();
        return playListDAO.addplaylist(playList);
    }

    @Override
    public boolean addSongToPlayList(Song song, int id) {
        return playListDAO.addSongToPlaylist(song, id);
    }

    @Override
    public boolean addSubToPlayList(User user, int id) {
        return playListDAO.addSubToPlaylist(user, id);
    }

    @Override
    public boolean deleteSongOfPlayList(int idSong, int idPlaylist) {
        return playListDAO.deleteSongOfPlaylist(idSong, idPlaylist);
    }

    @Override
    public boolean deleteSubOfPlayList(User user, int id) {
        return playListDAO.deleteSubOfPlaylist(user, id);
    }

    @Override
    public boolean deletePlayList(PlayList playList) {
        return playListDAO.deletePlaylist(playList);
    }

    @Override
    public boolean updatePlayList(PlayList playList) {
        return playListDAO.updatePlaylist(playList);
    }

    @Override
    public PlayList selectPlayListById(int id) {
        return playListDAO.getPlaylistById(id);
    }

    @Override
    public List<User> selectSubOfPlaylist(int id) {
        return playListDAO.getSubOfPlaylist(id);
    }

    @Override
    public List<PlayList> selectAllPlayList() {
        return playListDAO.getAllPlaylist();
    }

    @Override
    public List<PlayList> selectPlayListByName(String name) {
        return playListDAO.getPlaylistByName(name);
    }

    @Override
    public List<PlayList> selectPlayListByUser(User user) {
        return playListDAO.getPlaylistByUser(user);
    }

    @Override
    public boolean addSong(Song song, Disc disc) {
        manager= Connection.getManager();
        try {
            manager.getTransaction().begin();
            song.setDisc(disc);
            manager.getTransaction().commit();
        }catch (Exception e){
            manager.getTransaction().rollback();
        }
        manager.close();
        return songDAO.addSong(song);
    }

    @Override
    public boolean deleteSong(int id) {
        Song song = songDAO.getSongById(id);
        return songDAO.deleteSong(song);
    }

    @Override
    public boolean deleteAllSongOfDisc(int id) {
        return songDAO.deleteAllSongOfDisc(id);
    }

    @Override
    public boolean updateSong(Song song) {
        return songDAO.updateSong(song);
    }

    @Override
    public List<Song> selectAllSong() {
        return songDAO.getAllSong();
    }

    @Override
    public List<Song> selectSongByName(String name) {
        return songDAO.getSongByName(name);
    }

    @Override
    public List<Song> selectAllSongOfArtist(String name) {
        Artist artist = adao.getArtistByName(name);
        return songDAO.getSongByArtist(artist);
    }

    @Override
    public List<Song> selectAllSongByDisc(int id) {
        Disc disc = discDAO.getDiscById(id);
        return songDAO.getSongByDisc(disc);
    }

    @Override
    public boolean addUser(User user) {
        return false;
    }

    @Override
    public boolean deleteUser(String email) {
        return false;
    }

    @Override
    public boolean deleteAllPlayListOfUser(int id_user) {
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }

    @Override
    public List<User> selectAllUser() {
        return null;
    }

    @Override
    public User selectUserByEmail(String email) {
        return null;
    }

    @Override
    public List<User> selectUserByName(String name) {
        return null;
    }
}
