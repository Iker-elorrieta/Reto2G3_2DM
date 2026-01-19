package controlador;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import conexion.ConexionServidor;
import modelo.Users;

public class Controlador {
	
	private ConexionServidor con;
	private DataOutputStream out;
	private DataInputStream in;
    Users usuario = new Users();

	public Controlador() {
	    try {
	        con = new ConexionServidor();          //CONEXION CON EL SERVIDOR
	        out = con.getOut();
	        in = con.getIn();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	public boolean login(String usuariotexto, String contrasenatexto) {
		boolean resultado = false;
		try {
	       // FUNCION LOGIN EN LA CUAL DEPENDIENDO DE LO QUE SE RECIBA DEVUELVE UN BOOLEANO SI SE HA INICIADO SESION O NO

            out.writeUTF("LOGIN");      
            out.writeUTF(usuariotexto);     
            out.writeUTF(contrasenatexto);      

            String estado = in.readUTF();   

            switch (estado) {

                case "OK": {
                    resultado = true;

                    usuario.setUsername(in.readUTF());
                    usuario.setNombre(in.readUTF());
                    usuario.setApellidos(in.readUTF());
                    usuario.setEmail(in.readUTF());
                    usuario.setDireccion(in.readUTF());
                    usuario.setTelefono1(in.readUTF());
                    usuario.setTelefono2(in.readUTF());
                    usuario.setDni(in.readUTF());

                   
                    break;
                }

                case "ERROR": { //COMENTARIOS PROPIOS PARA COMPROBACION DE QUE ERROR ES POR CONSOLA QUE NO AFECTA A CLIENTE. 
                				//SI SIN PERMISOS O USU INCORRECTO.
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

	public Users leerUsuarioActual() {
		// FUNCION PARA GUARDAR EL USUARIO ACTIVO EN LOCAL
	    Users u = new Users();
	    u.setUsername(usuario.getUsername());
	    u.setNombre(usuario.getNombre());
	    u.setApellidos(usuario.getApellidos());
	    u.setEmail(usuario.getEmail());
	    u.setDireccion(usuario.getDireccion());
	    u.setTelefono1(usuario.getTelefono1());
	    u.setTelefono2(usuario.getTelefono2());
	    u.setDni(usuario.getDni());
	    return u;
	}


}
