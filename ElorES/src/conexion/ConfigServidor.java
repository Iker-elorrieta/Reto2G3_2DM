package conexion;

public class ConfigServidor {
	public static String IP = System.getenv("SERVER_IP");
	public static int PUERTO = Integer.parseInt(System.getenv("SERVER_PORT"));
}
