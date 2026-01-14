package conexion;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import modelo.Peticion;
import modelo.Respuesta;

public class ConexionServidor {

    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public ConexionServidor() throws Exception {
        socket = new Socket("localhost", 5000);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
    }
    
    public Respuesta enviar(Peticion p) throws Exception {
        out.writeObject(p);
        out.flush();
        return (Respuesta) in.readObject();
    }
}
