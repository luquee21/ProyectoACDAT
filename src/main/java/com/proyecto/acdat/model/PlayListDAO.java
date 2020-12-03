package com.proyecto.acdat.model;

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

    public PlayListDAO(String name, String description) {
        super(name, description);
    }
}
