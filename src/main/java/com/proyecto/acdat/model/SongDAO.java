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
    private static final String SELECTALLSONGOFPLAYLIST = "SELECT * FROM Cancion INNER JOIN Lista_cancion ON Cancion.id = Lista_cancion.id_cancion WHERE Lista_cancion.id_lista = ?";
    private static final String SELECTALLSONGBYDISC = "SELECT * FROM Cancion INNER JOIN Disco ON Cancion.id_disco=Disco.id WHERE Disco.id=?";
    private static final String SELECTALLSONGOFARTIST = "SELECT * FROM Cancion INNER JOIN Disco ON Cancion.id_disco = Disco.id INNER JOIN Artista ON Artista.id=Disco.id_artista WHERE Artista.nombre=?";
    private static final String INSERTSONG = "INSERT INTO Cancion (nombre,duracion,id_genero,id_disco) VALUES(?,?,NULL,?)";
    private static final String DELETESONG = "DELETE FROM Cancion WHERE id=?";
    private static final String DELETEALLSONGOFDISC = "DELETE FROM Cancion WHERE id_disco=?";
    private static final String UPDATESONG = "UPDATE Cancion SET nombre=?, duracion=? WHERE id=?";

    public SongDAO(){
        super();

    }

    /**
     * selecciona todas las canciones de una lista de la bbdd
     * @param id id de la playlist
     * @return devuelve una lista de canciones
     */
    public static List<Song> selectAllSongOfPlaylist(int id) {
        List<Song> aux = new ArrayList<>();
        Song song;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECTALLSONGOFPLAYLIST);
            ps.setInt(1, id);
            ResultSet s = ps.executeQuery();
            while (s != null && s.next()) {
                song = new Song(s.getInt("id"), s.getString("nombre"), s.getInt("duracion"), s.getInt("id_disco"));
                aux.add(song);
            }

        } catch (SQLException ex) {

        }
        return aux;
    }

    /**
     * selecciona todas las canciones de la bbdd
     * @return devuelve una lista de canciones
     */
    public static List<Song> selectAll() {
        List<Song> aux = new ArrayList<>();
        Song song;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECTALL);
            ResultSet s = ps.executeQuery();
            while (s != null && s.next()) {
                song = new Song(s.getInt("id"), s.getString("nombre"), s.getInt("duracion"), s.getInt("id_disco"));
                aux.add(song);
            }

        } catch (SQLException ex) {

        }
        return aux;
    }

    /**
     * selecciona todas las cancion segun un nombre en concreto
     * @param name nombre de la cancion
     * @return devuelve una lista de canciones
     */
    public static List<Song> selectByName(String name) {
        List<Song> aux = new ArrayList<>();
        Song song;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECTBYNAME);
            ps.setString(1, name);
            ResultSet s = ps.executeQuery();
            while (s != null && s.next()) {
                song = new Song(s.getInt("id"), s.getString("nombre"), s.getInt("duracion"), s.getInt("id_disco"));
                aux.add(song);
            }

        } catch (SQLException ex) {

        }

        return aux;
    }

    /**
     * borra todas las canciones de un disco
     * @param id id de disco
     * @return devuelve un verdadero si todo ha ido correcto
     */
    public static boolean deleteAllSongOfDisc(int id){
        boolean result = false;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(DELETEALLSONGOFDISC);
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
     * selecciona todas las canciones de un artista
     * @param name nombre del artista
     * @return devuelve una lista de canciones
     */
    public static List<Song> selectAllSongOfArtist(String name) {
        List<Song> aux = new ArrayList<>();
        Song song;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECTALLSONGOFARTIST);
            ps.setString(1, name);
            ResultSet s = ps.executeQuery();
            while (s != null && s.next()) {
                song = new Song(s.getInt("id"), s.getString("nombre"), s.getInt("duracion"), s.getInt("id_disco"));
                aux.add(song);
            }

        } catch (SQLException ex) {

        }
        return aux;
    }

    /**
     * selecciona todas las canciones de un disco
     * @param id_disc id del disco
     * @return devuelve una lista de canciones
     */
    public static List<Song> selectAllSongByDisc(int id_disc) {
        List<Song> aux = new ArrayList<>();
        Song song;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECTALLSONGBYDISC);
            ps.setInt(1, id_disc);
            ResultSet s = ps.executeQuery();
            while (s != null && s.next()) {
                song = new Song(s.getInt("id"), s.getString("nombre"), s.getInt("duracion"), s.getInt("id_disco"));
                aux.add(song);
            }

        } catch (SQLException ex) {

        }
        return aux;
    }

    /**
     * añade una cancion a la bbdd
     * @param song instancia de la cancion
     * @return devuelve un verdadero si todo ha ido correcto
     */
    public static boolean addSong(Song song) {
        boolean result = false;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(INSERTSONG);
            ps.setString(1, song.getName());
            ps.setInt(2, song.getDuration());
            ps.setInt(3, song.getId_disc());
            int rs = ps.executeUpdate();
            if (rs > 0) {
                result = true;
            }

        } catch (SQLException ex) {
        }
        return result;
    }

    /**
     * borra una cancion segun su id
     * @param id id de la cancion
     * @return devuelve un verdadero si todo ha ido correcto
     */
    public static boolean deleteSong(int id) {
        boolean result = false;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(DELETESONG);
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
     * actualiza una cancion
     * @param song instancia de la cancion
     * @return devuelve un verdadero si todo ha ido correcto
     */
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
