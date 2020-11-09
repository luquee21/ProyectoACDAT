package com.proyecto.acdat.gui;

import com.proyecto.acdat.instance.MyInstance;
import com.proyecto.acdat.model.User;
import com.proyecto.acdat.utils.Utilities;

import java.util.List;

public class SubMenuUser {
    public static void user() {
        int option = 0;
        do {
            Utilities.P("---- Usuario ----");
            Utilities.P("1) Añadir usuario");
            Utilities.P("2) Borrar usuario");
            Utilities.P("3) Listar usuario");
            Utilities.P("4) Actualizar usuario");
            Utilities.P("5) Volver atrás");
            option = Utilities.getInt();

            switch (option) {
                case 1:
                    addUser();
                    break;
                case 2:
                    deleteUser();
                    break;
                case 3:
                    listUser();
                    break;
                case 4:
                    updateUser();
                    break;
                case 5:
                    Menu.start();
                    break;
                default:
                    Utilities.P("Introduzca una opción válida");
                    break;
            }
        } while (option != 5);
    }

    private static void addUser() {
        Utilities.P("---- Añadir Usuario ----");
        String name = Utilities.getString("Introduce el nombre de usuario: ");
        String email = Utilities.getString("Introduce el correo de usuario: ");
        String photo = Utilities.getString("Introduce la foto de usuario: ");
        if (MyInstance.getInstance().addUser(new User(name, email, photo))) {
            Utilities.P("Se ha creado el usuario con éxito");
        } else {
            Utilities.P("No se ha podido crear el usuario");
        }
    }

    private static void deleteUser() {
        Utilities.P("---- Borrar Usuario ----");
        String email = Utilities.getString("Introduce el email del usuario que deseas borrar: ");
        if(MyInstance.getInstance().deleteUser(email)){
            Utilities.P("Se ha borrado el usuario con éxito");
        } else {
            Utilities.P("No se ha podido borrar el usuario");
        }
    }

    private static void listUser() {
        int option = 0;
        Utilities.P("---- Listar Usuario ----");
        Utilities.P("1) Listar todos los usuarios");
        Utilities.P("2) Listar usuario por nombre");
        Utilities.P("3) Listar usuario por email");
        Utilities.P("4) Volver atrás");
        option = Utilities.getInt();
        List<User> users;
        switch (option){
            case 1:
                users = MyInstance.getInstance().selectAllUser();
                if(users.isEmpty()){
                    Utilities.P("No hay ningún usuario creado");
                } else {
                    for(User u: users){
                        Utilities.P(u.toString());
                    }
                }
                break;
            case 2:
                String name = Utilities.getString("Introduce el nombre de usuario: ");
                users = MyInstance.getInstance().selectUserByName(name);
                if(users==null){
                    Utilities.P("No hay usuarios con ese nombre");
                }else{
                    for(User u : users){
                        Utilities.P(u.toString());
                    }
                }
                break;
            case 3:
                String email = Utilities.getString("Introduce el email de usuario: ");
                User user = MyInstance.getInstance().selectUserByEmail(email);
                if(user==null){
                    Utilities.P("No hay ningún usuario con ese email");
                } else {
                    Utilities.P(user.toString());
                }
                break;
            case 4:
                user();
                break;
            default:
                Utilities.P("Introduzca una opción válida");
                break;
        }
    }

    private static void updateUser() {
        Utilities.P("---- Actualizar Usuario ----");
        String oldemail = Utilities.getString("Introduce el email del usuario: ");
        User oldUser = MyInstance.getInstance().selectUserByEmail(oldemail);

        if(oldUser==null){
            Utilities.P("No hay ningún usuario con ese email");
        }else{
            String name = Utilities.getString("Introduzca el nuevo nombre de usuario: ");
            String photo = Utilities.getString("Introduzca la nueva foto de usuario: ");
            User newUser = new User(name,oldemail,photo);

            if(oldUser.equals(newUser)){
                Utilities.P("No puede ser igual");
            }else {
                if(MyInstance.getInstance().updateUser(newUser)){
                    Utilities.P("Se ha actualizado con éxito");
                }else{
                    Utilities.P("No se ha podido actualizar");
                }
            }
        }

    }
}

