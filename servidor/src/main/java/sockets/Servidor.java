package sockets;

import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(5000);
        System.out.println("Servidor iniciado");

        while (true) {
            Socket cliente = server.accept();
            new HiloCliente(cliente).start();
        }
    }
}
