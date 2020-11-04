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
    private static final String SELECTBYNATIONALITY = "SELECT * FROM Artista WHERE nacionalidad=?";

    public ArtistDAO(Artist artist) {
        this.id = artist.id;
        this.name = artist.name;
        this.discs = artist.discs;
        this.nationality = artist.nationality;
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
    public String getNationality() {
        return super.getNationality();
    }

    @Override
    public void setNationality(String nationality) {
        super.setNationality(nationality);
    }

    @Override
    public Disc[] getDiscs() {
        return super.getDiscs();
    }

    @Override
    public void setDiscs(Disc[] discs) {
        super.setDiscs(discs);
    }

    public static List<Artist> selectAll(String username) {
        List<Artist> aux = new ArrayList<>();
        Artist artist;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECTALL);
            ResultSet s = ps.executeQuery();

            while (s.next()) {
                artist = new Artist(s.getInt("id"), s.getString("nombre"), s.getString("nacionalidad"));
                aux.add(artist);
            }

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
                while(s.next()){
                    artist = new Artist(s.getInt("id"),s.getString("nombre"),s.getString("nacionalidad"));
                }

            } catch (SQLException ex) {

            }

        return artist;
    }

    public static List<Artist> selectByNationality(String nationality) {
        List<Artist> aux = new ArrayList<>();
        Artist artist;
        try {
            java.sql.Connection conn = ConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECTALL);
            ps.setString(1,nationality);
            ResultSet s = ps.executeQuery();

            while (s.next()) {
                artist = new Artist(s.getInt("id"), s.getString("nombre"), s.getString("nacionalidad"));
                aux.add(artist);
            }

        } catch (SQLException ex) {

        }
        return aux;
    }

    public static boolean addArtist(){

    }
}
