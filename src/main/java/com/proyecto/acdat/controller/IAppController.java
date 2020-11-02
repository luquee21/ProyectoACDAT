package com.proyecto.acdat.controller;

import com.proyecto.acdat.model.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public interface IAppController {

    //Artist
     boolean addArtist(String name, String nationality);
     boolean deleteArtist(int id);
     boolean updateArtist(Artist oldArtist, Artist newArtist);
     List<Artist> selectAllArtist();
     Artist selectArtistByName(String name);
     Artist selectArtistByNationality(String nationality);
     Artist isAvailableArtist(String name);

     //Disc
     boolean addDisc(String name, String artist, Date date);
     boolean deleteDisc(int id);
     boolean updateDisc(Disc oldDisc, Disc newDisc);
     List<Disc> selectAllDisc();
     Disc selectDiscByName(String name);
     Disc selectDiscByDate(Date date);
     Disc isAvailableDisc(String name);

     //Genre
     boolean addGenre(String name);
     boolean deleteGenre(String name);
     boolean updateGenre(Genre oldGenre, Genre newGenre);
     List<Genre> selectAllGenre();
     Genre selectGenreByName(String name);
     Genre isAvailableGenre(String name);

     //PlayList
     boolean addPlayList(String name, String description, String username);
     boolean addSongToPlayList(Song song, int id);
     boolean addSubToPlayList(User user, int id);
     boolean addCommentToPlayList(User user, int id_playlist, String message, Timestamp timestamp);
     boolean deleteCommentToPlayList(int id);
     boolean deleteSongToPlayList(Song song, int id);
     boolean deleteSubOfPlayList(User user, int id);
     boolean deletePlayList(int id);
     boolean updateCommentOfPlayList(int id, String message);
     boolean deleteCommentOfPlayList(int id);
     boolean updatePlayList(PlayList oldPlayList, PlayList newPlayList);
     List<PlayList> selectAllPlayList();
     List<PlayList> selectPlayListByName(String name);
     List<PlayList> selectPlayListByUserName(String username);
     PlayList isAvailablePlayList(String name);

     //Song
     boolean addSong(String name, int duration, String genre, String disc_name);
     boolean deleteSong(int id);
     boolean updateSong(Song oldSong, Song newSong);
     List<Song> selectAllSong();
     List<Song> selectSongByName(String name);
     List<Song> selectSongByGenre(String genre);
     List<Song> selectAllSongByDisc(String disc_name);

     //User
     boolean addUser(String email, String name);
     boolean addHistory(Song song, int id_user, Timestamp timestamp);
     boolean deleteUser(String email);
     boolean updateUser(User oldUser, User newUser);
     List<User> selectAllUser();
     List<User> selectByEmail(String email);
     List<User> selectByName(String name);




}
