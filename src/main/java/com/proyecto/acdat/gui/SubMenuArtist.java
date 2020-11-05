package com.proyecto.acdat.gui;

import com.proyecto.acdat.instance.MyInstance;
import com.proyecto.acdat.model.Artist;
import com.proyecto.acdat.utils.Utilities;

import java.util.List;

public class SubMenuArtist {


    public static void artist() {
        int option = 0;
        do {
            Utilities.P("---- Artista ----");
            Utilities.P("1) Añadir artista");
            Utilities.P("2) Borrar artista");
            Utilities.P("3) Listar artista");
            Utilities.P("4) Actualizar artista");
            Utilities.P("5) Volver atrás");
            option = Utilities.getInt();

            switch (option) {
                case 1:
                    addArtist();
                    break;
                case 2:
                    deleteArtist();
                    break;
                case 3:
                    listArtist();
                    break;
                case 4:

                    break;
                case 5:
                    Menu.start();
                    break;
                default:
                    Utilities.P("Por favor, introduce una opción válida");
                    break;
            }

        } while (option != 5);
    }

    public static void addArtist() {
        Utilities.P("---- Añadir Artista ----");
        String name = Utilities.getString("Introduce el nombre del artista");
        String nationality = Utilities.getString("Introduce la nacionalidad");
        String photo = Utilities.getString("Introduce la foto");
        if (MyInstance.getInstance().addArtist(new Artist(name, nationality, photo))) {
            Utilities.P("Artista creado con éxito");
        } else {
            Utilities.P("No se ha podido crear el artista");
        }
    }

    public static void deleteArtist() {
        int option = 0;
        do {
            Utilities.P("---- Borrar Artista ----");
            Utilities.P("1) Listar todos los artistas antes de borrar");
            Utilities.P("2) Borrar artista por nombre");
            Utilities.P("3) Volver atrás");

            switch (option) {
                case 1:

                    break;
                case 2:
                    String name = Utilities.getString("Introduce el nombre del artista que deseas borrar");
                    Artist artist = MyInstance.getInstance().selectArtistByName(name);
                    if (artist == null) {
                        Utilities.P("El artista introducido no existe, comprueba el nombre");
                    } else {
                        if (MyInstance.getInstance().deleteArtist(artist.getId())) {
                            Utilities.P("Artista borrado con éxito");
                        } else {
                            Utilities.P("No se ha podido borrar el artista");
                        }
                    }
                    break;
                case 3:
                    artist();
                    break;
                default:
                    Utilities.P("Introduzca una opción válida");
                    break;
            }
        } while (option != 3);
    }

    public static void listArtist() {
        int option = 0;
        List<Artist> artists = null;
        do {
            Utilities.P("---- Borrar Artista ----");
            Utilities.P("1) Listar todos los artistas");
            Utilities.P("2) Listar artista por nombre");
            Utilities.P("3) Listar artista por nacionalidad");
            Utilities.P("4) Volver atrás");
            Utilities.P("------------------------");
            switch (option) {
                case 1:
                    artists = MyInstance.getInstance().selectAllArtist();
                    if (artists == null) {
                        Utilities.P("No hay ningún artista creado");
                    } else {
                        for(Artist a : artists){
                            Utilities.P(a.toString());
                        }
                    }
                    break;
                case 2:
                    String name = Utilities.getString("Introduce el nombre del artista");
                    Artist artist = MyInstance.getInstance().selectArtistByName(name);
                    if(artist== null){
                        Utilities.P("No hay ningún artista con ese nombre");
                    } else {
                        Utilities.P(artist.toString());
                    }
                    break;
                case 3:
                    String nationality = Utilities.getString("Introduce la nacionalidad");
                    artists = MyInstance.getInstance().selectArtistByNationality(nationality);
                    if(artists== null){
                        Utilities.P("No hay ningún artista con esa nacionalidad");
                    } else {
                        for(Artist a : artists){
                            Utilities.P(a.toString());
                        }
                    }
                    break;
                case 4:
                    artist();
                    break;
            }
        } while (option != 3);
    }


}

