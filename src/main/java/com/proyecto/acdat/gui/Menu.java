package com.proyecto.acdat.gui;

import com.proyecto.acdat.utils.Connection;
import com.proyecto.acdat.utils.Utilities;

public class Menu {

    public static void start(){
        int option = 0;
        do{
            Connection.getEmf();
            Utilities.P("----Bienvenido a ----");
            Utilities.P("1) Menu Artista");
            Utilities.P("2) Menu Disco");
            Utilities.P("3) Menu Lista");
            Utilities.P("4) Menu Canción");
            Utilities.P("5) Menu Usuario");
            Utilities.P("6) Salir");
            option = Utilities.getInt();

            switch(option){
                case 1:
                    SubMenuArtist.artist();
                    break;
                case 2:
                    SubMenuDisc.disc();
                    break;
                case 3:
                    SubMenuPlayList.playlist();
                    break;
                case 4:
                    SubMenuSong.song();
                    break;
                case 5:
                    SubMenuUser.user();
                    break;
                case 6:
                    Utilities.P("Adios");
                    break;
                default:
                    Utilities.P("Introduce una opción correcta");
                    break;
            }
        }while(option!=6);
    }
}
