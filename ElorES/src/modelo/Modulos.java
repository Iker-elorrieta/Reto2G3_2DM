package modelo;

import java.io.Serializable;

public class Modulos implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Ciclo ciclos;   
    private String nombre;
    private String nombreEus;
    private int horas;
    private byte curso;

    public Modulos() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Ciclo getCiclos() {
        return ciclos;
    }

    public void setCiclos(Ciclo ciclos) {
        this.ciclos = ciclos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreEus() {
        return nombreEus;
    }

    public void setNombreEus(String nombreEus) {
        this.nombreEus = nombreEus;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public byte getCurso() {
        return curso;
    }

    public void setCurso(byte curso) {
        this.curso = curso;
    }
}
