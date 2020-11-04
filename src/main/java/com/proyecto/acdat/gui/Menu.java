package com.proyecto.acdat.gui;

import com.proyecto.acdat.utils.Utilities;

public class Menu {

    public static void start(){
        int option = 0;
        do{
            Utilities.P("----Bienvenido a ----");
            Utilities.P("1) Menu Artista");
            Utilities.P("2) Menu Disco");
            Utilities.P("3) Menu Lista");
            Utilities.P("4) Menu Canción");
            Utilities.P("5) Menu Usuario");
            Utilities.P("6) Salir");
            option = Utilities.getInt();
        }while(option!=6);
    }
}
