package conexion;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ConexionServidor {

    private Socket socket;
	private ObjectOutputStream out ;
    private ObjectInputStream ois;

    public ConexionServidor() throws Exception {
        socket = new Socket("localhost", 5000);
        out = new ObjectOutputStream(socket.getOutputStream());
        ois = new ObjectInputStream(socket.getInputStream());
        out.flush();
        
    }

    public ObjectOutputStream getOut() {
        return out;
    }

 

    public ObjectInputStream getOis() {
        return ois;
    }

    public void cerrar() throws Exception {
        ois.close();
        out.close();
        socket.close();
    }

	
}

