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

    public static List<User> selectAll() {
        List<User> aux = new ArrayList<>();
        User user;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECTALL);
            ResultSet s = ps.executeQuery();

            while (s.next()) {
                user = new User(s.getString("nombre"), s.getString("correo"), s.getString("foto"));
                aux.add(user);
            }

        } catch (SQLException ex) {
        }
        return aux;
    }

    public static List<User> selectByName(String name){
        List<User> users = new ArrayList<>();
        User user;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECTBYNAME);
            ps.setString(1,name);
            ResultSet s = ps.executeQuery();
            while(s.next()){
                user = new User(s.getString("nombre"),s.getString("correo"),s.getString("foto"));
                users.add(user);
            }

        } catch (SQLException ex) {

        }

        return users;
    }

    public static User selectByEmail(String email){
        User user=null;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECTBYEMAIL);
            ps.setString(1,email);
            ResultSet s = ps.executeQuery();
            while(s.next()){
                user = new User(s.getString("nombre"),s.getString("correo"),s.getString("foto"));
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

    public static boolean deleteUser(String email){
        boolean result = false;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(DELETEUSER);
            ps.setString(1,email);
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
