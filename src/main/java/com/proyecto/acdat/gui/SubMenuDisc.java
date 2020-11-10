package com.proyecto.acdat.gui;

import com.proyecto.acdat.instance.MyInstance;
import com.proyecto.acdat.model.Artist;
import com.proyecto.acdat.model.Disc;
import com.proyecto.acdat.utils.Utilities;

import java.util.Date;
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
                    addDisc();
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

    public static void addDisc(){
        Utilities.P("---- Añadir Disco ----");
        String name = Utilities.getString("Inserte el nombre del disco");
        String photo = Utilities.getString("Introduce la foto");
        String date = Utilities.getString("Introduce la fecha(AÑO-MES-DIA // EJ: 2020-11-05)");
        String artist = Utilities.getString("Introduce el nombre del artista");
        Artist a = MyInstance.getInstance().selectArtistByName(artist);
        if(a == null){
            Utilities.P("El artista no existe");
        } else {
            if(MyInstance.getInstance().addDisc(new Disc(name, photo, date, a.getId()))){
                Utilities.P("Disco creado con éxito");
            }else{
                Utilities.P("No se ha podido crear el disco");
            }
        }

    }

    public static void deleteDisc() {
        int option = 0;
        do {
            Utilities.P("---- Borrar Disco ----");
            Utilities.P("1) Listar todos los discos antes de borrar");
            Utilities.P("2) Borrar disco");
            Utilities.P("3) Borrar todos los discos de un artista");
            Utilities.P("4) Volver atrás");
            option=Utilities.getInt();
            switch (option) {
                case 1:
                    List<Disc> discs = MyInstance.getInstance().selectAllDisc();
                    for(Disc disc : discs){
                        Utilities.P(disc.toString());
                    }
                    break;
                case 2:
                    String name = Utilities.getString("Introduce el nombre del disco que deseas borrar");
                    List<Disc> discsByName = MyInstance.getInstance().selectDiscByName(name);
                    if (discsByName == null) {
                        Utilities.P("El disco introducido no existe, comprueba el nombre");
                    } else {
                        for(Disc disc : discsByName){
                            Utilities.P(disc.toString());
                        }
                        int id = Utilities.getInt("Por favor, inserte la id del disco a borrar");
                        if (MyInstance.getInstance().deleteDisc(id)) {
                            Utilities.P("Disco borrado con éxito");
                        } else {
                            Utilities.P("No se ha podido borrar el disco");
                        }
                    }
                    break;
                case 3:
                    String artistName = Utilities.getString("Introduce el nombre del artista para borrar todos sus discos");
                    Artist artist = MyInstance.getInstance().selectArtistByName(artistName);
                    if(artist==null){
                        Utilities.P("El artista introducido no existe, compruebe el nombre");
                    }else{
                        if(MyInstance.getInstance().deleteAllDiscOfArtist(artist.getId())){
                            Utilities.P("Discos borrados con exito");
                        }else{
                            Utilities.P("No se han podido borrar los discos");
                        }
                    }
                case 4:
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
            option=Utilities.getInt();

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
                    List<Disc> listDiscs = MyInstance.getInstance().selectDiscByName(name);
                    if(listDiscs== null){
                        Utilities.P("No hay ningún disco con ese nombre");
                    } else {
                        for(Disc a : listDiscs){
                            Utilities.P(a.toString());
                        }
                    }
                    break;
                case 3:
                    String artistName = Utilities.getString("Introduce el nombre del artista");
                    Artist artist = MyInstance.getInstance().selectArtistByName(artistName);
                    discs = MyInstance.getInstance().selectDiscByArtist(artist.getId());
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
        } while (option != 4);
    }

    public static void updateDisc(){
        Utilities.P("---- Actualizar Disco ----");
        String oldname = Utilities.getString("Introduce el nombre del disco: ");
        List<Disc> listDiscs = MyInstance.getInstance().selectDiscByName(oldname);
        Disc oldDisc = null;
        if(listDiscs== null){
            Utilities.P("No hay ningún disco con ese nombre");
        } else {
            for (Disc a : listDiscs) {
                Utilities.P(a.toString());
            }
        }
        int id = Utilities.getInt("Por favor, inserte el id del disco que desea modificar: ");
        for(Disc a : listDiscs){
            if(a.getId() == id){
                oldDisc = a;
            }
        }
        if(oldDisc == null){
            Utilities.P("No hay ningún disco con ese id");
        } else {
            String name = Utilities.getString("Introduce el nuevo nombre del disco: ");
            String photo = Utilities.getString("Introduce la nueva foto: ");
            Disc newDisc = new Disc(name, photo);

            if(oldDisc.equals(newDisc)){
                Utilities.P("No puede ser igual");

            } else {
                if(MyInstance.getInstance().updateDisc(newDisc)){
                    Utilities.P("Se ha actualizado con éxito");
                } else {
                    Utilities.P("No se ha podido actualizar");
                }
            }

        }
    }

}
