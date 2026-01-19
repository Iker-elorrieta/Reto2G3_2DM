package controlador;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import modelo.Users;
import modelo.UsersDAO;

public class ControladorServidor {


    public void login(DataInputStream in, DataOutputStream out) {
        try {

            
            String userPlano = in.readUTF(); //LEER LO INTRODUCIDO POR EL CLIENTE
            String passPlano = in.readUTF();

            String usuarioCifrado = CypherAES.encrypt(userPlano); // ENCRIPTACIÓN
            String passwordcifrada = CypherAES.encrypt(passPlano);
            
            
            UsersDAO dao = new UsersDAO();
            Users u = dao.login(usuarioCifrado, passwordcifrada);
            
            if (u != null) {
                if (u.getTipos().getId() == 3) {
                    out.writeUTF("OK");
                    out.writeUTF(CypherAES.decrypt(u.getUsername())); //DESENCRIPTACION PARA MOSTRAR EN EL MENU Y PERFIL
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


		
	}


