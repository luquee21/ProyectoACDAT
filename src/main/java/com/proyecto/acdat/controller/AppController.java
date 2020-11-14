package com.proyecto.acdat.controller;

import com.proyecto.acdat.model.*;

import java.util.List;

public class AppController implements IAppController {

    @Override
    public boolean addArtist(Artist artist) {
        return ArtistDAO.addArtist(artist);
    }

    @Override
    public boolean deleteArtist(String name) {
        return ArtistDAO.deleteArtist(name);
    }

    @Override
    public boolean updateArtist(Artist artist, int id) {
        return ArtistDAO.updateArtist(artist, id);
    }

    @Override
    public List<Artist> selectAllArtist() {
        return ArtistDAO.selectAll();

    }

    @Override
    public Artist selectArtistByName(String name) {
        return ArtistDAO.selectByName(name);
    }

    @Override
    public Artist selectArtistById(int id) {
        return ArtistDAO.selectById(id);
    }


    @Override
    public List<Artist> selectArtistByNationality(String nationality) {
        return ArtistDAO.selectByNationality(nationality);
    }

    @Override
    public boolean addDisc(Disc disc) {
        return DiscDAO.addDisc(disc);
    }

    @Override
    public boolean deleteDisc(int id) {
        return DiscDAO.deleteDisc(id);
    }


    @Override
    public boolean deleteAllDiscOfArtist(int id) {
        return DiscDAO.deleteAllDiscOfArtist(id);
    }

    @Override
    public boolean updateDisc(Disc disc) {
        return DiscDAO.updateDisc(disc);
    }

    @Override
    public List<Disc> selectAllDisc() {
        return DiscDAO.selectAll();
    }

    @Override
    public List<Disc> selectDiscByName(String name) {
        return DiscDAO.selectByName(name);
    }

    @Override
    public List<Disc> selectDiscByArtist(int id) {
        return DiscDAO.selectByArtist(id);
    }

    @Override
    public boolean addPlayList(PlayList playList) {
        return PlayListDAO.addPlayList(playList);
    }

    @Override
    public boolean addSongToPlayList(Song song, int id) {
        return false;
    }

    @Override
    public boolean addSubToPlayList(User user, int id) {
        return false;
    }

    @Override
    public boolean deleteSongOfPlayList(int idSong, int idPlaylist) {
        return PlayListDAO.deleteSongToPlayList(idSong, idPlaylist);
    }

    @Override
    public boolean deleteSubOfPlayList(User user, int id) {
        return false;
    }

    @Override
    public boolean deletePlayList(int id) {
        return false;
    }

    @Override
    public boolean updatePlayList(PlayList playList) {
        return false;
    }

    @Override
    public List<PlayList> selectAllPlayList() {
        return PlayListDAO.selectAll();
    }

    @Override
    public List<PlayList> selectPlayListByName(String name) {
        return PlayListDAO.selectPlayListByName(name);
    }

    @Override
    public List<PlayList> selectPlayListByEmail(User user) {
        return PlayListDAO.selectPlayListByEmail(user);
    }

    @Override
    public boolean addSong(Song song) {
        return SongDAO.addSong(song);
    }

    @Override
    public boolean deleteSong(int id) {
        return SongDAO.deleteSong(id);
    }

    @Override
    public boolean deleteAllSongOfDisc(int id) {
        return SongDAO.deleteAllSongOfDisc(id);
    }

    @Override
    public boolean updateSong(Song song) {
        return SongDAO.updateSong(song);
    }

    @Override
    public List<Song> selectAllSong() {
        return SongDAO.selectAll();
    }

    @Override
    public List<Song> selectSongByName(String name) {
        return SongDAO.selectByName(name);
    }

    @Override
    public List<Song> selectAllSongOfArtist(String name) {
        return SongDAO.selectAllSongOfArtist(name);
    }

    @Override
    public List<Song> selectAllSongByDisc(int id) {
        return SongDAO.selectAllSongByDisc(id);
    }

    @Override
    public boolean addUser(User user) {
        return UserDAO.addUser(user);
    }

    @Override
    public boolean deleteUser(String email) {
        return UserDAO.deleteUser(email);
    }

    @Override
    public boolean deleteAllPlayListOfUser(int id_user) {
        return UserDAO.deleteAllPlayListOfUser(id_user);
    }

    @Override
    public boolean updateUser(User user) {
        return UserDAO.updateUser(user);
    }

    @Override
    public List<User> selectAllUser() {
        return UserDAO.selectAll();
    }

    @Override
    public User selectUserByEmail(String email) {
        return UserDAO.selectByEmail(email);
    }

    @Override
    public List<User> selectUserByName(String name) {
        return UserDAO.selectByName(name);
    }
}
