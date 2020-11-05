package com.proyecto.acdat.controller;

import com.proyecto.acdat.model.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public interface IAppController {
    //comenta, reproduce, genero se borran

    //Artist
     boolean addArtist(Artist artist);
     boolean deleteArtist(int id);
     boolean updateArtist(Artist artist);
     List<Artist> selectAllArtist();
     Artist selectArtistByName(String name);
     Artist selectArtistByNationality(String nationality);

     //Disc
     boolean addDisc(Disc disc);
     boolean deleteDisc(int id);
     boolean updateDisc(Disc disc);
     List<Disc> selectAllDisc();
     Disc selectDiscByName(String name);
     Disc selectByArtist(Artist artist);

     //PlayList
     boolean addPlayList(PlayList playList);
     boolean addSongToPlayList(Song song, int id);
     boolean addSubToPlayList(User user, int id);
     boolean deleteSongToPlayList(Song song, int id);
     boolean deleteSubOfPlayList(User user, int id);
     boolean deletePlayList(int id);
     boolean updatePlayList(PlayList playList);
     List<PlayList> selectAllPlayList();
     List<PlayList> selectPlayListByName(String name);
     List<PlayList> selectPlayListByUserName(String username);

     //Song
     boolean addSong(Song song);
     boolean deleteSong(int id);
     boolean updateSong(Song song);
     List<Song> selectAllSong();
     List<Song> selectSongByName(String name);
     List<Song> selectSongByGenre(String genre);
     List<Song> selectAllSongByDisc(String disc_name);

     //User
     boolean addUser(User user);
     boolean addHistory(Song song, int id_user, Timestamp timestamp);
     boolean deleteUser(String email);
     boolean updateUser(User user);
     List<User> selectAllUser();
     List<User> selectByEmail(String email);
     List<User> selectByName(String name);
 



}