package controlador;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;

import Vista.Login;
import Vista.Menu;
import Vista.OtrosHorarios;
import Vista.Horario;
import Vista.Alumnos;
import Vista.Reuniones;
import Vista.Perfil;

import conexion.ConexionServidor;
import modelo.Horarios;
import modelo.Users;

public class Controlador {

    private ConexionServidor con;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    Users usuario = new Users();

    public Controlador() {
        try {
            con = new ConexionServidor();
            out = con.getOut();
            in = con.getOis();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean login(String usuariotexto, String contrasenatexto) {
        boolean resultado = false;
        try {
            out.writeObject("LOGIN");
            out.writeObject(usuariotexto);
            out.writeObject(contrasenatexto);

            String estado = (String) in.readObject();

            switch (estado) {

            case "OK": {
                resultado = true;

                Users u = (Users) in.readObject();
                usuario = u;
                break;
            }

            case "ERROR": {
                String mensaje = (String) in.readObject();
                System.out.println(mensaje);
                break;
            }

            default:
                System.out.println("RESPUESTA DESCONOCIDA DEL SERVIDOR");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void cambiarVista(JFrame vistaActual, JFrame vistaNueva) {
        vistaActual.setVisible(false);
        vistaNueva.setVisible(true);
        vistaActual.dispose();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void iniciarSesion(Login vista) {
        String usuarioTexto = vista.getUsuario();
        String contrasenaTexto = vista.getContrasena();

        if (login(usuarioTexto, contrasenaTexto)) {
            abrirMenu(vista);
        } else {
            vista.mostrarError("Usuario o contraseña incorrectos");
        }
    }

    public void abrirMenu(JFrame vista) {
        cambiarVista(vista, new Menu(this));
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void abrirMiHorario(Menu vista) {
        cambiarVista(vista, new Horario(this));
    }

    public void abrirOtrosHorarios(Menu vista) {
        cambiarVista(vista, new OtrosHorarios(this));
    }

    public void abrirAlumnos(Menu vista) {
        cambiarVista(vista, new Alumnos(this));
    }

    public void abrirReuniones(Menu vista) {
        cambiarVista(vista, new Reuniones(this));
    }

    public void abrirPerfil(Menu vista) {
        cambiarVista(vista, new Perfil(this));
    }

    public void cerrarSesion(Menu vista) {
        cambiarVista(vista, new Login(this));
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void rellenarPerfil(Perfil vista) {
        vista.setUsuario(usuario.getUsername());
        vista.setNombre(usuario.getNombre());
        vista.setApellido(usuario.getApellidos());
        vista.setCorreo(usuario.getEmail());
        vista.setDireccion(usuario.getDireccion());
        vista.setTelefono1(usuario.getTelefono1());
        vista.setTelefono2(usuario.getTelefono2());
        vista.setDni(usuario.getDni());
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public String[][] obtenerAlumnosDelServidor() {
        try {
            out.writeObject("GET_ALUMNOS");
            out.writeObject(String.valueOf(usuario.getId()));

            String estado = (String) in.readObject();

            if (estado.equals("OK")) {

                Users[] lista = (Users[]) in.readObject();

                String[][] datos = new String[lista.length][7];

                for (int i = 0; i < lista.length; i++) {
                    datos[i][0] = lista[i].getNombre();
                    datos[i][1] = lista[i].getApellidos();
                    datos[i][2] = lista[i].getEmail();
                    datos[i][3] = lista[i].getTelefono1();
                    datos[i][4] = lista[i].getTelefono2();
                    datos[i][5] = lista[i].getDireccion();
                    datos[i][6] = lista[i].getDni();
                }

                return datos;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new String[0][0];
    }

    public void cargarAlumnos(Alumnos vista) {
        String[][] datos = obtenerAlumnosDelServidor();
        vista.actualizarTabla(datos);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public String[][] obtenerHorariosDelServidor(int idProfesor) {
        try {
            out.writeObject("GET_HORARIOS");
            out.writeObject(idProfesor);

            String estado = (String) in.readObject();

            if (estado.equals("OK")) {

            	Object recibido = in.readObject();

            	if (recibido instanceof List<?>) {

            	    List<?> listaGenerica = (List<?>) recibido;

            	    ArrayList<Horarios> listaHorarios = new ArrayList<>();

            	    for (Object o : listaGenerica) {
            	        if (o instanceof Horarios h) {
            	            listaHorarios.add(h);
            	        }
            	    }

            	    String[][] datos = new String[listaHorarios.size()][4];

            	    for (int i = 0; i < listaHorarios.size(); i++) {
            	        Horarios h = listaHorarios.get(i);
            	        datos[i][0] = h.getDia();
            	        datos[i][1] = String.valueOf(h.getHora());
            	        datos[i][2] = h.getModulos().getNombre();
            	        datos[i][3] = h.getAula();
            	    }

            	    return datos;
            	}

            } else {
                String msg = (String) in.readObject();
                System.out.println("SERVIDOR RESPONDE ERROR: " + msg);
                return new String[0][0]; 
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new String[0][0];
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public Users[] obtenerProfesores() {
        try {
            out.writeObject("GET_PROFESORES");

            String estado = (String) in.readObject();

            if (estado.equals("OK")) {

                Users[] lista = (Users[]) in.readObject();

                return Arrays.stream(lista)
                        .filter(u -> !u.getId().equals(usuario.getId()))
                        .toArray(Users[]::new);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Users[0];
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void cargarHorarios(Horario vista) {
        String[][] horariosServidor = obtenerHorariosDelServidor(usuario.getId());
        cargarHorarios(vista, horariosServidor);
    }

    public void cargarHorarios(OtrosHorarios vista) {
        int idProfesor = vista.getProfesorSeleccionado();
        String[][] horariosServidor = obtenerHorariosDelServidor(idProfesor);
        cargarHorarios(vista, horariosServidor);
    }

    private void cargarHorarios(Object vista, String[][] horariosServidor) {
        String[][] tabla = new String[6][6];

        for (int i = 0; i < 6; i++) {
            tabla[i][0] = String.valueOf(i + 1);
            tabla[i][1] = "";
            tabla[i][2] = "";
            tabla[i][3] = "";
            tabla[i][4] = "";
            tabla[i][5] = "";
        }

        for (String[] h : horariosServidor) {
            String dia = h[0];
            int hora = Integer.parseInt(h[1]) - 1;
            String modulo = h[2];
            String aula = h[3];

            String contenido = modulo + " \n " + aula;

            switch (dia.toUpperCase()) {
                case "LUNES": tabla[hora][1] = contenido; break;
                case "MARTES": tabla[hora][2] = contenido; break;
                case "MIERCOLES": tabla[hora][3] = contenido; break;
                case "JUEVES": tabla[hora][4] = contenido; break;
                case "VIERNES": tabla[hora][5] = contenido; break;
            }
        }

        if (vista instanceof Horario h) h.actualizarTablaHorarios(tabla);
        if (vista instanceof OtrosHorarios o) o.actualizarTablaHorarios(tabla);
    }

}
