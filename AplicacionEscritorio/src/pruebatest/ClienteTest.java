package pruebatest;


import modelo.Peticion;
import modelo.Respuesta;
import modelo.Acciones;
import conexion.ConexionServidor;

public class ClienteTest {

    public static void main(String[] args) {
        try {
            ConexionServidor con = new ConexionServidor();

            Peticion p = new Peticion(Acciones.LOGIN, "hola");
            Respuesta r = con.enviar(p);

            System.out.println("Respuesta del servidor: " + r.getDatos());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
