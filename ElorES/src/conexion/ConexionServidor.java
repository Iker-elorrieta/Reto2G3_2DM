package conexion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ConexionServidor {

    private Socket socket;

    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    private DataOutputStream dos;
    private DataInputStream dis;

    public ConexionServidor() throws Exception {

        String ip = ConfigServidor.IP;
        int puerto = ConfigServidor.PUERTO;

        // Si no hay variables de entorno, usa localhost por defecto
        if (ip == null || ip.isEmpty()) {
            ip = "localhost";
        }
        if (puerto == 0) {
            puerto = 5000;
        }

        socket = new Socket(ip, puerto);

        dos = new DataOutputStream(socket.getOutputStream());
        dis = new DataInputStream(socket.getInputStream());

        oos = new ObjectOutputStream(socket.getOutputStream());
        oos.flush();

        ois = new ObjectInputStream(socket.getInputStream());
    }


    public ObjectOutputStream getOos() {
        return oos;
    }

    public ObjectInputStream getOis() {
        return ois;
    }

    public DataOutputStream getDos() {
        return dos;
    }

    public DataInputStream getDis() {
        return dis;
    }

    public void cerrar() throws Exception {
        dis.close();
        dos.close();
        ois.close();
        oos.close();
        socket.close();
    }
}
