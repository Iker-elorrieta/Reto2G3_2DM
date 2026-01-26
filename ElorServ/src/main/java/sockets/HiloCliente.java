package sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import controlador.ControladorServidor;
import modelo.Acciones;

public class HiloCliente extends Thread {

    private Socket socket;
    private ControladorServidor controlador = new ControladorServidor();

    public HiloCliente(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {

            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            

            while (true) {

                // LEER ACCIÓN COMO STRING (ObjectInputStream)
                String accion = (String) dis.readUTF();

                switch (accion) {

                    case Acciones.LOGIN:
                        controlador.login(dis, dos,oos,ois);
                        break;

                    case Acciones.GET_ALUMNOS:
                        controlador.getAlumnos(dis, dos,oos);
                        break;

                    case Acciones.GET_HORARIOS:
                        controlador.getHorarios(dis, dos,oos);
                        break;

                    case Acciones.GET_PROFESORES:
                        controlador.getProfesores(dis, dos,oos);
                        break;

                    default:
                        oos.writeObject("ERROR");
                        oos.writeObject("Acción no reconocida");
                        oos.flush();
                        break;
                }
            }

        } catch (Exception e) {
            System.out.println("Cliente desconectado");
        }
    }
}
