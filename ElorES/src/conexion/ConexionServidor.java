package conexion;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import java.net.Socket;

public class ConexionServidor {

    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;

    public ConexionServidor() throws Exception {
        socket = new Socket("localhost", 5000);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        out.flush();
        
    }

    public DataOutputStream getOut() {
        return out;
    }

    public DataInputStream getIn() {
        return in;
    }

    public void cerrar() throws Exception {
        in.close();
        out.close();
        socket.close();
    }
}
