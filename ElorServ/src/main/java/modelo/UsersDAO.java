package modelo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import bd.HibernateUtil;
import controlador.ControladorServidor;

public class UsersDAO {

    public Users login(String usuario, String passwordHash) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();

        String hql = "FROM Users u WHERE u.username = :user AND u.password = :pass";
        Query<Users> q = sesion.createQuery(hql, Users.class);
        q.setParameter("user", usuario);
        q.setParameter("pass", passwordHash);

        Users u = q.uniqueResult();
        sesion.close();
        return u;
    }

    public void encriptarTodasLasContrasenas() {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        sesion.beginTransaction();

        List<Users> lista = sesion.createQuery("FROM Users", Users.class).list();

        for (Users u : lista) {
            String actual = u.getPassword();

            if (actual != null && actual.length() == 64 && actual.matches("[0-9a-fA-F]+")) {
                continue;
            }

            String nueva = ControladorServidor.sha256(actual);
            u.setPassword(nueva);
            sesion.merge(u);
        }

        sesion.getTransaction().commit();
        sesion.close();
    }


}
