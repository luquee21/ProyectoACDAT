package com.proyecto.acdat.controller;

import com.proyecto.acdat.model.*;

import java.util.List;

public interface IAppController {
    //comenta, reproduce, genero se borran

    //Artist
    boolean addArtist(Artist artist);

    boolean deleteArtist(Artist artist);

    boolean updateArtist(Artist artist);

    List<Artist> selectAllArtist();

    Artist selectArtistByName(String name);

    Artist selectArtistById(int id);

    List<Artist> selectArtistByNationality(String nationality);

    //Disc
    boolean addDisc(Disc disc,String artist);

    boolean deleteDisc(int id);

    boolean deleteAllDiscOfArtist(int id);

    boolean updateDisc(Disc disc);

    List<Disc> selectAllDisc();

    List<Disc> selectDiscByName(String name);

    List<Disc> selectDiscByArtist(int id);

    Disc selectDiscById(int id);

    //PlayList
    boolean addPlayList(PlayList playList, User user);

    boolean addSongToPlayList(Song song, int id);

    boolean addSubToPlayList(User user, int id);

    boolean deleteSongOfPlayList(int id_song, int id_Playlist);

    boolean deleteSubOfPlayList(User user, int id);

    boolean deletePlayList(PlayList playList);

    boolean updatePlayList(PlayList playList);

    PlayList selectPlayListById(int id);

    List<User> selectSubOfPlaylist(int id);

    List<PlayList> selectAllPlayList();

    List<PlayList> selectPlayListByName(String name);

    List<PlayList> selectPlayListByUser(User user);

    //Song
    boolean addSong(Song song, Disc disc);

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
