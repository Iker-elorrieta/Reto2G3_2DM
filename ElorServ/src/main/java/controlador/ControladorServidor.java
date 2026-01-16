package controlador;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import modelo.Users;
import modelo.UsersDAO;

public class ControladorServidor {

	public void login(DataInputStream in, DataOutputStream out) {
	    try {
	        String user = in.readUTF();
	        String pass = in.readUTF();

	        UsersDAO dao = new UsersDAO();
	        Users u = dao.login(user, pass);

	        if (u != null) {

	            if (u.getTipos().getId() == 3) {
	                out.writeUTF("OK");
	                out.writeUTF(u.getUsername());
	                out.writeUTF(u.getNombre());
	                out.writeUTF(u.getTelefono2());
	                out.flush();
	            } else {
	                out.writeUTF("ERROR");
	                out.writeUTF("No tienes permisos para acceder");
	                out.flush();
	            }

	        } else {
	            out.writeUTF("ERROR");
	            out.writeUTF("Usuario o contraseña incorrectos");
	            out.flush();
	        }

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

		
	}


