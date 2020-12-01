package com.proyecto.acdat.model;

import com.proyecto.acdat.utils.ConnectionUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends User {

    private static final String SELECTALL = "SELECT * FROM Usuario";
    private static final String SELECTBYNAME = "SELECT * FROM Usuario WHERE nombre=?";
    private static final String SELECTBYEMAIL = "SELECT * FROM Usuario WHERE correo=?";
    private static final String SELECTBYID = "SELECT * FROM Usuario WHERE id=?";
    private static final String INSERTUSER = "INSERT INTO Usuario (correo,nombre,foto) VALUES(?,?,?)";
    private static final String DELETEUSER = "DELETE FROM Usuario WHERE id=?";
    private static final String DELETEALLPLAYLISTOFUSER = "DELETE FROM Lista WHERE id_usuario=?";
    private static final String UPDATEUSER = "UPDATE Usuario SET nombre=?, foto=? WHERE id=?";

}
