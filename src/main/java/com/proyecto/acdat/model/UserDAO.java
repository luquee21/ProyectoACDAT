package com.proyecto.acdat.model;

import com.proyecto.acdat.utils.Connection;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserDAO extends User {


    private EntityManager manager;

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

    public void deleteUser(User user) {
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            User u = manager.find(User.class, user.id);
            manager.remove(u);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
        manager.close();
    }

    public void updateUser(User user) {
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            User u = manager.find(User.class, user.id);
            u.setName(user.getName());
            u.setPhoto(user.getPhoto());
            manager.merge(u);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
        manager.close();
    }

    public List<User> getAllUser() {
        List<User> users = null;
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            TypedQuery query = manager.createNamedQuery("User.selectAll", User.class);
            users = (List<User>) query.getResultList();
            manager.getTransaction().commit();

        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
        manager.close();
        return users;
    }

    public User getUserById(int id) {
        User user = null;
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            TypedQuery query = manager.createNamedQuery("User.selectByID", User.class);
            query.setParameter("id", id);
            user = (User) query.getSingleResult();
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
        manager.close();
        return user;
    }

    public User getUserByEmail(String email) {
        User user = null;
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            TypedQuery query = manager.createNamedQuery("User.selectByEmail", User.class);
            query.setParameter("email", email);
            user = (User) query.getSingleResult();
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
        manager.close();
        return user;
    }

    public List<User> getUserByName(String name) {
        List<User> users = null;
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            TypedQuery query = manager.createNamedQuery("User.selectByName", User.class);
            query.setParameter("name", name);
            users = (List<User>) query.getResultList();
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
        manager.close();
        return users;
    }


}


