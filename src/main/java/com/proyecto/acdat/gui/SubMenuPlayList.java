package com.proyecto.acdat.gui;

import com.proyecto.acdat.instance.MyInstance;
import com.proyecto.acdat.model.PlayList;
import com.proyecto.acdat.model.Song;
import com.proyecto.acdat.model.User;
import com.proyecto.acdat.model.UserDAO;
import com.proyecto.acdat.utils.Utilities;

import java.util.List;

public class SubMenuPlayList {

    static UserDAO userDAO = new UserDAO();

    public static void playlist() {
        int opction = 0;
        do {
            Utilities.P("----PlayList----");
            Utilities.P("1) Añadir PlayList");
            Utilities.P("2) Borrar PlayList");
            Utilities.P("3) Actualizar PlayList");
            Utilities.P("4) Listar PlayList por email");
            Utilities.P("5) Listar todas las Playlist");
            Utilities.P("6) Añadir usuario a PlayList");
            Utilities.P("7) Borrar usuario de PlayList");
            Utilities.P("8) Añadir canción en PlayList");
            Utilities.P("9) Borrar canción de PlayList");
            Utilities.P("10) Listar suscriptores de Playlist");
            Utilities.P("11) Volver atrás");
            opction = Utilities.getInt();

            switch (opction) {
                case 1:
                    addPlayList();
                    break;
                case 2:
                    deletePlayList();
                    break;
                case 3:
                    updatePlayList();
                    break;
                case 4:
                    selectPlayListByEmail();
                    break;
                case 5:
                    selectAllPlaylist();
                    break;
                case 6:
                    addSubToPlayList();
                    break;
                case 7:
                    deleteSubOfPlayList();
                    break;
                case 8:
                    addSongToPlayList();
                    break;
                case 9:
                    deleteSongOfPlaylist();
                    break;
                case 10:
                    selectSubOfPlaylist();
                    break;
                case 11:
                    Menu.start();
                    break;
                default:
                    Utilities.P("Por favor, introduce una opción válida");
                    break;
            }
        } while (opction != 11);
    }

    private static void addPlayList() {
        Utilities.P("----Añadir PlayList----");
        String name = Utilities.getString("Introduce el nombre del la PlayList");
        String description = Utilities.getString("Introduce la descripción de la PlayList");
        String email = Utilities.getString("Introduce el correo electrónico del creador de la PlayList");
        User user = MyInstance.getInstance().selectUserByEmail(email, false);
        PlayList playList = new PlayList(name, description);
        if (MyInstance.getInstance().addPlayList(playList, user)) {
            Utilities.P("PlayList creada con éxito");
        } else {
            Utilities.P("No se ha podido crear la PlayList");
        }
    }

    public static void deletePlayList() {
        int option = 0;
        do {
            Utilities.P("----Borrar PlayList----");
            Utilities.P("1) Listar PlayLists antes de borrar");
            Utilities.P("2) Borrar PlayLists");
            Utilities.P("3) Volver atrás");
            option = Utilities.getInt();
            switch (option) {
                case 1:
                    List<PlayList> playlists = MyInstance.getInstance().selectAllPlayList();
                    if (playlists == null) {
                        Utilities.P("No hay ninguna PlayList");
                    } else {
                        for (PlayList p : playlists) {
                            Utilities.P(p.toString());
                        }
                    }
                    break;
                case 2:
                    String name = Utilities.getString("Introduce el nombre de la PlayList que deseas borrar");
                    List<PlayList> playlistbyname = MyInstance.getInstance().selectPlayListByName(name);
                    if (playlistbyname == null) {
                        Utilities.P("La PlayList introducida no existe, comprueba el nombre");
                    } else {
                        for (PlayList pl : playlistbyname) {
                            Utilities.P(pl.toString());
                        }
                        int id = Utilities.getInt("Por favor, introduce la id de la PlayList que deseas borrar");
                        PlayList toDelete = MyInstance.getInstance().selectPlayListById(id);
                        if (MyInstance.getInstance().deletePlayList(toDelete)) {
                            Utilities.P("PlayList borrada con éxito");
                        } else {
                            Utilities.P("No se ha podido borrar la PlayList seleccionada");
                        }
                        break;
                    }
                case 3:
                    playlist();
                    break;
                default:
                    Utilities.P("Introduzca una opción válida");
            }
        } while (option != 3);
    }

    public static void updatePlayList() {
        Utilities.P("----Actualizar PlayList----");
        String oldname = Utilities.getString("Introduce el nombre de la PlayList");
        List<PlayList> playlist = MyInstance.getInstance().selectPlayListByName(oldname);
        PlayList oldplaylist = null;
        if (playlist == null) {
            Utilities.P("La PlayList introducida no existe,comprueba el nombre");
        } else {
            for (PlayList pl : playlist) {
                Utilities.P(pl.toString());
            }
        }
        int id = Utilities.getInt("Por favor,introduce la id de la PlayList que deseas actualizar");
        for (PlayList p : playlist) {
            if (p.getId() == id) {
                oldplaylist = p;
            }
        }
        if (oldplaylist == null) {
            Utilities.P("No existe una PlayList con esa id");
        } else {
            String name = Utilities.getString("Introduce el nuevo nombre de la PLayList");
            String description = Utilities.getString("Introduce la nueva descripción de la PlayList");
            PlayList newPlayList = new PlayList(oldplaylist.getId(), name, description);

            if (oldplaylist.equals(newPlayList)) {
                Utilities.P("No puede ser igual");
            } else {
                if (MyInstance.getInstance().updatePlayList(newPlayList)) {
                    Utilities.P("Se ha actualizado con éxito");
                } else {
                    Utilities.P("No se ha podido actualizar");
                }
            }
        }

    }

