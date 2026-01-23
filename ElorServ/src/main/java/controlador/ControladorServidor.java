package controlador;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import modelo.Users;
import modelo.DAO;
import modelo.Horarios;
import modelo.Modulos;

public class ControladorServidor {

    public void login(ObjectInputStream ois, ObjectOutputStream oos) throws ClassNotFoundException {
        try {

            String userPlano = (String) ois.readObject(); 
            String passPlano = (String) ois.readObject();

            String usuarioCifrado = CypherAES.encrypt(userPlano);
            String passwordcifrada = CypherAES.encrypt(passPlano);

            DAO dao = new DAO();
            Users u = dao.login(usuarioCifrado, passwordcifrada);

            if (u != null) {
                if (u.getTipos().getId() == 3) {

                    oos.writeObject("OK");
                    oos.flush();

                    u.setUsername(CypherAES.decrypt(u.getUsername()));

                    oos.writeObject(u);
                    oos.flush();

                } else {
                    oos.writeObject("ERROR");
                    oos.writeObject("No tienes permisos para acceder");
                }
            } else {
                oos.writeObject("ERROR");
                oos.writeObject("Usuario o contraseña incorrectos");
            }

            oos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void getAlumnos(ObjectInputStream ois, ObjectOutputStream oos) {
        try {
            DAO dao = new DAO();
            int id = Integer.parseInt((String) ois.readObject());
            List<Users> alumnos = dao.getAlumnos(id);

            oos.writeObject("OK");
            oos.flush();



            oos.writeObject(alumnos);
            oos.flush();

        } catch (Exception e) {
            try {
                oos.writeObject("ERROR");
                oos.writeObject("No se pudieron obtener los alumnos");
                oos.flush();
            } catch (IOException ex) {}
        }
    }

    public void getHorarios(ObjectInputStream ois, ObjectOutputStream oos) {
        try {
        	int userId = (Integer) ois.readObject();

            DAO dao = new DAO();
            ArrayList<Horarios> lista = dao.getHorariosUsuario(userId);

            oos.writeObject("OK");
            oos.flush();

            ArrayList<Horarios> lista2 = new ArrayList<>();

            for (int i = 0; i < lista.size(); i++) {
                Horarios row = lista.get(i);

                Modulos mod = new Modulos();
                mod.setNombre(row.getModulos().getNombre());

                Users profe = new Users();
                profe.setId(userId);

                lista2.add(new Horarios(mod, profe, row.getDia(), row.getHora(), row.getAula()));
            }


            oos.writeObject(lista2);
            oos.flush();

        } catch (Exception e) {
            System.out.println("ERROR EN getHorarios: " + e.getMessage());
            try {
                oos.writeObject("ERROR");
                oos.writeObject("No se pudieron obtener los horarios");
                oos.flush();
            } catch (Exception ex) {}
        }
    }


    public void getProfesores(ObjectInputStream ois, ObjectOutputStream oos) {
        try {
            DAO dao = new DAO();
            List<Users> lista = dao.getProfesoresTipo3();

            oos.writeObject("OK");
            oos.flush();

            oos.writeObject(lista.toArray(new Users[0]));
            oos.flush();

        } catch (Exception e) {
            try {
                oos.writeObject("ERROR");
                oos.writeObject("No se pudieron obtener los profesores");
                oos.flush();
            } catch (Exception ex) {}
        }
    }

}
