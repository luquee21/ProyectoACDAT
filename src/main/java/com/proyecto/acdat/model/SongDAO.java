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

}
