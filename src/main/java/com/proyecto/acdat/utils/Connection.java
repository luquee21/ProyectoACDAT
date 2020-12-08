package com.proyecto.acdat.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Connection {
    private static EntityManagerFactory emf;
    private static String database = "";

    public static String getDatabase() {
        return database;
    }

    public static void setDatabase(String database) {
        Connection.database = database;
    }

    public static EntityManagerFactory getEmf() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory(database);
        }
        return emf;
    }

    public static EntityManager getManager() {
        return getEmf().createEntityManager();
    }
}
