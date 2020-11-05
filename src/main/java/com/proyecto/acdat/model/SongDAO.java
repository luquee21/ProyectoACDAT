package com.proyecto.acdat.model;

import com.proyecto.acdat.utils.ConnectionUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SongDAO extends Song {
    private static final String SELECTALL = "SELECT * FROM Cancion";
    private static final String SELECTBYNAME = "SELECT * FROM Cancion WHERE nombre=?";
    private static final String SELECTALLSONGBYDISC = "SELECT * FROM Disco WHERE id_disco=?";
    private static final String INSERTSONG = "INSERT INTO Cancion (nombre,duracion,id_genero,id_disco) VALUES(?,?,NULL,?)";
    private static final String DELETESONG = "DELETE FROM Cancion WHERE id=?";
    private static final String UPDATESONG = "UPDATE Cancion SET nombre=?, duracion=? WHERE id=?";

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
    public int getDuration() {
        return super.getDuration();
    }

    @Override
    public void setDuration(int duration) {
        super.setDuration(duration);
    }

    @Override
    public Disc getDisc() {
        return super.getDisc();
    }

    @Override
    public void setDisc(Disc disc) {
        super.setDisc(disc);
    }

    public static List<Song> selectAll() {
        List<Song> aux = new ArrayList<>();
        Song song;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECTALL);
            ResultSet s = ps.executeQuery();
            while (s.next()) {
                song = new Song(s.getString("nombre"), s.getInt("duracion"));
                aux.add(song);
            }

        } catch (SQLException ex) {

        }
        return aux;
    }

    public static List<Song> selectByName(String name) {
        List<Song> aux = new ArrayList<>();
        Song song = null;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECTBYNAME);
            ps.setString(1, name);
            ResultSet s = ps.executeQuery();
            while (s.next()) {
                song = new Song(s.getString("nombre"), s.getInt("duracion"));
            }

        } catch (SQLException ex) {

        }

        return aux;
    }

    public static List<Song> selectAllSongByDisc(int id_disc) {
        List<Song> aux = new ArrayList<>();
        Song song;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECTALLSONGBYDISC);
            ps.setInt(1, id_disc);
            ResultSet s = ps.executeQuery();
            while (s.next()) {
                song = new Song(s.getString("nombre"), s.getInt("duracion"));
                aux.add(song);
            }

        } catch (SQLException ex) {

        }
        return aux;
    }

    public static boolean addSong(Song song) {
        boolean result = false;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(INSERTSONG);
            ps.setString(1, song.getName());
            ps.setInt(2, song.getDuration());
            ps.setInt(3, song.getDisc().getId());
            int rs = ps.executeUpdate();
            if (rs > 0) {
                result = true;
            }

        } catch (SQLException ex) {
        }
        return result;
    }

    public static boolean deleteSong(Song song) {
        boolean result = false;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(DELETESONG);
            ps.setInt(1, song.getId());
            int rs = ps.executeUpdate();
            if (rs > 0) {
                result = true;
            }

        } catch (SQLException ex) {
        }

        return result;
    }

    public static boolean updateSong(Song song) {
        boolean result = false;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(UPDATESONG);
            ps.setString(1, song.getName());
            ps.setInt(2, song.getDuration());
            ps.setInt(3, song.getId());
            int rs = ps.executeUpdate();
            if (rs > 0) {
                result = true;
            }

        } catch (SQLException ex) {
        }

        return result;
    }

}
