package controlador;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;

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

                ObjectInputStream ois = new ObjectInputStream(in);
                Users u = (Users) ois.readObject();

                usuario = u;  

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
   
   


}
