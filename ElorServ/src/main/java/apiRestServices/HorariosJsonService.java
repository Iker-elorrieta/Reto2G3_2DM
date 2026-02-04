package apiRestServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apiDAO.ApiDAO;
import modelo.Horarios;

import java.util.List;

@Service
public class HorariosJsonService {

    @Autowired
    private ApiDAO dao;

    public List<Horarios> horariosProfesor(int idProfesor) {
        return dao.getHorariosProfesor(idProfesor);
    }

    public List<Horarios> horariosAlumno(int idAlumno) {
        return dao.getHorariosAlumno(idAlumno);
    }

    public void crearHorario(Horarios h) {
        dao.insertHorario(h);
    }
}


