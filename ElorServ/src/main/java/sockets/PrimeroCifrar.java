package sockets;

import java.util.List;

import org.hibernate.Session;

import bd.HibernateUtil;
import controlador.ControladorServidor;
import modelo.Users;


public class PrimeroCifrar {
	
    public static void main(String[] args) throws Exception {

     encriptarTodasLasContrasenas();

}
    public static void encriptarTodasLasContrasenas() {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        sesion.beginTransaction();

        List<Users> lista = sesion.createQuery("FROM Users", Users.class).list();

        for (Users u : lista) {
            String actual = u.getPassword();
            String actualNombre = u.getUsername();

            if (actual != null && actual.length() == 64 && actual.matches("[0-9a-fA-F]+")) {
                continue;
            }
            if (actualNombre != null && actualNombre.length() == 64 && actualNombre.matches("[0-9a-fA-F]+")) {
                continue;
            }

            String nueva = ControladorServidor.sha(actual);
            String nuevoNombre = ControladorServidor.sha(actualNombre);
            u.setPassword(nueva);
            u.setUsername(nuevoNombre);
            sesion.merge(u);
        }

        sesion.getTransaction().commit();
        sesion.close();
    }
}
