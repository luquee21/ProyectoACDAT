package com.proyecto.acdat.gui;

import com.proyecto.acdat.instance.MyInstance;
import com.proyecto.acdat.model.Artist;
import com.proyecto.acdat.model.Disc;
import com.proyecto.acdat.utils.Utilities;

import java.util.List;

public class SubMenuDisc {

    public static void disc(){
        int option=0;
        do{
            Utilities.P("---- Disco ----");
            Utilities.P("1) Añadir disco");
            Utilities.P("2) Borrar disco");
            Utilities.P("3) Listar disco");
            Utilities.P("4) Actualizar disco");
            Utilities.P("5) Volver atrás");
            option = Utilities.getInt();

            switch (option){
                case 1:
                    addDsic();
                    break;
                case 2:
                    deleteDisc();
                    break;
                case 3:
                    listDisc();
                    break;
                case 4:
                    updateDisc();
                    break;
                case 5:
                    Menu.start();
                    break;
                default:
                    Utilities.P("Por favor, introduce una opción válida");
            }
        }while(option!=5);
    }

    public static void addDsic(){
        Utilities.P("---- Añadir Disco ----");
        String name = Utilities.getString("Inserte el nombre del disco");
        String photo = Utilities.getString("Introduce la foto");
        if(MyInstance.getInstance().addDisc(new Disc(name, photo))){
            Utilities.P("Disco creado con éxito");
        }else{
            Utilities.P("No se ha podido crear el artista");
        }
    }

    public static void deleteDisc() {
        int option = 0;
        do {
            Utilities.P("---- Borrar Disco ----");
            Utilities.P("1) Listar todos los discos antes de borrar");
            Utilities.P("2) Borrar disco por nombre");
            Utilities.P("3) Volver atrás");

            switch (option) {
                case 1:
                    List<Disc> discs = MyInstance.getInstance().selectAllDisc();
                    for(Disc disc : discs){
                        Utilities.P(disc.toString());
                    }
                    break;
                case 2:
                    String name = Utilities.getString("Introduce el nombre del disco que deseas borrar");
                    Disc disc = MyInstance.getInstance().selectDiscByName(name);
                    if (disc == null) {
                        Utilities.P("El disco introducido no existe, comprueba el nombre");
                    } else {
                        if (MyInstance.getInstance().deleteDisc(disc.getId())) {
                            Utilities.P("Disco borrado con éxito");
                        } else {
                            Utilities.P("No se ha podido borrar el disco");
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

    public static void listDisc() {
        int option = 0;
        List<Disc> discs = null;
        do {
            Utilities.P("---- Listar Discos ----");
            Utilities.P("1) Listar todos los discos");
            Utilities.P("2) Listar discos por nombre");
            Utilities.P("3) Listar discos por artista");
            Utilities.P("4) Volver atrás");
            Utilities.P("------------------------");
            switch (option) {
                case 1:
                    discs = MyInstance.getInstance().selectAllDisc();
                    if (discs == null) {
                        Utilities.P("No hay ningún disco creado");
                    } else {
                        for(Disc disc : discs){
                            Utilities.P(disc.toString());
                        }
                    }
                    break;
                case 2:
                    String name = Utilities.getString("Introduce el nombre del disco");
                    Disc disc = MyInstance.getInstance().selectDiscByName(name);
                    if(disc== null){
                        Utilities.P("No hay ningún disco con ese nombre");
                    } else {
                        Utilities.P(disc.toString());
                    }
                    break;
                case 3:
                    String artistName = Utilities.getString("Introduce el nombre del artista");
                    Artist artist = MyInstance.getInstance().selectArtistByName(artistName);
                    discs = (List<Disc>) MyInstance.getInstance().selectByArtist(artist);
                    if(discs== null){
                        Utilities.P("No hay ningún disco de ese artista");
                    } else {
                        for(Disc a : discs){
                            Utilities.P(a.toString());
                        }
                    }
                    break;
                case 4:
                    disc();
                    break;
            }
        } while (option != 3);
    }

}
