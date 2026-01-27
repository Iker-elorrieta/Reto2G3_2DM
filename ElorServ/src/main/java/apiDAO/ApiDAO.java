package apiDAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import bd.HibernateUtil;
import modelo.Horarios;
import modelo.Reuniones;
import modelo.Users;

import java.util.List;

@Repository
public class ApiDAO {

    private Session getSession() {
        return HibernateUtil.getSessionFactory().openSession();
    }

  
    //  USUARIOS


    public List<Users> getAllUsers() {
        Session session = getSession();
        List<Users> lista = session.createQuery("FROM Users", Users.class).list();
        session.close();
        return lista;
    }

    public Users login(String email, String password) {
        Session session = getSession();
        Query<Users> q = session.createQuery(
            "FROM Users WHERE email = :email AND password = :password",
            Users.class
        );
        q.setParameter("email", email);
        q.setParameter("password", password);

        Users u = q.uniqueResult();
        session.close();
        return u;
    }

    public void insertUser(Users u) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        session.persist(u);   
        tx.commit();
        session.close();
    }

    public void updateUser(Users u) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        session.merge(u);     
        tx.commit();
        session.close();
    }

    public void deleteUser(int id) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Users u = session.get(Users.class, id);
        if (u != null) session.remove(u);   
        tx.commit();
        session.close();
    }


    //  HORARIOS


    public List<Horarios> getHorariosProfesor(int profeId) {
        Session session = getSession();
        Query<Horarios> q = session.createQuery(
            "FROM Horarios h WHERE h.users = " + profeId,
            Horarios.class
        );

        List<Horarios> lista = q.list();
        session.close();
        return lista;
    }


    public List<Horarios> getHorariosAlumno(int alumnoId) {
        Session session = getSession();

        Query<Horarios> q = session.createQuery(
            "SELECT h FROM Horarios h " +
            "WHERE h.modulos.ciclos IN (" +
                "SELECT mat.ciclos FROM Matriculaciones mat " +
                "WHERE mat.users = " + alumnoId+
            ")",
            Horarios.class
        );


        List<Horarios> lista = q.list();
        session.close();
        return lista;
    }



    //  REUNIONES


    public List<Reuniones> getAllReuniones() {
        Session session = getSession();
        List<Reuniones> lista = session.createQuery("FROM Reuniones", Reuniones.class).list();
        session.close();
        return lista;
    }

    public void insertReunion(Reuniones r) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        session.persist(r);   
        tx.commit();
        session.close();
    }

    public void updateReunion(Reuniones r) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        session.merge(r);     
        tx.commit();
        session.close();
    }

    public void deleteReunion(int idReunion) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Reuniones r = session.get(Reuniones.class, idReunion);
        if (r != null) session.remove(r);   
        tx.commit();
        session.close();
    }
}
