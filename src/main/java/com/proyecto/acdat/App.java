package com.proyecto.acdat;

import com.proyecto.acdat.model.PlayList;
import com.proyecto.acdat.model.PlayListDAO;
import com.proyecto.acdat.model.User;
import com.proyecto.acdat.model.UserDAO;


public class App {
    public static void main(String[] args) {

        UserDAO udao = new UserDAO();
        PlayListDAO pdao = new PlayListDAO();

        User u = new User("1", "1", "1.png");
        PlayList p = new PlayList("CACA2", "AMBARAJASAA");
        p.setCreator(u);


        pdao.addplaylist(p);
        udao.addUser(u);

        System.out.println(u.getPlayLists());
        System.out.println(p.getCreator());


/**
 EntityManager manager = Connection.getManager();
 manager.getTransaction().begin();
 PlayList playlist = manager.find(PlayList.class,4);
 User user = manager.find(User.class,3);





 manager.getTransaction().commit();


 System.out.println("LAS PLAYLIST DEL TIO SON " + user.getPlayLists());

 System.out.println("LAS PLAYLIST DEL TIO  DE ANTES SON " + u.getPlayLists());


 System.out.println("EL CREADOR ES  " + playlist.getCreator());
 **/


    }
}
