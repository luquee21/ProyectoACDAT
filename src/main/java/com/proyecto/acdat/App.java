package com.proyecto.acdat;

import com.proyecto.acdat.utils.Connection;

import javax.persistence.EntityManager;

public class App {
    public static void main(String[] args) {
        EntityManager et = Connection.getEmf().createEntityManager();

        et.getTransaction().begin();

        et.getTransaction().commit();


    }
}
