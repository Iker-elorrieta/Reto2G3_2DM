package controlador;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import conexion.ConexionServidor;
import modelo.Users;

public class Controlador {
	
	private ConexionServidor con;
	private DataOutputStream out;
	private DataInputStream in;

	public Controlador() {
	    try {
	        con = new ConexionServidor();
	        out = con.getOut();
	        in = con.getIn();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	public boolean login(String usuariotexto, String contrasenatexto) {
		boolean resultado = false;
		try {
	       

            out.writeUTF("LOGIN");      
            out.writeUTF(usuariotexto);     
            out.writeUTF(contrasenatexto);      

            String estado = in.readUTF();   

            switch (estado) {

                case "OK": {
                    resultado = true;

                    Users usuario = new Users();
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
