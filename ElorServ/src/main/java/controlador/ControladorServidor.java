package controlador;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import modelo.Users;
import modelo.DAO;
import modelo.Horarios;

public class ControladorServidor {

	public void login(DataInputStream dis, DataOutputStream dos, ObjectOutputStream oos, ObjectInputStream ois) {
	    try {
	        String userPlano = dis.readUTF();
	        String passPlano = dis.readUTF();

	        String usuarioCifrado = CypherAES.encrypt(userPlano);
	        String passwordCifrada = CypherAES.encrypt(passPlano);

	        DAO dao = new DAO();
	        Users u = dao.login(usuarioCifrado, passwordCifrada);

	        if (u != null) {

	            dos.writeUTF("OK");
	            dos.flush();

	            u.setUsername(CypherAES.decrypt(u.getUsername()));

	            oos.writeObject(u);
	            oos.flush();

	        } else {
	            dos.writeUTF("ERROR");
	            dos.writeUTF("Usuario o contraseña incorrectos");
	            dos.flush();
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}



	public void getAlumnos(DataInputStream dis, DataOutputStream dos, ObjectOutputStream oos) {
	    try {
	        int id = dis.readInt();

	        DAO dao = new DAO();
	        ArrayList<Users> alumnos = dao.getAlumnos(id);

	        dos.writeUTF("OK");
	        dos.flush();

	        oos.writeObject(alumnos);
	        oos.flush();

	    } catch (Exception e) {
	        try {
	            dos.writeUTF("ERROR");
	            dos.writeUTF("No se pudieron obtener los alumnos");
	            dos.flush();
	        } catch (Exception ex) {}
	    }
	}


	public void getHorarios(DataInputStream dis, DataOutputStream dos, ObjectOutputStream oos) {
	    try {
	    	Gson gson = new GsonBuilder()
        	        .excludeFieldsWithoutExposeAnnotation()
        	        .create();

	        int userId = dis.readInt();

	        DAO dao = new DAO();
	        ArrayList<Horarios> lista = dao.getHorariosUsuario(userId);

	        dos.writeUTF("OK");
	        dos.flush();

	        String json = gson.toJson(lista);
	        dos.writeUTF(json);
	        dos.flush();

	    } catch (Exception e) {
	        try {
	            dos.writeUTF("ERROR");
	            dos.writeUTF("No se pudieron obtener los horarios");
	            dos.flush();
	        } catch (Exception ex) {}
	    }
	}




	public void getProfesores(DataInputStream dis, DataOutputStream dos, ObjectOutputStream oos) {
	    try {
	        DAO dao = new DAO();
	        ArrayList<Users> lista = dao.getProfesoresTipo3();

	        dos.writeUTF("OK");
	        dos.flush();

	        oos.writeObject(lista);
	        oos.flush();

	    } catch (Exception e) {
	        try {
	            dos.writeUTF("ERROR");
	            dos.writeUTF("No se pudieron obtener los profesores");
	            dos.flush();
	        } catch (Exception ex) {}
	    }
	}


}
