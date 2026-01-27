package com.example.ElorServ;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sockets.Servidor;

@SpringBootApplication(scanBasePackages = {
	    "com.example.ElorServ",
	    "apiRestControllers",
	    "apiRestServices",
	    "apiDAO"
	})

public class ElorServApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElorServApplication.class, args);

        new Thread(() -> {                // INICIAR EL SERVIDOR EN UN HILO SEPARADO PARA QUE FUNCIONE CON SPRINGBOOT A LA VEZ
            try {
                Servidor.iniciar();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
