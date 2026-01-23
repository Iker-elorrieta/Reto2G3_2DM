package sockets;


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
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            

            while (true) { 
                String accion = (String)ois.readObject();

                switch (accion) { // SWITCH PARA ACCIONES EN EL SERVIDOR DEPENDIENDO DE LO QUE PIDA EL CLIENTE

                    case Acciones.LOGIN:
                       controlador.login(ois,oos);
                    
                        break;

                    case Acciones.GET_ALUMNOS:
						controlador.getAlumnos(ois,oos);
						break;
                    case Acciones.GET_HORARIOS:
                        controlador.getHorarios(ois, oos);
                        break;
                    case Acciones.GET_PROFESORES:
                        controlador.getProfesores(ois,oos);
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
