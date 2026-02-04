package com.example.ElorServ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import controlador.ControladorServidor;
import sockets.Servidor;

import jakarta.annotation.PostConstruct;

@SpringBootApplication(scanBasePackages = {
        "com.example.ElorServ",
        "apiRestControllers",
        "apiRestServices",
        "apiDAO",
        "bd",
        "controlador",
        "modelo"
})
public class ElorServApplication {

    @Autowired
    private ControladorServidor controladorServidor;

    public static void main(String[] args) {
        SpringApplication.run(ElorServApplication.class, args);
    }

    @PostConstruct
    public void iniciarServidorSockets() {
        new Thread(() -> {
            try {
                Servidor.iniciar(controladorServidor);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
