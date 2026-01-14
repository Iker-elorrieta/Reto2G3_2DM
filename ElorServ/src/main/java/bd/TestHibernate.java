package bd;


import org.hibernate.Session;
import org.hibernate.SessionFactory;

import modelo.Users;

public class TestHibernate {

	public static void main(String[] args) {

    	SessionFactory sessionF = HibernateUtil.getSessionFactory();

    	Session sesion = sessionF.openSession();

    	Users user = sesion.get(Users.class, 1);
    	System.out.println(user.getUsername());


    	sesion.close();
    	sessionF.close();
    }
}
