package controlador;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import conexion.ConexionServidor;
import modelo.UsersDTO;

public class Controlador {

	public boolean login(String usuariotexto, String contrasenatexto) {
		boolean resultado = false;
		try {
	        
            ConexionServidor con = new ConexionServidor();
         DataOutputStream out = con.getOut();
         DataInputStream in = con.getIn();

            out.writeUTF("LOGIN");      
            out.writeUTF(usuariotexto);     
            out.writeUTF(contrasenatexto);      

            String estado = in.readUTF();   

            switch (estado) {

                case "OK": {
                    resultado = true;

                    UsersDTO usuario = new UsersDTO();
                    usuario.setUsername(in.readUTF());
                    usuario.setNombre(in.readUTF());
                    usuario.setTelefono2(in.readUTF());

      
                    System.out.println("Usuario: " + usuario.getUsername());
                    System.out.println("Nombre: " + usuario.getNombre());
                    System.out.println("Teléfono: " + usuario.getTelefono2());
                    break;
                }

                case "ERROR": {
                    String mensaje = in.readUTF();
                    System.out.println("=== ERROR ===");
                    System.out.println(mensaje);
                    break;
                }

                default:
                    System.out.println("Respuesta desconocida del servidor");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
		return resultado;
	}

}
