package com.example.ElorServ;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import modelo.Centro;

@RestController
@RequestMapping("/api/centros")
public class CentroController {

    private final CentroService service;

    public CentroController(CentroService service) {
        this.service = service;
    }

    @GetMapping
    public List<Centro> getCentros() {
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
