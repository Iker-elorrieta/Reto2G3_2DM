package modelo;

import java.io.Serializable;
import java.util.Map;

public class Peticion implements Serializable {

    private static final long serialVersionUID = 1L;

    private String accion;
    private Map<String, Object> datos;

    public Peticion() {
    }

    public Peticion(String accion, Map<String, Object> datos) {
        this.accion = accion;
        this.datos = datos;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public Map<String, Object> getDatos() {
        return datos;
    }

    public void setDatos(Map<String, Object> datos) {
        this.datos = datos;
    }
}
