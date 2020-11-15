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
    private static final String SELECTBYIDSONG = "SELECT * FROM Artista INNER JOIN Disco ON Artista.id = Disco.id_artista INNER JOIN Cancion ON Cancion.id_disco = Disco.id WHERE Cancion.id = ?";
    private static final String SELECTBYID = "SELECT * FROM Artista WHERE id=?";
    private static final String SELECTBYNATIONALITY = "SELECT * FROM Artista WHERE nacionalidad=?";
    private static final String INSERTARTIST = "INSERT INTO Artista (nombre,nacionalidad,foto) VALUES(?,?,?)";
    private static final String DELETEARTIST = "DELETE FROM Artista WHERE nombre=?";
    private static final String UPDATEARTIST = "UPDATE Artista SET nombre=?, nacionalidad=?, foto=? WHERE id=?";

    /**
     * selecciona todos los artistas de la bbbdd
     * @return devuelve una lista de artistas
     */
    public static List<Artist> selectAll() {
        List<Artist> aux = new ArrayList<>();
        Artist artist;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECTALL);
            ResultSet s = ps.executeQuery();

            while (s != null && s.next()) {
                artist = new Artist(s.getInt("id"), s.getString("nombre"), s.getString("nacionalidad"), s.getString("foto"));
                aux.add(artist);
            }
            if (s != null) {
                s.close();
            }

        } catch (SQLException ex) {

        }
        return aux;
    }

    /**
     * selecciona el artista por su nombre de la bbdd
     * @param name nombre del artista
     * @return devuelve el artista
     */
        public static Artist selectByName(String name){
            Artist artist = null;
            try {
                java.sql.Connection conn = ConnectionUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(SELECTBYNAME);
                ps.setString(1, name);
                ResultSet s = ps.executeQuery();
                while (s != null && s.next()) {
                    artist = new Artist(s.getInt("id"), s.getString("nombre"), s.getString("nacionalidad"), s.getString("foto"));
                }
                if (s != null) {
                    s.close();
                }
            } catch (SQLException ex) {

            }

            return artist;
        }

    /**
     * selecciona un artista por su id
     * @param id id del artista
     * @return deveulve el artista
     */
    public static Artist selectById(int id) {
        Artist artist = null;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECTBYID);
            ps.setInt(1, id);
            ResultSet s = ps.executeQuery();
            while (s != null && s.next()) {
                artist = new Artist(s.getInt("id"), s.getString("nombre"), s.getString("nacionalidad"), s.getString("foto"));
            }
            if (s != null) {
                s.close();
            }
        } catch (SQLException ex) {

        }

        return artist;
    }

    /**
     * selecciona un artista por un id de su cancion
     * @param id id de la cancion
     * @return devuelve un artista
     */
    public static Artist selectByIdSong(int id) {
        Artist artist = null;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECTBYIDSONG);
            ps.setInt(1, id);
            ResultSet s = ps.executeQuery();
            while (s != null && s.next()) {
                artist = new Artist(s.getInt("id"), s.getString("nombre"), s.getString("nacionalidad"), s.getString("foto"));
            }
            if (s != null) {
                s.close();
            }
        } catch (SQLException ex) {

        }

        return artist;
    }

    /**
     * selecciona una lista de artistas segun su nacionalidad
     * @param nationality nacionalidad del artista
     * @return devuelve una lista de artista
     */
    public static List<Artist> selectByNationality(String nationality) {
        List<Artist> aux = new ArrayList<>();
        Artist artist;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECTBYNATIONALITY);
            ps.setString(1, nationality);
            ResultSet s = ps.executeQuery();

            while (s != null && s.next()) {
                artist = new Artist(s.getInt("id"), s.getString("nombre"), s.getString("nacionalidad"), s.getString("foto"));
                aux.add(artist);
            }
            if (s != null) {
                s.close();
            }
        } catch (SQLException ex) {

        }
        return aux;
    }

    /**
     * añade un artista a la bbdd
     * @param artist le pasamos una instancia de artista
     * @return devuelve un verdadero si todo ha ido correcto
     */
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

    /**
     * borra de la bbdd un artista segun su nombre
     * @param name nombre del artista
     * @return devuelve un verdadero si todo ha ido correcto
     */
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

    /**
     * actualiza un artista segun su id
     * @param artist instancia del artista con sus nuevos datos
     * @param id id del artista que se va a actualizar
     * @return devuelve un verdadero si todo ha ido correcto
     */
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
