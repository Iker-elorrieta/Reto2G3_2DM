package modelo;

import java.io.Serializable;
import java.sql.Timestamp;

public class ReunionesDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idReunion;
    private Integer idAlumno;
    private Integer idProfesor;
    private String estado;
    private String estadoEus;
    private String idCentro;
    private String titulo;
    private String asunto;
    private String aula;
    private Timestamp fecha;

    public ReunionesDTO() {
    }

    public ReunionesDTO(Integer idReunion, Integer idAlumno, Integer idProfesor, String estado,
                        String estadoEus, String idCentro, String titulo, String asunto,
                        String aula, Timestamp fecha) {
        this.idReunion = idReunion;
        this.idAlumno = idAlumno;
        this.idProfesor = idProfesor;
        this.estado = estado;
        this.estadoEus = estadoEus;
        this.idCentro = idCentro;
        this.titulo = titulo;
        this.asunto = asunto;
        this.aula = aula;
        this.fecha = fecha;
    }

    public Integer getIdReunion() {
        return idReunion;
    }

    public void setIdReunion(Integer idReunion) {
        this.idReunion = idReunion;
    }

    public Integer getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Integer idAlumno) {
        this.idAlumno = idAlumno;
    }

    public Integer getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(Integer idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstadoEus() {
        return estadoEus;
    }

    public void setEstadoEus(String estadoEus) {
        this.estadoEus = estadoEus;
    }

    public String getIdCentro() {
        return idCentro;
    }

    public void setIdCentro(String idCentro) {
        this.idCentro = idCentro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }
}
