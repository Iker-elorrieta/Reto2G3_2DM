package modelo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import bd.HibernateUtil;
import controlador.ControladorServidor;

public class UsersDAO {

    public Users login(String usuario, String password) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();

        String hql = "FROM Users u WHERE u.username = :user AND u.password = :pass";
        Query<Users> q = sesion.createQuery(hql, Users.class);
        q.setParameter("user", usuario);
        q.setParameter("pass", password);

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
