package com.proyecto.acdat.controller;

import com.proyecto.acdat.model.*;

import java.sql.Timestamp;
import java.util.List;

public class AppController implements IAppController {

    @Override
    public boolean addArtist(Artist artist) {
        boolean result = false;
        if (ArtistDAO.addArtist(artist)) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean deleteArtist(String name) {
        boolean result = false;
        if (ArtistDAO.deleteArtist(name)) {
            result = true;
        }
        return result;

    }

    @Override
    public boolean updateArtist(Artist artist) {
        boolean result = false;
        if(ArtistDAO.updateArtist(artist)){
            result = true;
        }
        return result;
    }

    @Override
    public List<Artist> selectAllArtist() {
        List<Artist> artists = ArtistDAO.selectAll();
        return artists;
    }

    @Override
    public Artist selectArtistByName(String name) {
        Artist artist = ArtistDAO.selectByName(name);
        return artist;
    }


    @Override
    public List<Artist> selectArtistByNationality(String nationality) {
        List<Artist> artists = ArtistDAO.selectByNationality(nationality);
        return artists;
    }

    @Override
    public boolean addDisc(Disc disc) {
        boolean result = false;
        if (DiscDAO.addDisc(disc)) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean deleteDisc(int id) {
        boolean result = false;
        if (DiscDAO.deleteDisc(id)) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean updateDisc(Disc disc) {
        boolean result = false;
        if(DiscDAO.updateArtist(disc)){
            result = true;
        }
        return result;
    }

    @Override
    public List<Disc> selectAllDisc() {
        List<Disc> discs = DiscDAO.selectAll();
        return discs;
    }

    @Override
    public List<Disc> selectDiscByName(String name) {
        List<Disc> discs = DiscDAO.selectByName(name);
        return discs;
    }

    @Override
    public List<Disc> selectDiscByArtist(Artist artist) {
        List<Disc> discs = DiscDAO.selectByArtist(artist);
        return discs;
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
    public List<Song> selectAllSongByDisc(String disc_name) {
        return null;
    }

    @Override
    public boolean addUser(User user) {
        boolean result = false;
        if(UserDAO.addUser(user)){
            result = true;
        }
        return result;
    }

    @Override
    public boolean addHistory(Song song, int id_user, Timestamp timestamp) {
        return false;
    }

    @Override
    public boolean deleteUser(String email) {
        boolean result = false;
        if(UserDAO.deleteUser(email)){
            result = true;
        }
        return result;
    }

    @Override
    public boolean updateUser(User user) {
        boolean result = false;
        if(UserDAO.updateUser(user)){
            result = true;
        }
        return result;
    }

    @Override
    public List<User> selectAllUser() {
        List<User> users = UserDAO.selectAll();
        return users;
    }

    @Override
    public User selectUserByEmail(String email) {
        User user = UserDAO.selectByEmail(email);
        return user;
    }

    @Override
    public List<User> selectUserByName(String name) {
        List<User> users = UserDAO.selectByName(name);
        return users;
    }
}
