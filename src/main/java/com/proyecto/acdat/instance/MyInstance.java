package com.proyecto.acdat.instance;

import com.proyecto.acdat.controller.AppController;

public class MyInstance {
    private static AppController appController;

    public static AppController getInstance(){

        if(appController == null){
            appController = new AppController();
        }
        return appController;
    }


}
