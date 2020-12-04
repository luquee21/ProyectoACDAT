package com.proyecto.acdat;

import com.proyecto.acdat.model.User;
import com.proyecto.acdat.model.UserDAO;


public class App {
    public static void main(String[] args) {

        User u = new User("pepe", "123@", "12323");
        UserDAO dao = new UserDAO();
        dao.addUser(u);
    }
}
