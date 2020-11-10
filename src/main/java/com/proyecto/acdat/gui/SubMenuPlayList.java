package com.proyecto.acdat.gui;

import com.proyecto.acdat.instance.MyInstance;
import com.proyecto.acdat.model.PlayList;
import com.proyecto.acdat.model.Song;
import com.proyecto.acdat.model.User;
import com.proyecto.acdat.utils.Utilities;

import java.util.List;

public class SubMenuPlayList {

    public static void playlist(){
        int opction = 0;
        do {
            Utilities.P("----PlayList----");
            Utilities.P("1) Añadir PlayList");
            Utilities.P("2) Borrar PlayList");
            Utilities.P("3) Actualizar PlayList");
            Utilities.P("4) Listar PlayList por email");
            Utilities.P("5) Añadir usuario a PlayList");
            Utilities.P("6) Borrar usuario de PlayList");
            Utilities.P("7) Añadir canción en PlayList");
            Utilities.P("8) Borrar canción de PlayList");
            Utilities.P("9) Volver atrás");
            opction=Utilities.getInt();

            switch (opction){
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
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                default:
                    Utilities.P("Por favor, introduce una opción válida");
                    break;
            }
        }while (opction!=5);
    }

    private static void addPlayList(){
        Utilities.P("----Añadir PlayList----");
        String name = Utilities.getString("Introduce el nombre del la PlayList");
        String description = Utilities.getString("Introduce la descripción de la PlayList");
        String email = Utilities.getString("Introduce el correo electrónico del creador de la PlayList");
        User user = MyInstance.getInstance().selectUserByEmail(email);
        if(MyInstance.getInstance().addPlayList(new PlayList(name,description,user))){
            Utilities.P("PlayList creada con éxito");
        }else {
            Utilities.P("No se ha podido crear la PlayList");
        }
    }

    public static void deletePlayList(){
        int option=0;
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
                        if (MyInstance.getInstance().deletePlayList(id)) {
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
        }while (option != 3) ;
    }

    public static void updatePlayList(){
        Utilities.P("----Actualizar PlayList----");
        String oldname = Utilities.getString("Introduce el nombre de la PlayList");
        List<PlayList> playlist = MyInstance.getInstance().selectPlayListByName(oldname);
        PlayList oldplaylist = null;
        if (playlist == null){
            Utilities.P("La PlayList introducida no existe,comprueba el nombre");
        }else{
            for (PlayList pl : playlist){
                Utilities.P(pl.toString());
            }
        }
        int id = Utilities.getInt("Por favor,introduce la id de la PlayList que deseas borrar");
        for (PlayList p : playlist){
            if (p.getId() == id){
                oldplaylist = p;
            }
        }
        if (oldplaylist == null){
            Utilities.P("No existe una PlayList con esa id");
        }else{
            String name = Utilities.getString("Introduce el nuevo nombre de la PLayList");
            String description = Utilities.getString("Introduce la nueva descripción de la PlayList");
            PlayList newPlayList = new PlayList(name,description);

            if (oldplaylist.equals(newPlayList)){
                Utilities.P("No puede ser igual");
            }else {
                if (MyInstance.getInstance().addPlayList(newPlayList)){
                    Utilities.P("Se ha actualizado con éxito");
                }else{
                    Utilities.P("No se ha podido actualizar");
                }
            }
        }

    }

    public static void selectPlayListByEmail(){
        String email = Utilities.getString("Introduce el email para buscar sus listas de reproducción");
        User user = MyInstance.getInstance().selectUserByEmail(email);
        if(user == null){
            Utilities.P("No existe ningún usuario con ese email");
        }else{
            List<PlayList> playList = MyInstance.getInstance().selectPlayListByEmail(user);
            if(playList.isEmpty()){
                Utilities.P("No hay listas de ese usuario");
            }else{
                for(PlayList p : playList){
                    Utilities.P(p.toString());
                }
            }
        }
    }

    public static void addSongToPlayList(){

    }

}
