package pruebatest;

import modelo.Peticion;
import modelo.Respuesta;
import modelo.Acciones;
import modelo.UsersDTO;

import java.util.HashMap;
import java.util.Map;

import conexion.ConexionServidor;

public class ClienteTest {

    public static void main(String[] args) {
        try {
            ConexionServidor con = new ConexionServidor();

            Map<String,Object> datos = new HashMap<>();
            datos.put("user", "alumno3");
            datos.put("pass", "123456");

            Peticion p = new Peticion(Acciones.LOGIN, datos);
            Respuesta r = con.enviar(p);

            Map<String, Object> datosRespuesta = r.getDatos();

            UsersDTO usuario = (UsersDTO) datosRespuesta.get("usuario");

            System.out.println("Usuario logueado: " + usuario.getUsername());
            System.out.println("Nombre: " + usuario.getNombre());
            System.out.println("Telefono2: " + usuario.getTelefono2());
            System.out.println("Mensaje: " + datosRespuesta.get("mensaje"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
