package conexion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ConexionServidor {

    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private ObjectInputStream ois;

    public ConexionServidor() throws Exception {
        socket = new Socket("localhost", 5000);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        ois = new ObjectInputStream(socket.getInputStream());
        out.flush();
        
    }

    public DataOutputStream getOut() {
        return out;
    }

    public DataInputStream getIn() {
        return in;
    }

    public ObjectInputStream getOis() {
        return ois;
    }

    public void cerrar() throws Exception {
        in.close();
        out.close();
        socket.close();
    }
}

