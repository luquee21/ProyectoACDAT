package com.proyecto.acdat.gui;

import com.proyecto.acdat.instance.MyInstance;
import com.proyecto.acdat.model.Artist;
import com.proyecto.acdat.utils.Utilities;

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

                    break;
                case 3:

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
        String name = Utilities.getString("Introduce el nombre del artista");
        String nationality = Utilities.getString("Introduce la nacionalidad");
        String photo = Utilities.getString("Introduce la foto");
        Artist
        MyInstance.getInstance().addArtist()


    }

}

