package com.proyecto.acdat.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Usuario")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "nombre")
    private String name;
    @Column(name = "correo")
    private String email;
    @Column(name = "foto")
    private String photo;
    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PlayList> playLists;

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
    }

    @Override
    public String toString() {
       return "[Usuario] = id: " + id + ", nombre: " + name + ", email: " + email;

    }

    @Override
    public boolean equals(Object o) {
        User user = (User) o;
        return id == user.id && email.equals(user.getEmail()) && photo.equals(user.getPhoto());
    }
}
