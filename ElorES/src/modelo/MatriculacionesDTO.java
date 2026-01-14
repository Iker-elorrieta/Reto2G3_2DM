package modelo;

import java.io.Serializable;
import java.sql.Date;

public class MatriculacionesDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer idUser;
    private Integer idCiclo;
    private byte curso;
    private Date fecha;

    public MatriculacionesDTO() {
    }

    public MatriculacionesDTO(Integer id, Integer idUser, Integer idCiclo, byte curso, Date fecha) {
        this.id = id;
        this.idUser = idUser;
        this.idCiclo = idCiclo;
        this.curso = curso;
        this.fecha = fecha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdCiclo() {
        return idCiclo;
    }

    public void setIdCiclo(Integer idCiclo) {
        this.idCiclo = idCiclo;
    }

    public byte getCurso() {
        return curso;
    }

    public void setCurso(byte curso) {
        this.curso = curso;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}

