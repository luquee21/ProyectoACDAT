package com.proyecto.acdat.model;

import com.proyecto.acdat.utils.ConnectionUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayListDAO extends PlayList {

    private static final String SELECTALL = "SELECT * FROM Lista";
    private static final String SELECTBYNAME = "SELECT * FROM Lista WHERE nombre=?";
    private static final String SELECTBYUSERNAME = "SELECT * FROM Lista WHERE id_usuario=?";
    private static final String INSERTPLAYLIST = "INSERT INTO Lista (nombre,descripcion,id_usuario) VALUES(?,?,?,?)";
    private static final String DELETEPLAYLIST = "DELETE FROM Lista WHERE id=?";
    private static final String UPDATEPLAYLIST = "UPDATE Lista SET nombre=?, descripcion=?, id_usuario=? WHERE id=?";
    private static final String INSERTSUB = "INSERT INTO Suscripcion (id_usuario, id_lista) values(?,?)";
    private static final String DELETESUB = "DELETE FROM Suscripcion WHERE id_usuario=? and id_lista=?";


    public PlayListDAO(int id, String name, String description) {
        super(id, name, description);
    }


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
    public String getDescription() {
        return super.getDescription();
    }

    @Override
    public void setDescription(String description) {
        super.setDescription(description);
    }

    @Override
    public Song[] getSongs() {
        return super.getSongs();
    }

    @Override
    public void setSongs(Song[] songs) {
        super.setSongs(songs);
    }

    @Override
    public User[] getSubscribers() {
        return super.getSubscribers();
    }

    @Override
    public void setSubscribers(User[] subscribers) {
        super.setSubscribers(subscribers);
    }

    @Override
    public User getCreator() {
        return super.getCreator();
    }

    @Override
    public void setCreator(User creator) {
        super.setCreator(creator);
    }

    public static List<PlayList> selectAll() {
        List<PlayList> aux = new ArrayList<>();
        PlayList playList;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECTALL);
            ResultSet s = ps.executeQuery();

            while (s.next()) {
                playList = new PlayList(s.getInt("id"), s.getString("name"), s.getString("description"));
                aux.add(playList);
            }

        } catch (SQLException ex) {

        }
        return aux;
    }

    public static List<PlayList> selectPlayListByName(String name){
        PlayList playList = null;
        List<PlayList> aux = new ArrayList<>();
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECTBYNAME);
            ps.setString(1,name);
            ResultSet s = ps.executeQuery();
            while(s.next()){
                playList = new PlayList(s.getInt("id"),s.getString("name"),s.getString("description"));
                aux.add(playList);
            }

        } catch (SQLException ex) {

        }

        return aux;
    }

    public static List<PlayList> selectPlayListByUserName(User user) {
        List<PlayList> aux = new ArrayList<>();
        PlayList playList;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECTBYUSERNAME);
            ps.setInt(1, user.getId());
            ResultSet s = ps.executeQuery();

            while (s.next()) {
                playList = new PlayList(s.getInt("id"), s.getString("nombre"), s.getString("nacionalidad"));
                aux.add(playList);
            }

        } catch (SQLException ex) {

        }
        return aux;
    }

    public static boolean addPlayList(PlayList playList){
        boolean result = false;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(INSERTPLAYLIST);
            ps.setString(1,playList.name);
            ps.setString(2,playList.description);
            ps.setInt(3,playList.creator.getId());
            int rs = ps.executeUpdate();
            if(rs > 0){
                result = true;
            }

        } catch (SQLException ex) {
        }

        return result;
    }

    public static boolean deletePlayList(int id){
        boolean result = false;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(DELETEPLAYLIST);
            ps.setInt(1,id);
            int rs = ps.executeUpdate();
            if(rs > 0){
                result = true;
            }

        } catch (SQLException ex) {
        }

        return result;
    }

    public static boolean updatePlayList(PlayList playList){
        boolean result = false;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(UPDATEPLAYLIST);

            ps.setString(1,playList.getName());
            ps.setString(2,playList.description);
            ps.setInt(3,playList.creator.getId());
            int rs = ps.executeUpdate();
            if(rs > 0){
                result = true;
            }

        } catch (SQLException ex) {
        }

        return result;
    }

    boolean addSubToPlayList(User user, int id){
        boolean result=false;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(INSERTSUB);
            ps.setInt(1, user.getId());
            ps.setInt(2, id);
            int rs = ps.executeUpdate();
            if(rs > 0){
                result = true;
            }

        } catch (SQLException ex) {
        }

        return result;
    }

    boolean deleteSubOfPlayList(User user, int id){
        boolean result = false;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(DELETESUB);
            ps.setInt(1, user.getId());
            ps.setInt(2, id);
            int rs = ps.executeUpdate();
            if(rs > 0){
                result = true;
            }

        } catch (SQLException ex) {
        }

        return result;
    }

}