    public static void selectAllPlaylist() {
        List<PlayList> playLists = MyInstance.getInstance().selectAllPlayList();
        if (!playLists.isEmpty()) {
            for (PlayList p : playLists) {
                Utilities.P(p.toString() + "| Creador: " + p.getCreator() + "| Suscriptores: " + p.getSubscribers() + "| Canciones: " + p.getSongs());
            }
        }
    }

    public static void selectSubOfPlaylist() {
        String name = Utilities.getString("Introduce el nombre de la PlayList");
        List<PlayList> playlistbyname = MyInstance.getInstance().selectPlayListByName(name);
        PlayList playList;
        if (playlistbyname == null) {
            Utilities.P("La PlayList introducida no existe, comprueba el nombre");
        } else {
            for (PlayList pl : playlistbyname) {
                Utilities.P(pl.toString());
            }
            int id = Utilities.getInt("Por favor, introduce la id de la PlayList para ver sus suscriptores");
            playList = MyInstance.getInstance().selectPlayListById(id);
            if (playList == null) {
                Utilities.P("La PlayList introducida no existe");
            } else {
                List<User> users = MyInstance.getInstance().selectSubOfPlaylist(id);
                if (users == null || users.isEmpty()) {
                    Utilities.P("No tiene suscriptores");
                } else {
                    for (User u : users) {
                        Utilities.P(u.toString());
                   }
                }
            }
        }

    }

    public static void selectPlayListByEmail() {
        String email = Utilities.getString("Introduce el email para buscar sus listas de reproducción");
        User user = MyInstance.getInstance().selectUserByEmail(email, false);
        if (user == null) {
            Utilities.P("No existe ningún usuario con ese email");
        } else {
            List<PlayList> playList = MyInstance.getInstance().selectPlayListByUser(user);
            if (playList.isEmpty()) {
                Utilities.P("No hay listas de ese usuario");
            } else {
                for (PlayList p : playList) {
                    Utilities.P(p.toString() + "| Creador: " + p.getCreator() + "| Suscriptores: " + p.getSubscribers() + "| Canciones: " + p.getSongs());
                }
            }
        }
    }

    public static void addSongToPlayList() {
        Song song = null;
        String songName = Utilities.getString("Introduce el nombre de la canción a añadir");
        List<Song> songs = MyInstance.getInstance().selectSongByName(songName);
        if (songs.isEmpty()) {
            Utilities.P("No existe ninguna canción con ese nombre");
        } else {
            for (Song s : songs) {
                Utilities.P(s.toString());
            }
            List<PlayList> playLists = MyInstance.getInstance().selectAllPlayList();
            if (!playLists.isEmpty()) {
                for (PlayList p : playLists) {
                    Utilities.P(p.toString() + " Canciones: " + p.getSongs());
                }
            }
            int id = Utilities.getInt("Introduce la id de la canción a añadir");
            int id_playlist = Utilities.getInt("Introduce la id de la playlist deseada: ");
            for (Song s : songs) {
                if (id == s.getId()) {
                    song = s;
                }
            }
            if (song == null) {
                Utilities.P("La canción no existe");
            } else {
                System.out.println(song.getId() + " " + id_playlist);
                if (MyInstance.getInstance().addSongToPlayList(song, id_playlist)) {
                    Utilities.P("Canción añadida con éxito");
                } else {
                    Utilities.P("No se ha podido añadir la canción");
                }
            }
        }

    }

    public static void deleteSongOfPlaylist() {
        String playlistName = Utilities.getString("Introduce el nombre de la Playlist: ");
        List<PlayList> playLists = MyInstance.getInstance().selectPlayListByName(playlistName);
        for (PlayList p : playLists) {
            Utilities.P(p.toString() + " Canciones " + p.getSongs());
        }
        int idPlaylist = Utilities.getInt("Introduce el id de la Playlist que deseas");
        int idSong = Utilities.getInt("Introduce el id de la canción que deseas borrar");

        if (MyInstance.getInstance().deleteSongOfPlayList(idSong, idPlaylist)) {
            Utilities.P("Canción borrada con éxito");
        } else {
            Utilities.P("No se ha podido borrar la canción");
        }
    }

    public static void addSubToPlayList() {
        String playlistName = Utilities.getString("Introduce el nombre de la Playlist: ");
        List<PlayList> playLists = MyInstance.getInstance().selectPlayListByName(playlistName);
        for (PlayList p : playLists) {
            Utilities.P(p.toString());
        }
        int idPlaylist = Utilities.getInt("Introduce el id de la Playlist que deseas");
        String email = Utilities.getString("Introduce el email del usuario que deseas suscribir a la Playlist");
        User user = MyInstance.getInstance().selectUserByEmail(email, false);
        if (MyInstance.getInstance().addSubToPlayList(user, idPlaylist)) {
            Utilities.P("Usuario suscrito con éxito");
        } else {
            Utilities.P("Ha ocurrido un error no se ha podido realizar la suscripción");
        }
    }

    public static void deleteSubOfPlayList() {
        String playlistName = Utilities.getString("Introduce el nombre de la Playlist: ");
        List<PlayList> playLists = MyInstance.getInstance().selectPlayListByName(playlistName);
        for (PlayList p : playLists) {
            Utilities.P(p.toString());
        }
        int idPlaylist = Utilities.getInt("Introduce el id de la Playlist que deseas");
        String email = Utilities.getString("Introduce el email del usuario que deseas borrar de la Playlist");
        User user = MyInstance.getInstance().selectUserByEmail(email, true);
        if (MyInstance.getInstance().deleteSubOfPlayList(user, idPlaylist)) {
            Utilities.P("Usuario borrado con éxito");
        } else {
            Utilities.P("Ha ocurrido un error no se ha podido borrar");
        }
    }

}
