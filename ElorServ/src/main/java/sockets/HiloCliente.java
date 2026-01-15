package sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import modelo.Users;
import modelo.UsersDAO;

public class HiloCliente extends Thread {

    private Socket socket;

    public HiloCliente(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
        	DataInputStream in = new DataInputStream(socket.getInputStream());
        	DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        	

        	String accion = in.readUTF();
        	System.out.println(accion);
        	switch (accion) {

        	    case "LOGIN": {
        	        String user = in.readUTF();
        	        String pass = in.readUTF();

        	        UsersDAO dao = new UsersDAO();
        	        Users u = dao.login(user, pass);

        	        if (u != null) {
        	            out.writeUTF("OK");
        	            out.writeUTF(u.getUsername());
        	            out.writeUTF(u.getNombre());
        	            out.writeUTF(u.getTelefono2());
        	        } else {
        	            out.writeUTF("ERROR");
        	            out.writeUTF("Usuario o contraseña incorrectos");
        	        }
        	        break;
        	    }


        	    default:
        	        out.writeUTF("ERROR");
        	        out.writeUTF("Acción no reconocida");
        	        break;
        	}
        } catch (Exception e) {
			e.printStackTrace();
    }
}
}
