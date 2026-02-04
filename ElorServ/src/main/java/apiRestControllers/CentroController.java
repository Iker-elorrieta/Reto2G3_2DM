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

}
