package controlador;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import javax.swing.JFrame;

import Vista.Login;
import Vista.Menu;
import Vista.Horario;
import Vista.Alumnos;
import Vista.Reuniones;
import Vista.Perfil;

import conexion.ConexionServidor;
import modelo.Users;

public class Controlador {

    private ConexionServidor con;
    private DataOutputStream out;
    private DataInputStream in;
    Users usuario = new Users();

    public Controlador() {
        try {
            con = new ConexionServidor(); // CONEXION CON EL SERVIDOR
            out = con.getOut();
            in = con.getIn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean login(String usuariotexto, String contrasenatexto) {
        boolean resultado = false;
        try {
            // FUNCIÓN LOGIN EN LA CUAL DEPENDIENDO DE LO QUE SE RECIBA DEVUELVE UN BOOLEANO
            // SI SE HA INICIADO SESIÓN O NO.

            out.writeUTF("LOGIN");
            out.writeUTF(usuariotexto);
            out.writeUTF(contrasenatexto);

            String estado = in.readUTF();

            switch (estado) {

            case "OK": {
                resultado = true;

                usuario.setUsername(in.readUTF());
                usuario.setId(Integer.parseInt(in.readUTF()));
                usuario.setNombre(in.readUTF());
                usuario.setApellidos(in.readUTF());
                usuario.setEmail(in.readUTF());
                usuario.setDireccion(in.readUTF());
                usuario.setTelefono1(in.readUTF());
                usuario.setTelefono2(in.readUTF());
                usuario.setDni(in.readUTF());

                break;
            }

            case "ERROR": {
                // COMENTARIOS PROPIOS PARA COMPROBACIÓN DE QUE ERROR ES POR CONSOLA QUE NO
                // AFECTA A CLIENTE.
                // SI SIN PERMISOS O USUARIO INCORRECTO.
                String mensaje = in.readUTF();

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
    

    public Users leerUsuarioActual() {
        // FUNCIÓN PARA GUARDAR EL USUARIO ACTIVO EN LOCAL
        Users u = new Users();
        u.setUsername(usuario.getUsername());
        u.setId(usuario.getId());
        u.setNombre(usuario.getNombre());
        u.setApellidos(usuario.getApellidos());
        u.setEmail(usuario.getEmail());
        u.setDireccion(usuario.getDireccion());
        u.setTelefono1(usuario.getTelefono1());
        u.setTelefono2(usuario.getTelefono2());
        u.setDni(usuario.getDni());
        return u;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // MÉTODO GENERAL PARA CAMBIAR DE VISTA
    public void cambiarVista(JFrame vistaActual, JFrame vistaNueva) {
        vistaActual.setVisible(false);
        vistaNueva.setVisible(true);
        vistaActual.dispose();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // LOGIN

    // MÉTODO QUE GESTIONA EL INICIO DE SESIÓN DESDE LA VISTA LOGIN
    public void iniciarSesion(Login vista) {
        String usuarioTexto = vista.getUsuario();
        String contrasenaTexto = vista.getContrasena();

        if (login(usuarioTexto, contrasenaTexto)) {
            abrirMenu(vista);
        } else {
            vista.mostrarError("Usuario o contraseña incorrectos");
        }
    }

    // MÉTODO PARA ABRIR EL MENÚ
    public void abrirMenu(JFrame vista) {
        cambiarVista(vista, new Menu(this));
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // MENÚ

    // MÉTODO PARA ABRIR MI HORARIO
    public void abrirMiHorario(Menu vista) {
        cambiarVista(vista, new Horario(this));
    }

    // MÉTODO PARA ABRIR OTROS HORARIOS
    public void abrirOtrosHorarios(Menu vista) {
        cambiarVista(vista, new Horario(this));
    }

    // MÉTODO PARA ABRIR ALUMNOS
    public void abrirAlumnos(Menu vista) {
        cambiarVista(vista, new Alumnos(this));
    }

    // MÉTODO PARA ABRIR REUNIONES
    public void abrirReuniones(Menu vista) {
        cambiarVista(vista, new Reuniones(this));
    }

    // MÉTODO PARA ABRIR PERFIL
    public void abrirPerfil(Menu vista) {
        cambiarVista(vista, new Perfil(this));
    }

    // MÉTODO PARA CERRAR SESIÓN
    public void cerrarSesion(Menu vista) {
        cambiarVista(vista, new Login(this));
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // PERFIL

    // MÉTODO PARA RELLENAR LOS DATOS DEL PERFIL
    public void rellenarPerfil(Perfil vista) {
        Users u = leerUsuarioActual();

        vista.setUsuario(u.getUsername());
        vista.setNombre(u.getNombre());
        vista.setApellido(u.getApellidos());
        vista.setCorreo(u.getEmail());
        vista.setDireccion(u.getDireccion());
        vista.setTelefono1(u.getTelefono1());
        vista.setTelefono2(u.getTelefono2());
        vista.setDni(u.getDni());
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   
    //RECIBIR LISTA DE ALUMNOS
    
    public String[][] obtenerAlumnosDelServidor() {
        try {
            out.writeUTF("GET_ALUMNOS");

            String estado = in.readUTF();

            if (estado.equals("OK")) {
                int cantidad = Integer.parseInt(in.readUTF());
                String[][] datos = new String[cantidad][2];

                for (int i = 0; i < cantidad; i++) {
                    datos[i][0] = in.readUTF();
                    datos[i][1] = in.readUTF();
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

    //RECIBIR HORARIOS DE PROFESOR ELEGIDO
    public String[][] obtenerHorariosDelServidor() {
        try {
            out.writeUTF("GET_HORARIOS");
            out.writeUTF(String.valueOf(usuario.getId()));

            String estado = in.readUTF();

            if (estado.equals("OK")) {
                int cantidad = Integer.parseInt(in.readUTF());

                String[][] datos = new String[cantidad][4];

                for (int i = 0; i < cantidad; i++) {
                    datos[i][0] = in.readUTF(); // dia
                    datos[i][1] = in.readUTF(); // hora
                    datos[i][2] = in.readUTF(); // nombre del módulo
                    datos[i][3] = in.readUTF(); // aula
                }

                return datos;
            } else {
                String msg = in.readUTF();
                System.out.println("SERVIDOR RESPONDE ERROR: " + msg);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new String[0][0];
    }


    public void cargarHorarios(Horario vista) {
        String[][] horariosServidor = obtenerHorariosDelServidor();

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

            String contenido = modulo + " - " + aula;

            switch (dia.toUpperCase()) {
                case "LUNES": tabla[hora][1] = contenido; break;
                case "MARTES": tabla[hora][2] = contenido; break;
                case "MIERCOLES": tabla[hora][3] = contenido; break;
                case "JUEVES": tabla[hora][4] = contenido; break;
                case "VIERNES": tabla[hora][5] = contenido; break;
            }
        }

        vista.actualizarTablaHorarios(tabla);
    }



}
