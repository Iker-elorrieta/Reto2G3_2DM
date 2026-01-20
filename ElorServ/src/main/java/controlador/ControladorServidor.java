package controlador;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

import modelo.Users;
import modelo.DAO;

public class ControladorServidor {


    public void login(DataInputStream in, DataOutputStream out) {
        try {

            
            String userPlano = in.readUTF(); //LEER LO INTRODUCIDO POR EL CLIENTE
            String passPlano = in.readUTF();

            String usuarioCifrado = CypherAES.encrypt(userPlano); // ENCRIPTACIÓN
            String passwordcifrada = CypherAES.encrypt(passPlano);
            
            
            DAO dao = new DAO();
            Users u = dao.login(usuarioCifrado, passwordcifrada);
            
            if (u != null) {
                if (u.getTipos().getId() == 3) {
                    out.writeUTF("OK");
                    out.writeUTF(CypherAES.decrypt(u.getUsername())); //DESENCRIPTACION PARA MOSTRAR EN EL MENU Y PERFIL
                    out.writeUTF(String.valueOf(u.getId()));
                    out.writeUTF(u.getNombre());
                    out.writeUTF(u.getApellidos());
                    out.writeUTF(u.getEmail());
                    out.writeUTF(u.getDireccion());
                    out.writeUTF(u.getTelefono1());
                    out.writeUTF(u.getTelefono2());
                    out.writeUTF(u.getDni());

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

    public void getAlumnos(DataInputStream in, DataOutputStream out) {
        try {
            DAO dao = new DAO();
            List<Users> alumnos = dao.getAlumnos();

            out.writeUTF("OK");
            out.writeUTF(String.valueOf(alumnos.size()));

            for (Users alu : alumnos) {
                out.writeUTF(alu.getNombre());
                out.writeUTF(alu.getApellidos());
            }

            out.flush();

        } catch (Exception e) {
            try {
                out.writeUTF("ERROR");
                out.writeUTF("No se pudieron obtener los alumnos");
                out.flush();
            } catch (IOException ex) {}
        }
    }

    public void getHorarios(DataInputStream in, DataOutputStream out) {
        try {
            int userId = Integer.parseInt(in.readUTF());

            DAO dao = new DAO();
            List<Object[]> lista = dao.getHorariosUsuario(userId);

            out.writeUTF("OK");
            out.writeUTF(String.valueOf(lista.size()));

            for (Object[] row : lista) {

                String dia = (String) row[0];
                String hora = String.valueOf(row[1]);
                String aula = row[2] == null ? "" : row[2].toString();   // ← EVITA NPE
                String modulo = row[3] == null ? "" : row[3].toString(); // ← EVITA NPE



                out.writeUTF(dia);
                out.writeUTF(hora);
                out.writeUTF(modulo);
                out.writeUTF(aula);
            }

            out.flush();

        } catch (Exception e) {
            System.out.println("ERROR EN getHorarios: " + e.getMessage());
            try {
                out.writeUTF("ERROR");
                out.writeUTF("No se pudieron obtener los horarios");
                out.flush();
            } catch (Exception ex) {}
        }
    }








		
	}


