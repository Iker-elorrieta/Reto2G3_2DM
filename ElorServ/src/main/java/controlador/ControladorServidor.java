package controlador;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import apiDAO.LeerJson;
import modelo.Users;
import modelo.Centro;
import modelo.DAO;
import modelo.Horarios;
import modelo.Reuniones;

@Component
public class ControladorServidor {
	@Autowired
	private DAO dao;


	public void login(DataInputStream dis, DataOutputStream dos, ObjectOutputStream oos, ObjectInputStream ois) {
	    try {
	        String userPlano = dis.readUTF();
	        String passPlano = dis.readUTF();

	        String usuarioCifrado = CypherAES.encrypt(userPlano);
	        String passwordCifrada = CypherAES.encrypt(passPlano);

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

	public void getCentros(DataOutputStream dos, ObjectOutputStream oos) {
	    try {
	        List<Centro> lista =  LeerJson.getCentros(); 
	        dos.writeUTF("OK");
	        dos.flush();

	        oos.writeObject(lista);
	        oos.flush();

	    } catch (Exception e) {
	        try {
	            dos.writeUTF("ERROR");
	            dos.writeUTF("No se pudieron obtener los centros");
	            dos.flush();
	        } catch (Exception ex) {}
	    }
	}
	public void getReuniones(DataInputStream dis, DataOutputStream dos, ObjectOutputStream oos) {
	    try {
	        int idProfesor = dis.readInt();

	        ArrayList<Reuniones> lista = dao.getReunionesProfesor(idProfesor);

	        dos.writeUTF("OK");
	        dos.flush();

	        oos.writeObject(lista);
	        oos.flush();

	    } catch (Exception e) {
	        try {
	            dos.writeUTF("ERROR");
	            dos.writeUTF("No se pudieron obtener las reuniones");
	            dos.flush();
	        } catch (Exception ex) {}
	    }
	}


	public void getProfesores(DataInputStream dis, DataOutputStream dos, ObjectOutputStream oos) {
	    try {
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
