package apiRestServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apiDAO.ApiDAO;
import modelo.Reuniones;

import java.util.List;

@Service
public class ReunionesJsonService {

    @Autowired
    private ApiDAO dao;

    public List<Reuniones> listarReuniones() {
        return dao.getAllReuniones();
    }

    public void crearReunion(Reuniones r) {
        dao.insertReunion(r);
    }

    public void actualizarReunion(int id, Reuniones r) {
        r.setIdReunion(id);
        dao.updateReunion(r);
    }

    public void eliminarReunion(int id) {
        dao.deleteReunion(id);
    }
}
