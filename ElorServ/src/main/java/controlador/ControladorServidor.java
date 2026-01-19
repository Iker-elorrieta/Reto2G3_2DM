package controlador;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.security.MessageDigest;

import modelo.Users;
import modelo.UsersDAO;

public class ControladorServidor {

    private boolean contrasenasConvertidas = false;

    public void login(DataInputStream in, DataOutputStream out) {
        try {

            if (!contrasenasConvertidas) {
                UsersDAO dao = new UsersDAO();
                dao.encriptarTodasLasContrasenas();
                contrasenasConvertidas = true;
            }

            String userPlano = in.readUTF();
            String passPlano = in.readUTF();

            String passHash = sha256(passPlano);
            String usuariohash = sha256(userPlano);
            UsersDAO dao = new UsersDAO();
            Users u = dao.login(usuariohash, passHash);

            if (u != null) {
                if (u.getTipos().getId() == 3) {
                    out.writeUTF("OK");
                    out.writeUTF(u.getUsername());
                    out.writeUTF(u.getNombre());
                    out.writeUTF(u.getTelefono2());
                } else {
                    out.writeUTF("ERROR");
                    out.writeUTF("No tienes permisos para acceder");
                }
            } else {
                out.writeUTF("ERROR");
                out.writeUTF("Usuario o contraseña incorrectos");
            }

            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	
	public static String sha256(String texto) {
	    try {
	        MessageDigest md = MessageDigest.getInstance("SHA");
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


