package modelo;

import java.io.Serializable;
import java.util.Map;

public class Respuesta implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean ok;
    private Map<String, Object> datos;

    public Respuesta() {
    }

    public Respuesta(boolean ok, Map<String, Object> datos) {
        this.ok = ok;
        this.datos = datos;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public Map<String, Object> getDatos() {
        return datos;
    }

    public void setDatos(Map<String, Object> datos) {
        this.datos = datos;
    }
}
