package modelo;

import java.io.Serializable;

public class Respuesta implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean ok;
    private Object datos;

    public Respuesta() {
    }

    public Respuesta(boolean ok, Object datos) {
        this.ok = ok;
        this.datos = datos;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public Object getDatos() {
        return datos;
    }

    public void setDatos(Object datos) {
        this.datos = datos;
    }
}
