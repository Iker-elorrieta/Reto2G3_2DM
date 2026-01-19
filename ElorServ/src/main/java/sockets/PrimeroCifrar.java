package sockets;

import modelo.UsersDAO;

public class PrimeroCifrar {
	
    public static void main(String[] args) throws Exception {

	 UsersDAO dao = new UsersDAO();
     dao.encriptarTodasLasContrasenas();

}
}
