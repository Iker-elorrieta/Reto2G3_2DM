package Vista;

import java.awt.EventQueue;
import controlador.Controlador;

public class Principal {

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Controlador controlador = new Controlador();
                    Login frame = new Login(controlador);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
