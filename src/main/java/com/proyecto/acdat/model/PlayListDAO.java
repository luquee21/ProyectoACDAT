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
    public List<Song> getSongs() {
        return super.getSongs();
    }

    @Override
    public void setSongs(List<Song> songs) {
        super.setSongs(songs);
    }

    @Override
    public List<User> getSubscribers() {
        return super.getSubscribers();
    }

    @Override
    public void setSubscribers(List<User> subscribers) {
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

            while (s!=null && s.next()) {
                playList = new PlayList(s.getInt("id"),s.getString("nombre"),s.getString("descripcion"), s.getInt("id_usuario"));
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
            while(s!=null && s.next()){
                playList = new PlayList(s.getInt("id"),s.getString("nombre"),s.getString("descripcion"), s.getInt("id_usuario"));
                aux.add(playList);
            }

        } catch (SQLException ex) {
        }

        return aux;
    }

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
            ps.setInt(3,playList.getId());
            int rs = ps.executeUpdate();
            if(rs > 0){
                result = true;
            }

        } catch (SQLException ex) {
        }

        return result;
    }

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

    public static boolean addSongToPlayList(Song song, int id){
        boolean result = false;
        try{
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(INSERTSONG);
            ps.setInt(1, song.getId());
            ps.setInt(2,id);
            int rs = ps.executeUpdate();
            if (rs > 0){
                result = true;
            }

        }catch (SQLException ex){
            System.out.println(ex);
        }
        return result;

    }

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
