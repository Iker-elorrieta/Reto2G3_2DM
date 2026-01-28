package controlador;

import bd.HibernateUtil;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MiControlador {

    private final HibernateUtil hibernateUtil;

    @Autowired
    public MiControlador(HibernateUtil hibernateUtil) {
        this.hibernateUtil = hibernateUtil;
    }

    @GetMapping("/test")
    public String testConexion() {
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            return "Conexión correcta con la base de datos";
        } catch (Exception e) {
            return "Error al conectar: " + e.getMessage();
        }
    }
}
