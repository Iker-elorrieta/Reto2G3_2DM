package apiRestServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import apiDAO.ApiDAO;
import modelo.Users;

@Service
public class UsersJsonService {

    @Autowired
    private ApiDAO dao;

    public List<Users> listarUsuariosJson() {
        return dao.getAllUsers();
    }

    public Users login(String email, String password) {
        return dao.login(email, password);
    }

    public void crearUsuario(Users u) {
        dao.insertUser(u);
    }

    public void actualizarUsuario(int id, Users u) {
        u.setId(id);
        dao.updateUser(u);
    }

    public void eliminarUsuario(int id) {
        dao.deleteUser(id);
    }
}
