package modelo;

import java.io.Serializable;

public class ModulosDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer idCiclo;
    private String nombre;
    private String nombreEus;
    private int horas;
    private byte curso;

    public ModulosDTO() {
    }

    public ModulosDTO(Integer id, Integer idCiclo, String nombre, String nombreEus, int horas, byte curso) {
        this.id = id;
        this.idCiclo = idCiclo;
        this.nombre = nombre;
        this.nombreEus = nombreEus;
        this.horas = horas;
        this.curso = curso;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdCiclo() {
        return idCiclo;
    }

    public void setIdCiclo(Integer idCiclo) {
        this.idCiclo = idCiclo;
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
