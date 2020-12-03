package com.proyecto.acdat.controller;

import com.proyecto.acdat.model.*;

import java.util.List;

public class AppController implements IAppController {

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
    public boolean addPlayList(PlayList playList) {
        return false;
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
        return false;
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
    public PlayList selectPlayListById(int id) {
        return null;
    }

    @Override
    public List<User> selectSubOfPlaylist(int id) {
        return null;
    }

    @Override
    public List<PlayList> selectAllPlayList() {
        return null;
    }

    @Override
    public List<PlayList> selectPlayListByName(String name) {
        return null;
    }

    @Override
    public List<PlayList> selectPlayListByEmail(User user) {
        return null;
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
