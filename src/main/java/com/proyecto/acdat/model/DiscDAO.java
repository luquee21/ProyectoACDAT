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
    private static final String UPDATEDISC = "UPDATE Disco set nombre=?, foto=?, fecha_prod=? WHERE id_artista=?";
    private static final String DELETEDISC = "DELETE from Disco where id=?";
    private static final String DELETEALLDISC = "DELETE from Disco where id_artista=?";



    public static List<Disc> selectAll() {
        List<Disc> aux = new ArrayList<>();
        Disc disc;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECTALL);
            ResultSet s = ps.executeQuery();

            while (s.next()) {
                disc = new Disc(s.getInt("id"),s.getString("nombre"), s.getString("foto"),s.getString("fecha_prod"),s.getInt("id_artista"));
                aux.add(disc);
            }

        } catch (SQLException ex) {

        }
        return aux;
    }

    public static List<Disc> selectByName(String name) {
        List<Disc> discs = new ArrayList<>();
        Disc disc;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECTBYNAME);
            ps.setString(1, name);
            ResultSet s = ps.executeQuery();
            while (s.next()) {
                disc = new Disc(s.getInt("id"),s.getString("nombre"), s.getString("foto"),s.getString("fecha_prod"),s.getInt("id_artista"));
                discs.add(disc);
            }

        } catch (SQLException ex) {

        }

        return discs;
    }

    public static List<Disc> selectByArtist(int id) {
        List<Disc> aux = new ArrayList<>();
        Disc disc;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECTBYARTIST);
            ps.setInt(1, id);
            ResultSet s = ps.executeQuery();

            while (s.next()) {
                disc = new Disc(s.getInt("id"),s.getString("nombre"), s.getString("foto"),s.getString("fecha_prod"),s.getInt("id_artista"));
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
            ps.setInt(3, disc.getId_artista());
            ps.setString(4, disc.getDate());
            int rs = ps.executeUpdate();
            if (rs > 0) {
                result = true;
            }

        } catch (SQLException ex) {
        }

        return result;
    }

    public static boolean deleteDisc(int id) {
        boolean result = false;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(DELETEDISC);
            ps.setInt(1, id);
            int rs = ps.executeUpdate();
            if (rs > 0) {
                result = true;
            }

        } catch (SQLException ex) {
        }

        return result;
    }

    public static boolean deleteAllDiscOfArtist(int id) {
        boolean result = false;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(DELETEALLDISC);
            ps.setInt(1, id);
            int rs = ps.executeUpdate();
            if (rs > 0) {
                result = true;
            }

        } catch (SQLException ex) {
        }

        return result;
    }

    public static boolean updateDisc(Disc disc) {
        boolean result = false;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(UPDATEDISC);

            ps.setString(1, disc.getName());
            ps.setString(2, disc.getPhoto());
            ps.setString(3, disc.getDate());
            ps.setInt(4, disc.getId_artista());
            int rs = ps.executeUpdate();
            if (rs > 0) {
                result = true;
            }

        } catch (SQLException ex) {
        }

        return result;
    }

}
