package com.proyecto.acdat.controller;

import com.proyecto.acdat.model.*;

import java.sql.Timestamp;
import java.util.List;

public class AppController implements IAppController{

    @Override
    public boolean addArtist(Artist artist) {
        return false;
    }

    @Override
    public boolean deleteArtist(int id) {
        return false;
    }

    @Override
    public boolean updateArtist(Artist artist) {
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
    public Artist selectArtistByNationality(String nationality) {
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
    public boolean updateDisc(Disc disc) {
        return false;
    }

    @Override
    public List<Disc> selectAllDisc() {
        return null;
    }

    @Override
    public Disc selectDiscByName(String name) {
        return null;
    }

    @Override
    public Disc selectByArtist(Artist artist) {
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
    public boolean deleteSongToPlayList(Song song, int id) {
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
    public List<PlayList> selectAllPlayList() {
        return null;
    }

    @Override
    public List<PlayList> selectPlayListByName(String name) {
        return null;
    }

    @Override
    public List<PlayList> selectPlayListByUserName(String username) {
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
    public List<Song> selectSongByGenre(String genre) {
        return null;
    }

    @Override
    public List<Song> selectAllSongByDisc(String disc_name) {
        return null;
    }

    @Override
    public boolean addUser(User user) {
        return false;
    }

    @Override
    public boolean addHistory(Song song, int id_user, Timestamp timestamp) {
        return false;
    }

    @Override
    public boolean deleteUser(String email) {
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
    public List<User> selectByEmail(String email) {
        return null;
    }

    @Override
    public List<User> selectByName(String name) {
        return null;
    }
}
