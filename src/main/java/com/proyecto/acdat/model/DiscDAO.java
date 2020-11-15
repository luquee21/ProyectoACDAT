package com.proyecto.acdat.model;

import com.proyecto.acdat.utils.ConnectionUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DiscDAO extends Disc {

    private static final String SELECTALL = "SELECT * FROM Disco";
    private static final String SELECTBYID = "SELECT * FROM Disco WHERE id = ?";
    private static final String SELECTBYNAME = "SELECT * FROM Disco WHERE nombre=?";
    private static final String SELECTBYARTIST = "SELECT * FROM Disco WHERE id_artista=?";
    private static final String INSERTDISC = "INSERT INTO Disco (nombre, foto, id_artista, fecha_prod) VALUES (?, ?, ?, ?)";
    private static final String UPDATEDISC = "UPDATE Disco set nombre=?, foto=?, fecha_prod=? WHERE id_artista=?";
    private static final String DELETEDISC = "DELETE from Disco where id=?";
    private static final String DELETEALLDISC = "DELETE from Disco where id_artista=?";


    /**
     * selecciona todos los discos de la bbdd
     * @return devuelve una lista con todos los artistas
     */
    public static List<Disc> selectAll() {
        List<Disc> aux = new ArrayList<>();
        Disc disc;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECTALL);
            ResultSet s = ps.executeQuery();

            while (s != null && s.next()) {
                disc = new Disc(s.getInt("id"), s.getString("nombre"), s.getString("foto"), s.getString("fecha_prod"), s.getInt("id_artista"));
                aux.add(disc);
            }

        } catch (SQLException ex) {

        }
        return aux;
    }

    /**
     * selecciona todos los discos con un nombre en concreto
     * @param name nombre del disco
     * @return devuelve una lista de discos
     */
    public static List<Disc> selectByName(String name) {
        List<Disc> discs = new ArrayList<>();
        Disc disc;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECTBYNAME);
            ps.setString(1, name);
            ResultSet s = ps.executeQuery();
            while (s != null && s.next()) {
                disc = new Disc(s.getInt("id"), s.getString("nombre"), s.getString("foto"), s.getString("fecha_prod"), s.getInt("id_artista"));
                discs.add(disc);
            }

        } catch (SQLException ex) {

        }

        return discs;
    }

    /**
     * selecciona una lista de discos según el id de un artista
     * @param id id del artista
     * @return devuelve una lista de discos
     */
    public static List<Disc> selectByArtist(int id) {
        List<Disc> aux = new ArrayList<>();
        Disc disc;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECTBYARTIST);
            ps.setInt(1, id);
            ResultSet s = ps.executeQuery();

            while (s != null && s.next()) {
                disc = new Disc(s.getInt("id"), s.getString("nombre"), s.getString("foto"), s.getString("fecha_prod"), s.getInt("id_artista"));
                aux.add(disc);
            }

        } catch (SQLException ex) {

        }
        return aux;
    }

    /**
     * selecciona un disco de la bbdd según su id
     * @param id id del disco
     * @return devuelve el disco
     */
    public static Disc selectById(int id) {
        Disc disc = null;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECTBYID);
            ps.setInt(1, id);
            ResultSet s = ps.executeQuery();
            while (s != null && s.next()) {
                disc = new Disc(s.getInt("id"), s.getString("nombre"), s.getString("foto"), s.getString("fecha_prod"), s.getInt("id_artista"));
            }

        } catch (SQLException ex) {

        }
        return disc;
    }

    /**
     * añade un disco a la bbdd
     * @param disc instancia de disco
     * @return devuelve un verdadero si todo ha ido correcto
     */
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

    /**
     * borra un disco segun su id
     * @param id id de disco
     * @return devuelve un verdadero si todo ha ido correcto
     */
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

    /**
     * borra todos los disco de un artista segun su id
     * @param id id del artista
     * @return devuelve un verdadero si todo ha ido correcto
     */
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

    /**
     * actualiza un disco en la bbdd segun el id del artista
     * @param disc instancia de disco
     * @return devuelve un verdadero si todo ha ido correcto
     */
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
