package com.proyecto.acdat.gui;

import com.proyecto.acdat.instance.MyInstance;
import com.proyecto.acdat.model.Disc;
import com.proyecto.acdat.utils.Utilities;

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

    public static boolean addDsic(){
        String name = Utilities.getString("Inserte el nombre del disco");
        String photo = Utilities.getString("Introduce la foto");
        MyInstance.getInstance().addDisc(new Disc(name, photo));

    }

}
