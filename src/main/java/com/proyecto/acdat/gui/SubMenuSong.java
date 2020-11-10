package com.proyecto.acdat.gui;

import com.proyecto.acdat.instance.MyInstance;
import com.proyecto.acdat.model.Artist;
import com.proyecto.acdat.model.Disc;
import com.proyecto.acdat.model.Song;
import com.proyecto.acdat.utils.Utilities;

import java.util.List;

public class SubMenuSong {
    public static void song() {
        int option = 0;
        do {
            Utilities.P("---- Canción ----");
            Utilities.P("1) Añadir canción");
            Utilities.P("2) Borrar canción");
            Utilities.P("3) Listar canción");
            Utilities.P("4) Actualizar canción");
            Utilities.P("5) Volver atrás");
            Utilities.P("----------------");
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

    private static void addSong() {
        Utilities.P("---- Añadir Canción ----");
        String name = Utilities.getString("Inserte el nombre de la canción");
        int duration = Utilities.getInt("Introduce la duración");
        name = Utilities.getString("Introduce el nombre del disco al que pertenece");
        List<Disc> discs = MyInstance.getInstance().selectDiscByName(name);

        if(discs.isEmpty()){
            Utilities.P("No existe el disco");
        } else {
            for(Disc d : discs) {
                Utilities.P(d.toString());
            }
            int id = Utilities.getInt("Introduce el id del disco al que pertenece");
            if (MyInstance.getInstance().addSong(new Song(name, duration,id))) {
                Utilities.P("Canción creada con éxito");
            } else {
                Utilities.P("No se ha podido crear la canción");
            }
        }

    }

    private static void deleteSong() {
        int option = 0;
        List<Song> songs;
        List<Disc> disc;
        do {
            Utilities.P("---- Borrar Canción ----");
            Utilities.P("1) Listar todas las canciones antes de borrar");
            Utilities.P("2) Borrar canción");
            Utilities.P("3) Borrar todas las canciones de un disco");
            Utilities.P("4) Volver atrás");
            Utilities.P("-----------------------");
            option = Utilities.getInt();
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
                    String aux = Utilities.getString("Introduce el nombre del disco");
                    disc = MyInstance.getInstance().selectDiscByName(aux);
                    if(disc.isEmpty()){
                        Utilities.P("No hay ningún disco con ese nombre");
                    }else {
                        for(Disc d: disc){
                            Utilities.P(d.toString());
                        }
                    int id = Utilities.getInt("Introduce el id del disco");
                        if(MyInstance.getInstance().deleteAllSongOfDisc(id)){
                            Utilities.P("Se han borrado todas las canciones del disco");
                        } else {
                            Utilities.P("No se han borrado las canciones");
                        }
                    }
                    break;
                case 4:
                    song();
                    break;
                default:
                    Utilities.P("Introduzca una opción válida");
                    break;
            }
        } while (option != 4);
    }

    private static void listSong() {
        int option = 0;
        List<Song> songs;
        do {
            Utilities.P("---- Listar Canciones ----");
            Utilities.P("1) Listar todas las canciones");
            Utilities.P("2) Listar canciones por nombre");
            Utilities.P("3) Listar canciones por disco");
            Utilities.P("4) Listar canciones de un artista");
            Utilities.P("5) Volver atrás");
            Utilities.P("------------------------");
            option = Utilities.getInt();
            switch (option) {
                case 1:
                    songs = MyInstance.getInstance().selectAllSong();
                    if (songs == null) {
                        Utilities.P("No hay ningún disco creado");
                    } else {
                        for (Song s : songs) {
                            Utilities.P(s.toString());
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
                    for (Disc d : discs) {
                        Utilities.P(d.toString());
                    }
                    if(!discs.isEmpty()){
                        int id = Utilities.getInt("Introduce el id del disco deseado");
                        List<Song> aux = MyInstance.getInstance().selectAllSongByDisc(id);
                        if(aux!=null){

                            for(Song s: aux){
                                Utilities.P(s.toString());
                            }
                        }
                    } else {
                        Utilities.P("No hay ningun disco con ese nombre");
                    }


                    break;
                case 4:
                    songs = MyInstance.getInstance().selectAllSongOfArtist(Utilities.getString("Introduce el nombre del artista"));
                    if (songs == null) {
                        Utilities.P("No hay canciones de ese artista");
                    } else {
                        for (Song s : songs) {
                            Utilities.P(s.toString());
                        }
                    }
                    break;
                case 5:
                    song();
                    break;
            }
        } while (option != 5);
    }

    private static void updateSong() {
        Utilities.P("---- Actualizar Canción ----");
        String oldname = Utilities.getString("Introduce el nombre de la canción: ");
        List<Song> songs = MyInstance.getInstance().selectSongByName(oldname);
        Song oldSong = null;
        if (songs == null) {
            Utilities.P("No hay ningún disco con ese nombre");
        } else {
            for (Song song : songs) {
                Utilities.P(song.toString());
            }
        }
        int id = Utilities.getInt("Por favor, inserte el id de la canción que desea modificar: ");
        for (Song song : songs) {
            if (song.getId() == id) {
                oldSong = song;
            }
        }
        if (oldSong == null) {
            Utilities.P("No hay ninguna canción con ese id");
        } else {
            String name = Utilities.getString("Introduce el nuevo nombre de la canción: ");
            int duration = Utilities.getInt("Introduce la nueva duración: ");
            Song newSong = new Song(name, duration);

            if (oldSong.equals(newSong)) {
                Utilities.P("No puede ser igual");

            } else {
                if (MyInstance.getInstance().updateSong(newSong)) {
                    Utilities.P("Se ha actualizado con éxito");
                } else {
                    Utilities.P("No se ha podido actualizar");
                }
            }

        }
    }
}
