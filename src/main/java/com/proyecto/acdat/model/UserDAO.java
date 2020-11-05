package com.proyecto.acdat.model;

import com.proyecto.acdat.utils.ConnectionUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends User {
    private static final String SELECTALL = "SELECT * FROM Usuario";
    private static final String SELECTBYNAME = "SELECT * FROM Usuario WHERE nombre=?";
    private static final String SELECTBYEMAIL = "SELECT * FROM Usuario WHERE correo=?";
    private static final String INSERTUSER = "INSERT INTO Usuario (correo,nombre,foto) VALUES(?,?,?)";
    private static final String DELETEUSER = "DELETE FROM Usuario WHERE id=?";
    private static final String UPDATEUSER = "UPDATE Usuario SET nombre=? WHERE id=?";

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }

    @Override
    public PlayList[] getPlaylists() {
        return super.getPlaylists();
    }

    @Override
    public void setPlaylists(PlayList[] playlists) {
        super.setPlaylists(playlists);
    }

    public static List<User> selectAll() {
        List<User> aux = new ArrayList<>();
        User user;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECTALL);
            ResultSet s = ps.executeQuery();

            while (s.next()) {
                user = new User(s.getInt("id"), s.getString("nombre"), s.getString("email"), s.getString("foto"));
                aux.add(user);
            }

        } catch (SQLException ex) {

        }
        return aux;
    }

    public static User selectByName(String name){
        User user = null;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECTBYNAME);
            ps.setString(1,name);
            ResultSet s = ps.executeQuery();
            while(s.next()){
                user = new User(s.getInt("id"),s.getString("nombre"),s.getString("correo"),s.getString("foto"));
            }

        } catch (SQLException ex) {

        }

        return user;
    }

    public static User selectByEmail(String email){
        User user = null;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECTBYEMAIL);
            ps.setString(1,email);
            ResultSet s = ps.executeQuery();
            while(s.next()){
                user = new User(s.getInt("id"),s.getString("nombre"),s.getString("correo"),s.getString("foto"));
            }

        } catch (SQLException ex) {

        }

        return user;
    }

    public static boolean addUser(User user){
        boolean result = false;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(INSERTUSER);
            ps.setString(1,user.getEmail());
            ps.setString(2,user.getName());
            ps.setString(3,user.getPhoto());
            int rs = ps.executeUpdate();
            if(rs > 0){
                result = true;
            }

        } catch (SQLException ex) {
        }

        return result;
    }

    public static boolean deleteUser(User user){
        boolean result = false;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(DELETEUSER);
            ps.setInt(1,user.getId());
            int rs = ps.executeUpdate();
            if(rs > 0){
                result = true;
            }

        } catch (SQLException ex) {
        }

        return result;
    }

    public static boolean updateUser(User user){
        boolean result = false;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(UPDATEUSER);
            ps.setString(1,user.getName());
            ps.setInt(2,user.getId());

            int rs = ps.executeUpdate();
            if(rs > 0){
                result = true;
            }

        } catch (SQLException ex) {
        }

        return result;
    }
}