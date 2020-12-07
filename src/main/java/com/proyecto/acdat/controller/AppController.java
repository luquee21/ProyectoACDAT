package com.proyecto.acdat.controller;

import com.proyecto.acdat.model.*;
import com.proyecto.acdat.utils.Connection;

import javax.persistence.EntityManager;
import java.util.List;

public class AppController implements IAppController {

    private EntityManager manager;
    PlayListDAO playListDAO = new PlayListDAO();


    @Override
    public boolean addArtist(Artist artist) {
        return false;
    }

    @Override
    public boolean deleteArtist(String name) {
        return false;
    }

    @Override
    public boolean updateArtist(Artist artist, int id) {

        return false;
    }

    @Override
    public List<Artist> selectAllArtist() {
        return null;
    }

    @Override
    public Artist selectArtistByName(String name) {
        return null;
    }

    @Override
    public Artist selectArtistById(int id) {

        return null;
    }


    @Override
    public List<Artist> selectArtistByNationality(String nationality) {
        return null;
    }

    @Override
    public boolean addDisc(Disc disc) {

        return false;
    }

    @Override
    public boolean deleteDisc(int id) {

        return false;
    }


    @Override
    public boolean deleteAllDiscOfArtist(int id) {
        return false;
    }

    @Override
    public boolean updateDisc(Disc disc) {
        return false;
    }

    @Override
    public List<Disc> selectAllDisc() {
        return null;
    }

    @Override
    public List<Disc> selectDiscByName(String name) {
        return null;
    }

    @Override
    public List<Disc> selectDiscByArtist(int id) {
        return null;
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
    public boolean addSong(Song song) {
        return false;
    }

    @Override
    public boolean deleteSong(int id) {
        return false;
    }

    @Override
    public boolean deleteAllSongOfDisc(int id) {
        return false;
    }

    @Override
    public boolean updateSong(Song song) {
        return false;
    }

    @Override
    public List<Song> selectAllSong() {
        return null;
    }

    @Override
    public List<Song> selectSongByName(String name) {
        return null;
    }

    @Override
    public List<Song> selectAllSongOfArtist(String name) {
        return null;
    }

    @Override
    public List<Song> selectAllSongByDisc(int id) {
        return null;
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
