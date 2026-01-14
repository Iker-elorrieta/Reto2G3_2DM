package sockets;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import modelo.Acciones;
import modelo.Peticion;
import modelo.Respuesta;
import modelo.Users;
import modelo.UsersDAO;
import modelo.UsersDTO;

public class HiloCliente extends Thread {

    private Socket socket;

    public HiloCliente(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            Peticion p = (Peticion) in.readObject();

            switch (p.getAccion()) {

                case Acciones.LOGIN:

                    Map<String, Object> datos = p.getDatos();
                    String usuario = (String) datos.get("user");
                    String password = (String) datos.get("pass");

                    UsersDAO dao = new UsersDAO();
                    Users u = dao.login(usuario, password);

                    Map<String, Object> respuesta = new HashMap<>();

                    if (u != null) {

                        UsersDTO dto = new UsersDTO();
                        dto.setUsername(u.getUsername());
                        dto.setNombre(u.getNombre());
                        dto.setTelefono2(u.getTelefono2());

                        respuesta.put("mensaje", "Login correcto");
                        respuesta.put("usuario", dto);

                        out.writeObject(new Respuesta(true, respuesta));

                    } else {

                        respuesta.put("mensaje", "Usuario o contraseña incorrectos");
                        out.writeObject(new Respuesta(false, respuesta));
                    }


                    break;

                default:
                    Map<String, Object> error = new HashMap<>();
                    error.put("mensaje", "Acción no reconocida");
                    out.writeObject(new Respuesta(false, error));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
