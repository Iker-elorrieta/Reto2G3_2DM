package modelo;


import java.io.Serializable;

public class HorariosDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer idModulo;
    private Integer idUser;
    private String dia;
    private byte hora;
    private String aula;
    private String observaciones;

    public HorariosDTO() {
    }

    public HorariosDTO(Integer id, Integer idModulo, Integer idUser, String dia, byte hora,
                       String aula, String observaciones) {
        this.id = id;
        this.idModulo = idModulo;
        this.idUser = idUser;
        this.dia = dia;
        this.hora = hora;
        this.aula = aula;
        this.observaciones = observaciones;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(Integer idModulo) {
        this.idModulo = idModulo;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public byte getHora() {
        return hora;
    }

    public void setHora(byte hora) {
        this.hora = hora;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
