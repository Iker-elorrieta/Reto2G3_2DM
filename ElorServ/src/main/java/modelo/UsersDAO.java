package modelo;

import org.hibernate.Session;
import org.hibernate.query.Query;

import bd.HibernateUtil;


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
}
