package com.proyecto.acdat.model;

import com.proyecto.acdat.utils.ConnectionUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArtistDAO extends Artist {
    private static final String SELECTALL = "SELECT * FROM Artista";
    private static final String SELECTBYNAME = "SELECT * FROM Artista WHERE nombre=?";
    private static final String SELECTBYID = "SELECT * FROM Artista WHERE id=?";
    private static final String SELECTBYNATIONALITY = "SELECT * FROM Artista WHERE nacionalidad=?";
    private static final String INSERTARTIST = "INSERT INTO Artista (nombre,nacionalidad,foto) VALUES(?,?,?)";
    private static final String DELETEARTIST = "DELETE FROM Artista WHERE nombre=?";
    private static final String UPDATEARTIST = "UPDATE Artista SET nombre=?, nacionalidad=?, foto=? WHERE id=?";


    public static List<Artist> selectAll() {
        List<Artist> aux = new ArrayList<>();
        Artist artist;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECTALL);
            ResultSet s = ps.executeQuery();

            while (s.next()) {
                artist = new Artist(s.getInt("id"),s.getString("nombre"), s.getString("nacionalidad"), s.getString("foto"));
                aux.add(artist);
            }
                s.close();
        } catch (SQLException ex) {

        }
        return aux;
    }

        public static Artist selectByName(String name){
            Artist artist = null;
            try {
                java.sql.Connection conn = ConnectionUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(SELECTBYNAME);
                ps.setString(1,name);
                ResultSet s = ps.executeQuery();
                while (s.next()) {
                    artist = new Artist(s.getInt("id"), s.getString("nombre"), s.getString("nacionalidad"), s.getString("foto"));
                }
                s.close();
            } catch (SQLException ex) {

            }

            return artist;
        }

    public static Artist selectById(int id) {
        Artist artist = null;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECTBYID);
            ps.setInt(1, id);
            ResultSet s = ps.executeQuery();
            while (s.next()) {
                artist = new Artist(s.getInt("id"), s.getString("nombre"), s.getString("nacionalidad"), s.getString("foto"));
            }
            s.close();
        } catch (SQLException ex) {

        }

        return artist;
    }

    public static List<Artist> selectByNationality(String nationality) {
        List<Artist> aux = new ArrayList<>();
        Artist artist;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECTBYNATIONALITY);
            ps.setString(1, nationality);
            ResultSet s = ps.executeQuery();

            while (s.next()) {
                artist = new Artist(s.getInt("id"),s.getString("nombre"), s.getString("nacionalidad"), s.getString("foto"));
                aux.add(artist);
            }
            s.close();
        } catch (SQLException ex) {

        }
        return aux;
    }

    public static boolean addArtist(Artist artist){
        boolean result = false;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(INSERTARTIST);
            ps.setString(1,artist.getName());
            ps.setString(2,artist.getNationality());
            ps.setString(3,artist.getPhoto());
            int rs = ps.executeUpdate();
            if(rs > 0){
                result = true;
            }
        } catch (SQLException ex) {
        }

        return result;
    }

    public static boolean deleteArtist(String name){
        boolean result = false;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(DELETEARTIST);
            ps.setString(1,name);
            int rs = ps.executeUpdate();
            if(rs > 0){
                result = true;
            }

        } catch (SQLException ex) {
        }
        return result;
    }

    public static boolean updateArtist(Artist artist, int id){
        boolean result = false;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(UPDATEARTIST);

            ps.setString(1,artist.getName());
            ps.setString(2,artist.getNationality());
            ps.setString(3,artist.getPhoto());
            ps.setInt(4,id);
            int rs = ps.executeUpdate();
            if(rs > 0){
                result = true;
            }

        } catch (SQLException ex) {

        }
        return result;
    }
}
