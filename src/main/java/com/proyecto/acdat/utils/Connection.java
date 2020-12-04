package com.proyecto.acdat.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Connection {
    private static EntityManagerFactory emf;

    public static EntityManagerFactory getEmf() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("proyectojpa");
        }
        return emf;
    }

    public static EntityManager getManager() {
        return getEmf().createEntityManager();
    }
}
