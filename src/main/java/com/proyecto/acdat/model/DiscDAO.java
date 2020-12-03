package com.proyecto.acdat.model;

public class DiscDAO extends Disc {

    private static final String SELECTALL = "SELECT * FROM Disco";
    private static final String SELECTBYID = "SELECT * FROM Disco WHERE id = ?";
    private static final String SELECTBYNAME = "SELECT * FROM Disco WHERE nombre=?";
    private static final String SELECTBYARTIST = "SELECT * FROM Disco WHERE id_artista=?";
    private static final String INSERTDISC = "INSERT INTO Disco (nombre, foto, id_artista, fecha_prod) VALUES (?, ?, ?, ?)";
    private static final String UPDATEDISC = "UPDATE Disco set nombre=?, foto=? WHERE id_artista=?";
    private static final String DELETEDISC = "DELETE from Disco where id=?";
    private static final String DELETEALLDISC = "DELETE from Disco where id_artista=?";


    public DiscDAO(String name, String photo, Artist artist, String date) {
        super(name, photo, artist, date);
    }
}
