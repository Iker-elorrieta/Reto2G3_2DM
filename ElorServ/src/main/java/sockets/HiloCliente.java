package sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import controlador.ControladorServidor;



public class HiloCliente extends Thread {

    private Socket socket;
    private ControladorServidor controlador = new ControladorServidor();

    public HiloCliente(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            DataInputStream in = new DataInputStream(socket.getInputStream());  //CREACION SOCKET
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            

            while (true) { 
                String accion = in.readUTF();

                switch (accion) { // SWITCH PARA ACCIONES EN EL SERVIDOR DEPENDIENDO DE LO QUE PIDA EL CLIENTE

                    case "LOGIN":
                       controlador.login(in, out);
                    
                        break;

                   
                    default:
                        out.writeUTF("ERROR");
                        out.writeUTF("Acción no reconocida");
                        out.flush();
                        break;
                }
            }

        } catch (Exception e) {
            System.out.println("Cliente desconectado");
        }
    }

}
