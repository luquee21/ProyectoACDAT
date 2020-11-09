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
}
