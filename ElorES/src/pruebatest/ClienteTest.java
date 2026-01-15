package pruebatest;

import conexion.ConexionServidor;
import modelo.UsersDTO;

import java.io.DataInputStream;
import java.io.DataOutputStream;


public class ClienteTest {

    public static void main(String[] args) {
        try {
        
            ConexionServidor con = new ConexionServidor();
         DataOutputStream out = con.getOut();
         DataInputStream in = con.getIn();

            out.writeUTF("LOGIN");      
            out.writeUTF("alumno3");     
            out.writeUTF("123456");      

            String estado = in.readUTF();   

            switch (estado) {

                case "OK": {
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
    }
}
