package com.proyecto.acdat.gui;

import com.proyecto.acdat.instance.MyInstance;
import com.proyecto.acdat.model.Artist;
import com.proyecto.acdat.model.Disc;
import com.proyecto.acdat.model.Song;
import com.proyecto.acdat.utils.Utilities;

import java.util.List;

public class SubMenuSong {
    public static void disc() {
        int option = 0;
        do {
            Utilities.P("---- Canción ----");
            Utilities.P("1) Añadir canción");
            Utilities.P("2) Borrar canción");
            Utilities.P("3) Listar canción");
            Utilities.P("4) Actualizar canción");
            Utilities.P("5) Volver atrás");
            option = Utilities.getInt();

            switch (option) {
                case 1:
                    addSong();
                    break;
                case 2:
                    deleteSong();
                    break;
                case 3:
                    listSong();
                    break;
                case 4:
                    updateSong();
                    break;
                case 5:
                    Menu.start();
                    break;
                default:
                    Utilities.P("Por favor, introduce una opción válida");
            }
        } while (option != 5);
    }

    public static void addSong() {
        Utilities.P("---- Añadir Canción ----");
        String name = Utilities.getString("Inserte el nombre de la canción");
        int duration = Utilities.getInt("Introduce la duración");
        if (MyInstance.getInstance().addSong(new Song(name, duration))) {
            Utilities.P("Canción creada con éxito");
        } else {
            Utilities.P("No se ha podido crear la canción");
        }
    }

    public static void deleteSong() {
        int option = 0;
        List<Song> songs = null;
        do {
            Utilities.P("---- Borrar Canción ----");
            Utilities.P("1) Listar todas las canciones antes de borrar");
            Utilities.P("2) Borrar canción");
            Utilities.P("3) Volver atrás");

            switch (option) {
                case 1:
                    songs = MyInstance.getInstance().selectAllSong();
                    for (Song song : songs) {
                        Utilities.P(song.toString());
                    }
                    break;
                case 2:
                    String name = Utilities.getString("Introduce el nombre de la canción que deseas borrar");
                    songs = MyInstance.getInstance().selectSongByName(name);
                    if (songs == null) {
                        Utilities.P("La canción introducida no existe, comprueba el nombre");
                    } else {
                        for (Song song : songs) {
                            Utilities.P(song.toString());
                        }
                        int id = Utilities.getInt("Por favor, inserte la id de la canción a borrar");
                        if (MyInstance.getInstance().deleteSong(id)) {
                            Utilities.P("Canción borrada con éxito");
                        } else {
                            Utilities.P("No se ha podido borrar la canción");
                        }
                    }
                    break;
                case 3:
                    disc();
                    break;
                default:
                    Utilities.P("Introduzca una opción válida");
                    break;
            }
        } while (option != 3);
    }

    public static void listSong() {
        int option = 0;
        List<Song> songs = null;
        do {
            Utilities.P("---- Listar Canciones ----");
            Utilities.P("1) Listar todos las canciones");
            Utilities.P("2) Listar canciones por nombre");
            Utilities.P("3) Listar canciones por disco");
            Utilities.P("4) Volver atrás");
            Utilities.P("------------------------");
            switch (option) {
                case 1:
                    songs = MyInstance.getInstance().selectAllSong();
                    if (songs == null) {
                        Utilities.P("No hay ningún disco creado");
                    } else {
                        for (Song song : songs) {
                            Utilities.P(song.toString());
                        }
                    }
                    break;
                case 2:
                    String name = Utilities.getString("Introduce el nombre de la canción");
                    songs = MyInstance.getInstance().selectSongByName(name);
                    if (songs == null) {
                        Utilities.P("No hay ningún disco con ese nombre");
                    } else {
                        for (Song song : songs) {
                            Utilities.P(song.toString());
                        }
                    }
                    break;
                case 3:
                    String discName = Utilities.getString("Introduce el nombre del disco");
                    List<Disc> discs = MyInstance.getInstance().selectDiscByName(discName);
                    Disc choosenDisc = null;
                    int id = Utilities.getInt("Por favor, inserte la id del disco deseado");
                    for(Disc disc : discs){
                        if(disc.getId() == id){
                            choosenDisc = disc;
                        }
                    }
                    if (choosenDisc == null) {
                        Utilities.P("No hay ningún disco de ese artista");
                    } else {
                        for (Song song : choosenDisc.getSongs()) {
                            Utilities.P(song.toString());
                        }
                    }
                    break;
                case 4:
                    disc();
                    break;
            }
        } while (option != 4);
    }

    public static void updateSong(){
        Utilities.P("---- Actualizar Canción ----");
        String oldname = Utilities.getString("Introduce el nombre de la canción: ");
        List<Song> songs = MyInstance.getInstance().selectSongByName(oldname);
        Song oldSong = null;
        if(songs == null){
            Utilities.P("No hay ningún disco con ese nombre");
        } else {
            for (Song song : songs) {
                Utilities.P(song.toString());
            }
        }
        int id = Utilities.getInt("Por favor, inserte el id de la canción que desea modificar: ");
        for(Song song : songs){
            if(song.getId() == id){
                oldSong = song;
            }
        }
        if(oldSong == null){
            Utilities.P("No hay ninguna canción con ese id");
        } else {
            String name = Utilities.getString("Introduce el nuevo nombre de la canción: ");
            int duration = Utilities.getInt("Introduce la nueva duración: ");
            Song newSong = new Song(name, duration);

            if(oldSong.equals(newSong)){
                Utilities.P("No puede ser igual");

            } else {
                if(MyInstance.getInstance().updateSong(newSong)){
                    Utilities.P("Se ha actualizado con éxito");
                } else {
                    Utilities.P("No se ha podido actualizar");
                }
            }

        }
    }
}
