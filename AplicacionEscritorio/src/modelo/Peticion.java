package modelo;

import java.io.Serializable;

public class Peticion implements Serializable {

    private static final long serialVersionUID = 1L;

    private String accion;
    private Object datos;

    public Peticion() {
    }

    public Peticion(String accion, Object datos) {
        this.accion = accion;
        this.datos = datos;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public Object getDatos() {
        return datos;
    }

    public void setDatos(Object datos) {
        this.datos = datos;
    }
}
