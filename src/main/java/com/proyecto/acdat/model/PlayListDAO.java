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
    private static final String SELECTBYEMAIL = "SELECT * FROM Lista WHERE id_usuario=?";
    private static final String INSERTPLAYLIST = "INSERT INTO Lista (nombre,descripcion,id_usuario) VALUES(?,?,?)";
    private static final String DELETEPLAYLIST = "DELETE FROM Lista WHERE id=?";
    private static final String UPDATEPLAYLIST = "UPDATE Lista SET nombre=?, descripcion=? WHERE id=?";
    private static final String INSERTSUB = "INSERT INTO Suscripcion values(?,?)";
    private static final String DELETESUB = "DELETE FROM Suscripcion WHERE id_usuario=?";
    private static final String SELECTSUB = "SELECT * FROM Suscripcion INNER JOIN Usuario ON Suscripcion.id_usuario=Usuario.id WHERE Suscripcion.id_lista=?";
    private static final String INSERTSONG = "INSERT INTO Lista_cancion values(?,?)";
    private static final String DELETESONG = "DELETE FROM Lista_cancion WHERE id_cancion=? AND id_lista=?";
    private static final String SELECTBYID = "SELECT * FROM Lista WHERE id=?";

    public PlayListDAO() {
    }

    /**
     * Metodo que se conecta a la BBDD
     * @return una lista de todas las playlists de la BBDD
     */
    public static List<PlayList> selectAll() {
        List<PlayList> aux = new ArrayList<>();
        PlayList playList;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECTALL);
            ResultSet s = ps.executeQuery();

            while (s!=null && s.next()) {
                playList = new PlayList(s.getInt("id"),s.getString("nombre"),s.getString("descripcion"), s.getInt("id_usuario"));
                aux.add(playList);
            }

        } catch (SQLException ex) {
        }
        return aux;
    }

    /**
     * Método que se conecta a la BBDD
     * @param name nombre de la playlist a buscar
     * @return Lista de todas las playlist de la BBDD con el nombre pasado por parametro
     */
    public static List<PlayList> selectPlayListByName(String name){
        PlayList playList = null;
        List<PlayList> aux = new ArrayList<>();
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECTBYNAME);
            ps.setString(1,name);
            ResultSet s = ps.executeQuery();
            while(s!=null && s.next()){
                playList = new PlayList(s.getInt("id"),s.getString("nombre"),s.getString("descripcion"), s.getInt("id_usuario"));
                aux.add(playList);
            }

        } catch (SQLException ex) {
        }

        return aux;
    }

    /**
     * Método que se conecta a la BBDD
     * @param id id de la playlist a buscar
     * @return la playlist buscada por la id pasada
     */
    public static PlayList selectPlayListById(int id){
        PlayList playList = null;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECTBYID);
            ps.setInt(1,id);
            ResultSet s = ps.executeQuery();
            while(s!=null && s.next()){
                playList = new PlayList(s.getInt("id"),s.getString("nombre"),s.getString("descripcion"), s.getInt("id_usuario"));            }

        } catch (SQLException ex) {

        }

        return playList;
    }

    /**
     * Método que se conecta a la BBDD para buscar una playlist creada por cierto usuario
     * @param user Usuario del programa
     * @return lista de playlists que contengan como creador el usuario pasado por parametro
     */
    public static List<PlayList> selectPlayListByEmail(User user) {
        List<PlayList> aux = new ArrayList<>();
        PlayList playList;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECTBYEMAIL);
            ps.setInt(1, user.getId());
            ResultSet s = ps.executeQuery();

            while (s!=null && s.next()) {
                playList = new PlayList(s.getInt("id"), s.getString("nombre"), s.getString("descripcion"), s.getInt("id_usuario"));
                aux.add(playList);
            }

        } catch (SQLException ex) {

        }
        return aux;
    }

    /**
     * Método que se conecta a la BBDD para añadir una nueva playlist
     * @param playList lista de reproducción
     * @return booleano (verdadero o falso) si se ha podido o no añadir la lista de reproducción a la BBDD
     */
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

    /**
     * Método que se conecta a la BBDD para borrar una playlist
     * @param id id de la playlist a borrar
     * @return booleano (verdadero o falso) si se ha podido o no borrar la lista de reproducción en la BBDD
     */
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

    /**
     * Método que conecta la BBDD para actualizar datos de una playlist
     * @param playList una playlist nueva para editar los datos de una ya existente
     * @return booleano (verdadero o falso) si se ha podido o no actualizar la lista de reproducción en la BBDD
     */
    public static boolean updatePlayList(PlayList playList){
        boolean result = false;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(UPDATEPLAYLIST);

            ps.setString(1,playList.getName());
            ps.setString(2,playList.description);
            ps.setInt(3,playList.getId());
            int rs = ps.executeUpdate();
            if(rs > 0){
                result = true;
            }

        } catch (SQLException ex) {
        }

        return result;
    }

    /**
     * Método que conecta con la BBDD para añadir un suscriptor a una playlist
     * @param user Usuario a suscribir
     * @param id id de la playlist a la que queremos suscribirnos
     * @return booleano (verdadero o falso) si se ha podido o no realizar la suscripción a la lista
     * de reproducción
     */
    public static boolean addSubToPlayList(User user, int id){
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

    /**
     * Método que conecta a la BBDD para borrar una suscripción de una playlist
     * @param user Usuario a borrar
     * @param id id de la playlist de la que se desea quitar la suscripción
     * @return booleano (verdadero o falso) si se ha podido o no quitar la suscripción de
     * la lista de reproducción en la BBDD
     */
    public static boolean deleteSubOfPlayList(User user, int id){
        boolean result = false;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(DELETESUB);
            ps.setInt(1, user.getId());
            int rs = ps.executeUpdate();
            if(rs > 0){
                result = true;
            }

        } catch (SQLException ex) {
        }

        return result;
    }

    /**
     * Método que conecta a la BBDD para añadir una canción a una playlist
     * @param song cancion a añadir
     * @param id id de la playlist donde queremos añadir la canción
     * @return booleano (verdadero o falso) si se ha podido o no añadir la canción
     * a la lista de reproducción en la BBDD
     */
    public static boolean addSongToPlayList(Song song, int id) {
        boolean result = false;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(INSERTSONG);
            ps.setInt(1, id);
            ps.setInt(2, song.getId());
            int rs = ps.executeUpdate();
            if (rs > 0) {
                result = true;
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return result;

    }

    /**
     * Método que conecta con la BBDD para borrar una cancion de una playlist
     * @param idSong id de la camción a borrar
     * @param idPlaylist id de la playlist de donde borrar la canción
     * @return booleano (verdadero o falso) si se ha podido o no borrar la canción
     * de la lista de reproducción en la BBDD
     */
    public static boolean deleteSongToPlayList(int idSong, int idPlaylist){
        boolean result=false;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(DELETESONG);
            ps.setInt(1,idSong);
            ps.setInt(2,idPlaylist);
            int rs = ps.executeUpdate();
            if(rs > 0){
                result = true;
            }
        }catch (SQLException ex){}
        return result;
    }

    /**
     * Método que conecta con la BBDD para mostrar la suscriptores de una playlist
     * @param id id de la playlist para buscar sus suscriptores
     * @return lista de usuarios que contiene los usuarios suscritos a la playlist con la id pasada
     */
    public static List<User> selectSubOfPlaylist(int id){
        User user = null;
        List<User> aux = new ArrayList<>();
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECTSUB);
            ps.setInt(1,id);
            ResultSet s = ps.executeQuery();
            while(s!=null && s.next()){
                user = new User(s.getInt("id"), s.getString("nombre"), s.getString("correo"), s.getString("foto"));
                aux.add(user);
            }

        } catch (SQLException ex) {

        }

        return aux;
    }

}
