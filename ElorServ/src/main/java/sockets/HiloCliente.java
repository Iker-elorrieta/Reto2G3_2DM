package sockets;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import modelo.Peticion;
import modelo.Respuesta;
import modelo.Acciones;

public class HiloCliente extends Thread {

    private Socket socket;

    public HiloCliente(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

            Peticion p = (Peticion) in.readObject();

            switch (p.getAccion()) {

                case Acciones.LOGIN:
                    out.writeObject(new Respuesta(true, "Usuario logueado correctamente"));
                    break;

                default:
                    out.writeObject(new Respuesta(false, "Error en el login"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
