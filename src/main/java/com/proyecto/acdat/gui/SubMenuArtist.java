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
            Utilities.P("-----------------");
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
                    updateArtist();
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

    private static void addArtist() {
        Utilities.P("---- Añadir Artista ----");
        String name = Utilities.getString("Introduce el nombre del artista: ");
        String nationality = Utilities.getString("Introduce la nacionalidad: ");
        String photo = Utilities.getString("Introduce la foto: ");
        if (MyInstance.getInstance().addArtist(new Artist(name, nationality, photo))) {
            Utilities.P("Artista creado con éxito");
        } else {
            Utilities.P("No se ha podido crear el artista");
        }
    }

    private static void deleteArtist() {
        int option = 0;
        do {
            Utilities.P("---- Borrar Artista ----");
            Utilities.P("1) Listar todos los artistas antes de borrar");
            Utilities.P("2) Borrar artista por nombre");
            Utilities.P("3) Volver atrás");
            Utilities.P("------------------------");
            option = Utilities.getInt();
            switch (option) {
                case 1:
                    List<Artist> artists = MyInstance.getInstance().selectAllArtist();
                    if (artists == null) {
                        Utilities.P("No hay ningún artista creado");
                    } else {
                        for (Artist a : artists) {
                            Utilities.P(a.toString());
                        }
                    }
                    break;
                case 2:
                    String name = Utilities.getString("Introduce el nombre del artista que deseas borrar: ");
                    Artist artist = MyInstance.getInstance().selectArtistByName(name);
                    if (artist == null) {
                        Utilities.P("El artista introducido no existe, comprueba el nombre");
                    } else {
                        if (MyInstance.getInstance().deleteArtist(name)) {
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
                    Utilities.P("Por favor, introduce una opción válida");
                    break;
            }
        } while (option != 3);
    }

    private static void listArtist() {
        int option = 0;
        String name, nationality;
        Artist artist;
        List<Artist> artists;

        do {
            Utilities.P("---- Listar Artista ----");
            Utilities.P("1) Listar todos los artistas (con discos)");
            Utilities.P("2) Listar todos los artistas (sin discos)");
            Utilities.P("3) Listar artista por nombre (con discos)");
            Utilities.P("4) Listar artista por nombre (sin discos)");
            Utilities.P("5) Listar artista por nacionalidad (con discos)");
            Utilities.P("6) Listar artista por nacionalidad (sin discos)");
            Utilities.P("7) Volver atrás");
            Utilities.P("------------------------");
            option = Utilities.getInt();
            switch (option) {
                case 1:
                    artists = MyInstance.getInstance().selectAllArtist();
                    if (artists.isEmpty()) {
                        Utilities.P("No hay ningún artista creado");
                    } else {
                        for (Artist a : artists) {
                            Utilities.P(a.toString() + " | " + a.getDisc());
                        }
                    }
                    break;
                case 2:
                    artists = MyInstance.getInstance().selectAllArtist();
                    if (artists.isEmpty()) {
                        Utilities.P("No hay ningún artista creado");
                    } else {
                        for (Artist a : artists) {
                            Utilities.P(a.toString());
                        }
                    }
                    break;
                case 3:
                    name = Utilities.getString("Introduce el nombre del artista: ");
                    artist = MyInstance.getInstance().selectArtistByName(name);
                    Utilities.P(artist.toString() + " | " + artist.getDisc());
                    break;
                case 4:
                    name = Utilities.getString("Introduce el nombre del artista: ");
                    artist = MyInstance.getInstance().selectArtistByName(name);
                    Utilities.P(artist.toString());
                    break;
                case 5:
                    nationality = Utilities.getString("Introduce la nacionalidad");
                    artists = MyInstance.getInstance().selectArtistByNationality(nationality);
                    if (artists.isEmpty()) {
                        Utilities.P("No hay ningún artista con esa nacionalidad");
                    } else {
                        for (Artist a : artists) {
                            Utilities.P(a.toString() + " | " + a.getDisc());
                        }
                    }
                    break;
                case 6:
                    nationality = Utilities.getString("Introduce la nacionalidad");
                    artists = MyInstance.getInstance().selectArtistByNationality(nationality);
                    if (artists.isEmpty()) {
                        Utilities.P("No hay ningún artista con esa nacionalidad");
                    } else {
                        for (Artist a : artists) {
                            Utilities.P(a.toString());
                        }
                    }
                    break;
                case 7:
                    artist();
                    break;
                default:
                    Utilities.P("Por favor, introduce una opción válida");
                    break;
            }
        } while (option != 3);
    }

    private static void updateArtist() {
        Utilities.P("---- Actualizar Artista ----");
        String oldname = Utilities.getString("Introduce el nombre del artista: ");
        Artist oldArtist = MyInstance.getInstance().selectArtistByName(oldname);

        if (oldArtist == null) {
            Utilities.P("No hay ningún artista con ese nombre");
        } else {
            String name = Utilities.getString("Introduce el nuevo nombre del artista: ");
            String nationality = Utilities.getString("Introduce la nueva nacionalidad: ");
            String photo = Utilities.getString("Introduce la nueva foto: ");
            Artist newArtist = new Artist(name, nationality, photo);

            if (oldArtist.equals(newArtist)) {
                Utilities.P("No puede ser igual");
            } else {
                if (MyInstance.getInstance().updateArtist(newArtist, oldArtist.getId())) {
                    Utilities.P("Se ha actualizado con éxito");
                } else {
                    Utilities.P("No se ha podido actualizar");
                }
            }

        }


    }


}

