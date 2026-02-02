package bd;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class HibernateUtil {

    private final SessionFactory sessionFactory;

    // Spring inyecta Environment aquí
    public HibernateUtil(Environment env) {
        this.sessionFactory = buildSessionFactory(env);
    }

    private SessionFactory buildSessionFactory(Environment env) {
        try {
            Configuration cfg = new Configuration();
            cfg.configure(); // cargamos los datos de hibernate.cfg.xml

            // Creamos las propiedades correspondientes a los datos de conexión
            Properties props = new Properties();
            props.put("hibernate.connection.url", env.getProperty("db.url"));
            props.put("hibernate.connection.username", env.getProperty("db.username"));
            props.put("hibernate.connection.password", env.getProperty("db.password"));

            cfg.addProperties(props); // sobrescribimos esas propiedades en la configuración
            return cfg.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
