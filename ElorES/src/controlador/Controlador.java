package controlador;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Arrays;

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

                Users u = (Users) con.getOis().readObject();


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
        cambiarVista(vista, new OtrosHorarios(this));
        
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
   
    //RECIBIR LISTA DE ALUMNOS
    
    public String[][] obtenerAlumnosDelServidor() {
        try {
            out.writeUTF("GET_ALUMNOS");

            String estado = in.readUTF();

            if (estado.equals("OK")) {

            	Users[] lista = (Users[]) con.getOis().readObject();


                String[][] datos = new String[lista.length][2];

                for (int i = 0; i < lista.length; i++) {
                    datos[i][0] = lista[i].getNombre();
                    datos[i][1] = lista[i].getApellidos();
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
    public String[][] obtenerHorariosDelServidor(int idProfesor) {
        try {
            out.writeUTF("GET_HORARIOS");
            out.writeUTF(String.valueOf(idProfesor));

            String estado = in.readUTF();

            if (estado.equals("OK")) {

                Horarios[] lista = (Horarios[]) con.getOis().readObject();

                String[][] datos = new String[lista.length][4];

                for (int i = 0; i < lista.length; i++) {
                    datos[i][0] = lista[i].getDia();
                    datos[i][1] = String.valueOf(lista[i].getHora());
                    datos[i][2] = lista[i].getModulos().getNombre();
                    datos[i][3] = lista[i].getAula();
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
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public Users[] obtenerProfesores() {
        try {
            out.writeUTF("GET_PROFESORES");

            String estado = in.readUTF();

            if (estado.equals("OK")) {

                Users[] lista = (Users[]) con.getOis().readObject();

                // Filtrar para excluir al usuario logueado
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

		//CARGAR HORARIOS EN LA VISTA CORRESPONDIENTE

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




