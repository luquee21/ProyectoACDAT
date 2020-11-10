package com.proyecto.acdat.controller;

import com.proyecto.acdat.model.*;

import java.util.List;

public interface IAppController {
    //comenta, reproduce, genero se borran

    //Artist
     boolean addArtist(Artist artist);
     boolean deleteArtist(String name);
     boolean updateArtist(Artist artist, int id);
     List<Artist> selectAllArtist();
     Artist selectArtistByName(String name);

 Artist selectArtistById(int id);

 List<Artist> selectArtistByNationality(String nationality);

     //Disc
     boolean addDisc(Disc disc);
     boolean deleteDisc(int id);
     boolean deleteAllDiscOfArtist(int id);
     boolean updateDisc(Disc disc);
     List<Disc> selectAllDisc();
     List<Disc> selectDiscByName(String name);
     List<Disc> selectDiscByArtist(int id);

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
     List<PlayList> selectPlayListByEmail(User user);

     //Song
     boolean addSong(Song song);
     boolean deleteSong(int id);
     boolean deleteAllSongOfDisc(int id);
     boolean updateSong(Song song);
     List<Song> selectAllSong();
     List<Song> selectSongByName(String name);
     List<Song> selectAllSongOfArtist(String name);
     List<Song> selectAllSongByDisc(int id);

     //User
     boolean addUser(User user);
     boolean deleteUser(String email);

    boolean deleteAllPlayListOfUser(int id_user);

    boolean updateUser(User user);
     List<User> selectAllUser();
     User selectUserByEmail(String email);
     List<User> selectUserByName(String name);
 



}
