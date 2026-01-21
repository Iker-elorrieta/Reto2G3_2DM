package apiRestControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import apiRestServices.UsersJsonService;
import modelo.Users;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsersJsonService service;

    @GetMapping
    public List<Users> listar() {
        return service.listarUsuariosJson();
    }

    @PostMapping
    public void crear(@RequestBody Users u) {
        service.crearUsuario(u);
    }

    @PutMapping("/{id}")
    public void actualizar(@PathVariable int id, @RequestBody Users u) {
        service.actualizarUsuario(id, u);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        service.eliminarUsuario(id);
    }
}


