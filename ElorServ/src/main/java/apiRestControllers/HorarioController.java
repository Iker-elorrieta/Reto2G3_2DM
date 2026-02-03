package apiRestControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import apiRestServices.HorariosJsonService;
import modelo.Horarios;

import java.util.List;

@RestController
@RequestMapping("/api/horarios")
public class HorarioController {

    @Autowired
    private HorariosJsonService service;

    @GetMapping("/profesor/{id}")
    public List<Horarios> horariosProfesor(@PathVariable int id) {
        return service.horariosProfesor(id);
    }

    @GetMapping("/alumno/{id}")
    public List<Horarios> horariosAlumno(@PathVariable int id) {
        return service.horariosAlumno(id);
    }

    @PostMapping
    public void crear(@RequestBody Horarios h) {
        service.crearHorario(h);
    }
}
