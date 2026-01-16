package controlador;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.security.MessageDigest;

import modelo.Users;
import modelo.UsersDAO;

public class ControladorServidor {

	public void login(DataInputStream in, DataOutputStream out) {
	    try {
	    	String userPlano = in.readUTF();
	    	String passPlano = in.readUTF();

	    	String userHash = sha256(userPlano);
	    	String passHash = sha256(passPlano);


	        UsersDAO dao = new UsersDAO();
	        Users u = dao.login(userHash, passHash);

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
	public static String sha256(String texto) {
	    try {
	        MessageDigest md = MessageDigest.getInstance("SHA-256");
	        byte[] hash = md.digest(texto.getBytes("UTF-8"));

	        StringBuilder hex = new StringBuilder();
	        for (byte b : hash) {
	            hex.append(String.format("%02x", b));
	        }
	        return hex.toString();

	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}

		
	}


