package controlador;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bd.HibernateUtil;
import modelo.Users;

@Component
public class PrimeroCifrar {

    private final HibernateUtil hibernateUtil;

    @Autowired
    public PrimeroCifrar(HibernateUtil hibernateUtil) {
        this.hibernateUtil = hibernateUtil;
    }

    public void cifrarUsuariosYContrasenas() {
        Session sesion = hibernateUtil.getSessionFactory().openSession();
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
