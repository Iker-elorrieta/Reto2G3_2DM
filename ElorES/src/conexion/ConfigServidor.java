package conexion;

public class ConfigServidor {

    public static String IP;
    public static int PUERTO;

    static {
        String ipEnv = System.getenv("SERVER_IP");
        String portEnv = System.getenv("SERVER_PORT");

        // Si no hay variables → usar localhost:5000
        IP = (ipEnv != null && !ipEnv.isEmpty()) ? ipEnv : "localhost";

        if (portEnv != null && !portEnv.isEmpty()) {
            PUERTO = Integer.parseInt(portEnv);
        } else {
            PUERTO = 5000;
        }
    }
}
