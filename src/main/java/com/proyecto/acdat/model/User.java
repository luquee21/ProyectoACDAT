package com.proyecto.acdat.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Usuario")
@NamedQueries({
        @NamedQuery(name = "User.selectAll", query = "SELECT u FROM User u"),
        @NamedQuery(name = "User.selectByName", query = "SELECT u FROM User u WHERE name = :name"),
        @NamedQuery(name = "User.selectByEmail", query = "SELECT u FROM User u WHERE email = :email"),
        @NamedQuery(name = "User.selectByID", query = "SELECT u FROM User u WHERE id = :id")
})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected int id;
    @Column(name = "nombre", nullable = false)
    protected String name;
    @Column(name = "correo", unique = true, nullable = false)
    protected String email;
    @Column(name = "foto", nullable = false)
    protected String photo;
    @OneToMany(mappedBy = "creator", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    protected List<PlayList> playLists;

    @ManyToMany(mappedBy = "subscribers", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    protected List<PlayList> subscription;


    public List<PlayList> getSubscription() {
        return subscription;
    }

    public void setSubscription(List<PlayList> subscription) {
        this.subscription = subscription;
        for(PlayList p : subscription){
            List<User> users = p.getSubscribers();
            if(users == null){
                users = new ArrayList<>();
            }
            if(!users.contains(this)){
                users.add(this);
            }
        }

    }

    public User() {
    }

    public User(String name, String email, String photo) {
        this.name = name;
        this.email = email;
        this.photo = photo;
    }

    public User(int id, String name, String email, String photo) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.photo = photo;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<PlayList> getPlayLists() {
        return playLists;
    }

    public void setPlayLists(List<PlayList> playLists) {
        this.playLists = playLists;
        if (playLists != null) {
            for (PlayList p : playLists) {
                p.setCreator(this);
            }
        }
    }

    @Override
    public String toString() {
       return "[Usuario] = id: " + id + ", nombre: " + name + ", email: " + email;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
