package apiRestControllers;

import java.util.List;
import org.springframework.web.bind.annotation.*;

import apiDAO.LeerJson;
import modelo.Centro;

@RestController
@RequestMapping("/api/centros")
public class CentroController {

    private final LeerJson service;

    public CentroController(LeerJson service) {
        this.service = service;
    }

    @GetMapping 
    public List<Centro> getCentros() {            //EJECUCCION DE FUNCIONES DEPENDIENDO DE LO DESEADO 
        return service.getCentros();
    }

    @PostMapping
    public Centro crearCentro(@RequestBody Centro centro) {
        service.crearCentro(centro);
        return centro;
    }

    @PutMapping("/{id}")
    public Centro actualizarCentro(@PathVariable int id, @RequestBody Centro centro) {
        service.actualizarCentro(id, centro);
        return centro;
    }

    @DeleteMapping("/{id}")
    public void eliminarCentro(@PathVariable int id) {
        service.eliminarCentro(id);
    }
}
