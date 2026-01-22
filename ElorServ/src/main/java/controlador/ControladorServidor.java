package controlador;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import modelo.Users;
import modelo.DAO;
import modelo.Horarios;
import modelo.Modulos;

public class ControladorServidor {

	public void login(DataInputStream in, DataOutputStream out, ObjectOutputStream oos) {
		try {

			String userPlano = in.readUTF(); // LEER LO INTRODUCIDO POR EL CLIENTE
			String passPlano = in.readUTF();

			String usuarioCifrado = CypherAES.encrypt(userPlano); // ENCRIPTACIÓN
			String passwordcifrada = CypherAES.encrypt(passPlano);

			DAO dao = new DAO();
			Users u = dao.login(usuarioCifrado, passwordcifrada);

			if (u != null) {
				if (u.getTipos().getId() == 3) {

					out.writeUTF("OK");
					out.flush();

					u.setUsername(CypherAES.decrypt(u.getUsername()));

					oos.writeObject(u);
					oos.flush();

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

	public void getAlumnos(DataInputStream in, DataOutputStream out, ObjectOutputStream oos) {
		try {
			DAO dao = new DAO();
			List<Users> alumnos = dao.getAlumnos(Integer.parseInt(in.readUTF()));

			out.writeUTF("OK");
			out.flush();

			Users[] array = alumnos.toArray(new Users[0]);


			oos.writeObject(array);
			oos.flush();

		} catch (Exception e) {
			try {
				out.writeUTF("ERROR");
				out.writeUTF("No se pudieron obtener los alumnos");
				out.flush();
			} catch (IOException ex) {
			}
		}
	}

	public void getHorarios(DataInputStream in, DataOutputStream out, ObjectOutputStream oos) {
		try {
			int userId = Integer.parseInt(in.readUTF());

			DAO dao = new DAO();
			List<Object[]> lista = dao.getHorariosUsuario(userId);

			out.writeUTF("OK");
			out.flush();

			Horarios[] array = new Horarios[lista.size()];

			for (int i = 0; i < lista.size(); i++) {
				Object[] row = lista.get(i);

				String dia = (String) row[0];
				byte hora = Byte.parseByte(row[1].toString());
				String aula = row[2] == null ? "" : row[2].toString();
				String moduloNombre = row[3] == null ? "" : row[3].toString();

				Modulos mod = new Modulos();
				mod.setNombre(moduloNombre);

				Users profe = new Users();
				profe.setId(userId);

				array[i] = new Horarios(mod, profe, dia, hora);
				array[i].setAula(aula);
			}

			oos.writeObject(array);
			oos.flush();

		} catch (Exception e) {
			System.out.println("ERROR EN getHorarios: " + e.getMessage());
			try {
				out.writeUTF("ERROR");
				out.writeUTF("No se pudieron obtener los horarios");
				out.flush();
			} catch (Exception ex) {
			}
		}
	}

	public void getProfesores(DataInputStream in, DataOutputStream out, ObjectOutputStream oos) {
		try {
			DAO dao = new DAO();
			List<Users> lista = dao.getProfesoresTipo3(); //DEVUELVE PROFESORES TIPO 3 USUARIOS COMPLETOS

			out.writeUTF("OK");
			out.flush();

			oos.writeObject(lista.toArray(new Users[0]));
			oos.flush();
			
		} catch (Exception e) {
			try {
				out.writeUTF("ERROR");
				out.writeUTF("No se pudieron obtener los profesores");
				out.flush();
			} catch (Exception ex) {
			}
		}
	}

}
