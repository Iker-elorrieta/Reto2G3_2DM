package controlador;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;

import modelo.Users;
import modelo.DAO;

public class ControladorServidor {


	public void login(DataInputStream in, DataOutputStream out) {
	    try {
	        String userPlano = in.readUTF();
	        String passPlano = in.readUTF();

	        String usuarioCifrado = CypherAES.encrypt(userPlano);
	        String passwordCifrada = CypherAES.encrypt(passPlano);

	        DAO dao = new DAO();
	        Users u = dao.login(usuarioCifrado, passwordCifrada);

	        if (u != null && u.getTipos().getId() == 3) {
	            out.writeUTF("OK");
	            out.flush();

	            u.setUsername(CypherAES.decrypt(u.getUsername()));

	            ObjectOutputStream oos = new ObjectOutputStream(out);
	            oos.writeObject(u);
	            oos.flush();

	        } else {
	            out.writeUTF("ERROR");
	            out.writeUTF("Usuario o contraseña incorrectos");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

        
    }







		
	


