package com.proyecto.acdat.model;

import com.proyecto.acdat.utils.ConnectionUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DiscDAO extends Disc {

    private static final String SELECTALL = "SELECT * FROM Disco";
    private static final String SELECTBYNAME = "SELECT * FROM Disco WHERE nombre=?";
    private static final String SELECTBYARTIST = "SELECT * FROM Disco WHERE id_artista=?";
    private static final String INSERTDISC = "INSERT INTO Disco (nombre, foto, id_artista, fecha_prod) VALUES (?, ?, ?, ?)";
    private static final String UPDATEDISC = "UPDATE Disco set nombre=?, foto=?, id_Artista=?, fecha_prod=?";
    private static final String DELETEDISC = "DELETE from Disco where id=?";


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
    public Song[] getSongs() {
        return super.getSongs();
    }

    @Override
    public void setSongs(Song[] songs) {
        super.setSongs(songs);
    }

    public static List<Disc> selectAll() {
        List<Disc> aux = new ArrayList<>();
        Disc disc;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECTALL);
            ResultSet s = ps.executeQuery();

            while (s.next()) {
                disc = new Disc(s.getInt("id"), s.getString("nombre"), s.getString("foto"));
                aux.add(disc);
            }

        } catch (SQLException ex) {

        }
        return aux;
    }

    public static Disc selectByName(String name) {
        Disc disc = null;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECTBYNAME);
            ps.setString(1, name);
            ResultSet s = ps.executeQuery();
            while (s.next()) {
                disc = new Disc(s.getInt("id"), s.getString("nombre"), s.getString("foto"));
            }

        } catch (SQLException ex) {

        }

        return disc;
    }

    public static List<Disc> selectByArtist(Artist artist) {
        List<Disc> aux = new ArrayList<>();
        Disc disc;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECTBYARTIST);
            ps.setInt(1, artist.getId());
            ResultSet s = ps.executeQuery();

            while (s.next()) {
                disc = new Disc(s.getInt("id"), s.getString("nombre"), s.getString("nacionalidad"));
                aux.add(disc);
            }

        } catch (SQLException ex) {

        }
        return aux;
    }

    public static boolean addDisc(Disc disc) {
        boolean result = false;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(INSERTDISC);
            ps.setString(1, disc.getName());
            ps.setString(2, disc.getPhoto());
            ps.setInt(3, disc.getArtist().getId());
            ps.setDate(4, disc.getDate());
            int rs = ps.executeUpdate();
            if (rs > 0) {
                result = true;
            }

        } catch (SQLException ex) {
        }

        return result;
    }

    public static boolean deleteDisc(Disc disc) {
        boolean result = false;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(DELETEDISC);
            ps.setInt(1, disc.getId());
            int rs = ps.executeUpdate();
            if (rs > 0) {
                result = true;
            }

        } catch (SQLException ex) {
        }

        return result;
    }

    public static boolean updateArtist(Disc disc) {
        boolean result = false;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(UPDATEDISC);

            ps.setString(1, disc.getName());
            ps.setString(2, disc.getPhoto());
            ps.setInt(3, disc.getArtist().getId());
            ps.setDate(4, disc.getDate());
            int rs = ps.executeUpdate();
            if (rs > 0) {
                result = true;
            }

        } catch (SQLException ex) {
        }

        return result;
    }
}
