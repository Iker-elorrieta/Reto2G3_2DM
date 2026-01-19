package sockets;

import java.util.List;

import org.hibernate.Session;

import bd.HibernateUtil;
import controlador.CypherAES;
import modelo.Users;

//CIFRADO 1 SOLA VEZ POR ALUMNOS, IKER NO TIENES QUE TOCAR
public class PrimeroCifrar {

    public static void main(String[] args) throws Exception {
        cifrarUsuariosYContrasenas();
    }

    public static void cifrarUsuariosYContrasenas() {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        sesion.beginTransaction();

        List<Users> lista = sesion.createQuery("FROM Users", Users.class).list();

        for (Users u : lista) {

           
                u.setUsername(CypherAES.encrypt(u.getUsername()));
         

           
                u.setPassword(CypherAES.encrypt(u.getPassword()));
            

            sesion.merge(u);
        }

        sesion.getTransaction().commit();
        sesion.close();
    }

  
}
