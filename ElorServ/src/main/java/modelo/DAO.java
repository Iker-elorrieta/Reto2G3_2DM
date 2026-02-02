package modelo;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bd.HibernateUtil;

@Component
public class DAO {

    @Autowired
    private HibernateUtil hibernateUtil;

    public Users login(String usuario, String password) {
        Session sesion = hibernateUtil.getSessionFactory().openSession();

        String hql = "FROM Users u WHERE u.username = :user AND u.password = :pass AND u.tipos.name='profesor'";
        Query<Users> q = sesion.createQuery(hql, Users.class);
        q.setParameter("user", usuario);
        q.setParameter("pass", password);

        Users u = q.uniqueResult();
        return u;
    }

    public ArrayList<Users> getAlumnos(int profesorId) {
        Session sesion = hibernateUtil.getSessionFactory().openSession();

        String hql = "SELECT DISTINCT mat.users FROM Matriculaciones mat WHERE mat.users.tipos.name = 'alumno' "
                + "AND mat.ciclos IN (SELECT h.modulos.ciclos FROM Horarios h WHERE h.users = " + profesorId
                + ")";
        Query<Users> q = sesion.createQuery(hql, Users.class);

        List<Users> lista = q.list();
        return new ArrayList<>(lista);

    }

    public ArrayList<Horarios> getHorariosUsuario(int userId) {
        Session sesion = hibernateUtil.getSessionFactory().openSession();

        String hql = "SELECT h FROM Horarios h JOIN FETCH h.modulos m JOIN FETCH m.ciclos JOIN FETCH h.users u WHERE u.id = :id AND h.modulos IS NOT NULL";
        //HACE FALTA .ID CON JOIN FETCH    
        Query<Horarios> q = sesion.createQuery(hql, Horarios.class);
        q.setParameter("id", userId);

        List<Horarios> listaHibernate = q.list();

        return new ArrayList<>(listaHibernate);
    }

    public ArrayList<Users> getProfesoresTipo3() {
        Session sesion = hibernateUtil.getSessionFactory().openSession();

        String hql = "FROM Users u WHERE u.tipos.name = 'profesor'";

        Query<Users> q = sesion.createQuery(hql, Users.class);

        List<Users> listaHibernate = q.list();

        return new ArrayList<>(listaHibernate);
    }

    public ArrayList<Reuniones> getReunionesProfesor(int idProfesor) {
        Session sesion = hibernateUtil.getSessionFactory().openSession();

        String hql = "FROM Reuniones r JOIN FETCH r.usersByAlumnoId JOIN FETCH r.usersByProfesorId WHERE r.usersByProfesorId.id = :idProfesor ";

        Query<Reuniones> q = sesion.createQuery(hql, Reuniones.class);
        q.setParameter("idProfesor", idProfesor);

        List<Reuniones> listaHibernate = q.list();

        return new ArrayList<>(listaHibernate);
    }


    public boolean actualizarEstadoReunion(int idReunion, String estado) {
        Transaction tx = null;

        try {
        Session session = hibernateUtil.getSessionFactory().openSession();

            tx = session.beginTransaction();

            Reuniones r = session.get(Reuniones.class, idReunion);
            if (r == null) {
                return false;
            }

            r.setEstado(estado);

            session.merge(r);
            tx.commit();

            return true;

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    

    }

    public boolean crearReunionObjeto(Reuniones r) {
        Transaction tx = null;

        try {
        Session session = hibernateUtil.getSessionFactory().openSession();

            tx = session.beginTransaction();
            session.persist(r);
            tx.commit();

            return true;

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }



}
