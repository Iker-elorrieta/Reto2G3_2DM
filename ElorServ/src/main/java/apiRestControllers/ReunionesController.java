package apiRestControllers;

import org.springframework.web.bind.annotation.RestController;

import apiRestServices.ReunionesJsonService;
import modelo.Reuniones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController
@RequestMapping("/api/reuniones")
public class ReunionesController {

    @Autowired
    private ReunionesJsonService service;

    @GetMapping
    public List<Reuniones> listar() {
        return service.listarReuniones();
    }

    @PostMapping
    public void crear(@RequestBody Reuniones r) {
        service.crearReunion(r);
    }

    @PutMapping("/{id}")
    public void actualizar(@PathVariable int id, @RequestBody Reuniones r) {
        service.actualizarReunion(id, r);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        service.eliminarReunion(id);
    }
}
