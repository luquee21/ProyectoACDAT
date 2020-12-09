package com.proyecto.acdat.model;

import com.proyecto.acdat.utils.Connection;
import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserDAO extends User {

    private static final String TAG = "USERDAO";
    private EntityManager manager;

    public UserDAO(String name, String email, String photo) {
        super(name, email, photo);
    }

    public UserDAO() {
    }

    public boolean addUser(User u) {
        boolean result = false;
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            manager.persist(u);
            manager.getTransaction().commit();
            result = true;
        } catch (Exception e) {
        }
        manager.close();
        return result;
    }

    public boolean deleteUser(User user) {
        boolean result = false;
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            User u = manager.find(User.class, user.id);
            manager.remove(u);
            manager.getTransaction().commit();
            result = true;
        } catch (Exception e) {
            System.out.println(e);
        }
        manager.close();
        return result;
    }

    public boolean deleteAllPlaylistOfUser(int id) {
        boolean result = false;
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            User u = getUserById(id, true);
            u.setPlayLists(null);
            manager.persist(u);
            manager.getTransaction().commit();
            result = true;
        } catch (Exception e) {
        }
        manager.close();
        return result;
    }

    public boolean updateUser(User user) {
        boolean result = false;
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            User u = getUserById(id, false);
            u.setName(user.getName());
            u.setPhoto(user.getPhoto());
            manager.merge(u);
            manager.getTransaction().commit();
            result = true;
        } catch (Exception e) {
        }
        manager.close();
        return result;
    }

    public List<User> getAllUser(boolean flag) {
        List<User> users = null;
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            TypedQuery query = manager.createNamedQuery("User.selectAll", User.class);
            users = (List<User>) query.getResultList();
            if (flag) {
                for (User u : users) {
                    Hibernate.initialize(u.getPlayLists());
                    Hibernate.initialize(u.getSubscription());
                }
            }
            manager.getTransaction().commit();
        } catch (Exception e) {
        }
        manager.close();
        return users;
    }

    public User getUserById(int id, boolean flag) {
        User user = null;
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            user = manager.find(User.class, id);
            if (flag) {
                Hibernate.initialize(user.getPlayLists());
                Hibernate.initialize(user.getSubscription());
            }
            manager.getTransaction().commit();
        } catch (Exception e) {
        }
        manager.close();
        return user;
    }

    public User getUserByEmail(String email, boolean flag) {
        User user = null;
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            TypedQuery query = manager.createNamedQuery("User.selectByEmail", User.class);
            query.setParameter("email", email);
            user = (User) query.getSingleResult();
            if (flag) {
                Hibernate.initialize(user.getPlayLists());
                Hibernate.initialize(user.getSubscription());
            }
            manager.getTransaction().commit();
        } catch (Exception e) {
        }
        manager.close();
        return user;
    }

    public List<User> getUserByName(String name, boolean flag) {
        List<User> users = null;
        manager = Connection.getManager();
        try {
            manager.getTransaction().begin();
            TypedQuery query = manager.createNamedQuery("User.selectByName", User.class);
            query.setParameter("name", name);
            users = (List<User>) query.getResultList();
            if (flag) {
                for (User u : users) {
                    Hibernate.initialize(u.getPlayLists());
                    Hibernate.initialize(u.getSubscription());
                }
            }
            manager.getTransaction().commit();
        } catch (Exception e) {
        }
        manager.close();
        return users;
    }


}


