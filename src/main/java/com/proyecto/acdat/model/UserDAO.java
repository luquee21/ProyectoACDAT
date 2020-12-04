package com.proyecto.acdat.model;

import com.proyecto.acdat.utils.Connection;

import javax.persistence.EntityManager;

public class UserDAO extends User {


    private EntityManager manager;
    private static final String SELECTALL = "SELECT * FROM Usuario";
    private static final String SELECTBYNAME = "SELECT * FROM Usuario WHERE nombre=?";
    private static final String SELECTBYEMAIL = "SELECT * FROM Usuario WHERE correo=?";
    private static final String SELECTBYID = "SELECT * FROM Usuario WHERE id=?";
    private static final String INSERTUSER = "INSERT INTO Usuario (correo,nombre,foto) VALUES(?,?,?)";
    private static final String DELETEUSER = "DELETE FROM Usuario WHERE id=?";
    private static final String DELETEALLPLAYLISTOFUSER = "DELETE FROM Lista WHERE id_usuario=?";
    private static final String UPDATEUSER = "UPDATE Usuario SET nombre=?, foto=? WHERE id=?";

    public UserDAO(String name, String email, String photo) {
        super(name, email, photo);
    }

    public UserDAO() {
    }

    public void addUser(User u) {
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            manager.persist(u);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
        manager.close();
    }

    public void deleteUser(User u) {
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            manager.remove(u);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
        manager.close();
    }

    public void updateUser(User u) {
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            manager.merge(u);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
        manager.close();
    }
}
