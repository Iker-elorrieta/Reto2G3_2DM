package modelo;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import bd.HibernateUtil;

public class DAO {

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

    public List<Users> getAlumnos(int profesorId) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();

        Query<Users> q = sesion.createQuery(
            "SELECT DISTINCT mat.users " +
            "FROM Matriculaciones mat " +
            "WHERE mat.users.tipos.id = 4 " + 
            "AND mat.ciclos.id IN (" +
                "SELECT h.modulos.ciclos.id " +
                "FROM Horarios h " +
                "WHERE h.users.id = :profeId" + 
            ")",
            Users.class
        );

        q.setParameter("profeId", profesorId);

        List<Users> lista = q.list();
        sesion.close();
        return lista;
    }


    public List<Object[]> getHorariosUsuario(int userId) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();

        String hql = "SELECT h.dia, h.hora, h.aula, h.modulos.nombre "
                   + "FROM Horarios h "
                   + "WHERE h.users.id = :id";

        Query<Object[]> q = sesion.createQuery(hql, Object[].class);
        q.setParameter("id", userId);

        List<Object[]> lista = q.list();
        sesion.close();
        return lista;
    }


    public List<Users> getProfesoresTipo3() {
        Session sesion = HibernateUtil.getSessionFactory().openSession();

        String hql = "FROM Users u WHERE u.tipos.id = 3";

        Query<Users> q = sesion.createQuery(hql, Users.class);

        List<Users> lista = q.list();
        sesion.close();
        return lista;
    }

    


}
