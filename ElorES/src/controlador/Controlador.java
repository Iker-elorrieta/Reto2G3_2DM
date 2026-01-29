package controlador;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Timestamp;
import java.text.Normalizer;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.JFrame;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Vista.Login;
import Vista.Menu;
import Vista.OtrosHorarios;
import Vista.Horario;
import Vista.Alumnos;
import Vista.DialogCrearReunion;
import Vista.VistaReuniones;
import Vista.Perfil;

import conexion.ConexionServidor;
import modelo.Centro;
import modelo.Horarios;
import modelo.Users;
import modelo.Reuniones;

public class Controlador {

	private ConexionServidor con;
	private DataOutputStream dos;
	private DataInputStream dis;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	Users usuario = new Users();
	private ArrayList<Reuniones> listaReuniones;

	public Controlador() {
		try {
			con = new ConexionServidor();
			dos = con.getDos();
			dis = con.getDis();
			out = con.getOos();
			in = con.getOis();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean login(String usuariotexto, String contrasenatexto) {
	    boolean resultado = false;
	    try {
	        dos.writeUTF("LOGIN");
	        dos.writeUTF(usuariotexto);
	        dos.writeUTF(contrasenatexto);
	        dos.flush();

	        String estado = dis.readUTF();

	        switch (estado) {

	            case "OK": {
	                resultado = true;

	                Users u = (Users) in.readObject();
	                usuario = u;
	                break;
	            }

	            case "ERROR": {
	                String mensaje = dis.readUTF();
	                System.out.println(mensaje);
	                break;
	            }
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
		cambiarVista(vista, new VistaReuniones(this));
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
	        dos.writeUTF("GET_ALUMNOS");
	        dos.writeInt(usuario.getId());
	        dos.flush();

	        String estado = dis.readUTF();

	        if (estado.equals("OK")) {

	            Object recibido = in.readObject();

	            if (recibido instanceof List<?>) {
	                List<?> listaGenerica = (List<?>) recibido;
	                ArrayList<Users> lista = new ArrayList<>();

	                for (Object o : listaGenerica) {
	                    if (o instanceof Users u) lista.add(u);
	                }

	                String[][] datos = new String[lista.size()][7];

	                for (int i = 0; i < lista.size(); i++) {
	                    Users u = lista.get(i);
	                    datos[i][0] = u.getNombre();
	                    datos[i][1] = u.getApellidos();
	                    datos[i][2] = u.getEmail();
	                    datos[i][3] = u.getTelefono1();
	                    datos[i][4] = u.getTelefono2();
	                    datos[i][5] = u.getDireccion();
	                    datos[i][6] = u.getDni();
	                }

	                return datos;
	            }
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return new String[0][0];
	}
	public ArrayList<Users> cargarAlumnosDialog(DialogCrearReunion vista) {
        ArrayList<Users> lista = new ArrayList<>();

		  try {
			dos.writeUTF("GET_ALUMNOS");
		 
	        dos.writeInt(usuario.getId());
	        dos.flush();

	        String estado = dis.readUTF();

	        if (estado.equals("OK")) {

	            Object recibido = in.readObject();

	            if (recibido instanceof List<?>) {
	                List<?> listaGenerica = (List<?>) recibido;

	                for (Object o : listaGenerica) {
	                    if (o instanceof Users u) lista.add(u);
	                }
	            }
	            else {
	            	System.out.println("Error cargando usuarios");
	            }
	        }else {
	        	System.out.println("No se han podido extraer los alumnos");
	        }
		  } catch (IOException e) {
				e.printStackTrace();

		  } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		  return lista;
	}
	public ArrayList<Reuniones> obtenerReuniones() {
	    ArrayList<Reuniones> reuniones = new ArrayList<>();

	    try {
	        dos.writeUTF("GET_REUNIONES");
	        dos.writeInt(usuario.getId());
	        dos.flush();

	        String estado = dis.readUTF();

	        if (estado.equals("OK")) {

	            String json = dis.readUTF();

	            Gson gson = new Gson();
	            List<Reuniones> lista = gson.fromJson(
	                json,
	                new TypeToken<List<Reuniones>>(){}.getType()
	            );

	            reuniones.addAll(lista);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return reuniones;
	}

	private String nombreCentro(String idCentro, ArrayList<Centro> centros) {
	    for (Centro c : centros) {
	        if (String.valueOf(c.getCCEN()).equals(idCentro)) {
	            return c.getNOM();
	        }
	    }
	    return "Centro desconocido";
	}

	public void cargarHorariosyReuniones(VistaReuniones vista) {

	    ArrayList<Centro> centros = obtenerCentros();
	    listaReuniones = obtenerReuniones();
	    String[][] horario = obtenerHorariosDelServidor(usuario.getId());

	    String[][] tabla = new String[6][6];
	    String[][] colores = new String[6][6];

	    // Inicializar tabla y colores
	    for (int i = 0; i < 6; i++) {
	        tabla[i][0] = String.valueOf(i + 1);
	        colores[i][0] = "BLANCO";

	        for (int j = 1; j < 6; j++) {
	            tabla[i][j] = "";
	            colores[i][j] = "BLANCO";
	        }
	    }

	    // Rellenar horario (clases)
	    for (String[] h : horario) {
	        String dia = h[0];
	        int hora = Integer.parseInt(h[1]) - 1;
	        String modulo = h[2];
	        String aula = h[3];

	        String contenido = modulo + "\n" + aula;

	        switch (dia.toUpperCase()) {
	            case "LUNES": tabla[hora][1] = contenido; break;
	            case "MARTES": tabla[hora][2] = contenido; break;
	            case "MIERCOLES": tabla[hora][3] = contenido; break;
	            case "JUEVES": tabla[hora][4] = contenido; break;
	            case "VIERNES": tabla[hora][5] = contenido; break;
	        }
	    }

	    // Mezclar reuniones
	    Locale locale = Locale.forLanguageTag("es-ES");

	    for (Reuniones r : listaReuniones) {

	        LocalDateTime fecha = r.getFecha().toLocalDateTime();

	        String dia = fecha.getDayOfWeek()
	                          .getDisplayName(TextStyle.FULL, locale)
	                          .toUpperCase();

	        dia = Normalizer.normalize(dia, Normalizer.Form.NFD).replaceAll("\\p{M}", "");

	        int horaReal = fecha.getHour();
	        int hora = horaReal - 7;

	        if (hora < 0 || hora >= 6) continue;

	        String nombreCentro = nombreCentro(r.getIdCentro(), centros);
	        String reunionTexto = r.getTitulo() + "\n" + nombreCentro;

	        int col = switch (dia) {
	            case "LUNES" -> 1;
	            case "MARTES" -> 2;
	            case "MIERCOLES" -> 3;
	            case "JUEVES" -> 4;
	            case "VIERNES" -> 5;
	            default -> -1;
	        };

	        if (col == -1) continue;

	        boolean hayClase = !tabla[hora][col].isBlank();

	        // Determinar color según estado
	        String estado = r.getEstado().trim().toUpperCase();  // pendiente / aceptada / rechazada

	        if (hayClase) {
	            // Conflicto detectado
	            tabla[hora][col] = tabla[hora][col] + "\n" + reunionTexto;
	            colores[hora][col] = "GRIS";

	        } else {
	            tabla[hora][col] = reunionTexto;

	            switch (estado) {
	                case "PENDIENTE": colores[hora][col] = "AMARILLO"; break;
	                case "ACEPTADA": colores[hora][col] = "VERDE"; break;
	                case "DENEGADA": colores[hora][col] = "ROJO"; break;
	                case "CONFLICTO": colores[hora][col] = "GRIS"; break;
	                default: colores[hora][col] = "AMARILLO"; break;
	            }
	        }

	    }

	    vista.actualizarTablaHorarios(tabla, colores);
	}

	public Reuniones buscarReunionPorTexto(String textoCelda) {

	    for (Reuniones r : listaReuniones) {

	        String centro = nombreCentro(r.getIdCentro(), obtenerCentros());
	        String reunionTexto = r.getTitulo() + "\n" + centro;

	        // Si la celda contiene el texto de la reunión, es esta
	        if (textoCelda.contains(reunionTexto)) {
	            return r;
	        }


	    
	}
	    return null;
	}


	public void cargarAlumnos(Alumnos vista) {
		String[][] datos = obtenerAlumnosDelServidor();
		vista.actualizarTabla(datos);
	}

	public List<Centro> pedirCentros() {
		ArrayList<Centro> listaCentros = new ArrayList<>();

		try {
			out.writeUTF("GET_CENTROS");
			out.flush();

			Object recibido = in.readObject();

			if (recibido instanceof List<?>) {

				List<?> listaGenerica = (List<?>) recibido;

				for (Object o : listaGenerica) {
					if (o instanceof Centro c) {
						listaCentros.add(c);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			listaCentros = null;
		}
		return listaCentros;

	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public String[][] obtenerHorariosDelServidor(int idProfesor) {
	    try {
	        dos.writeUTF("GET_HORARIOS");
	        dos.writeInt(idProfesor);
	        dos.flush();

	        String estado = dis.readUTF();

	        if (!estado.equals("OK")) {
	            String msg = dis.readUTF();
	            System.out.println("SERVIDOR RESPONDE ERROR: " + msg);
	            return new String[0][0];
	        }

	        String json = dis.readUTF();

	        Gson gson = new Gson();
	        List<Horarios> listaHorarios = gson.fromJson(
	                json,
	                new TypeToken<List<Horarios>>(){}.getType()
	        );

	        String[][] datos = new String[listaHorarios.size()][4];

	        for (int i = 0; i < listaHorarios.size(); i++) {
	            Horarios h = listaHorarios.get(i);
	            datos[i][0] = h.getDia();
	            datos[i][1] = String.valueOf(h.getHora());
	            datos[i][2] = h.getModulos().getNombre();
	            datos[i][3] = (h.getAula() == null) ? "" : h.getAula();
	        }

	        return datos;

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return new String[0][0];
	}


	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public Users[] obtenerProfesores() {
	    try {
	        dos.writeUTF("GET_PROFESORES");
	        dos.flush();

	        String estado = dis.readUTF();

	        if (estado.equals("OK")) {

	            Object recibido = in.readObject();

	            if (recibido instanceof List<?>) {
	                List<?> listaGenerica = (List<?>) recibido;
	                ArrayList<Users> lista = new ArrayList<>();

	                for (Object o : listaGenerica) {
	                    if (o instanceof Users u) lista.add(u);
	                }

	                return lista.stream()
	                        .filter(u -> u.getId() != usuario.getId())
	                        .toArray(Users[]::new);
	            }
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return new Users[0];
	}
	public ArrayList<Centro> obtenerCentros() {
		ArrayList<Centro> lista = new ArrayList<>();
	    try {
            
	        dos.writeUTF("GET_CENTROS");
	        dos.flush();

	        String estado = dis.readUTF();

	        if (estado.equals("OK")) {

	            Object recibido = in.readObject();

	            if (recibido instanceof List<?>) {
	                List<?> listaGenerica = (List<?>) recibido;

	                for (Object o : listaGenerica) {
	                    if (o instanceof Centro c) lista.add(c);
	                }
	            }
	        }else {
	        	System.out.println("No se han cargado los Centros en cliente");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return lista;
	}

	public boolean actualizarEstadoReunionPorId(int idReunion, String estado) {
	    try {
	        dos.writeUTF("ACTUALIZAR_ESTADO_REUNION");
	        dos.writeInt(idReunion);
	        dos.writeUTF(estado);
	        dos.flush();

	        String respuesta = dis.readUTF();
	        return respuesta.equals("OK");

	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
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
			case "LUNES":
				tabla[hora][1] = contenido;
				break;
			case "MARTES":
				tabla[hora][2] = contenido;
				break;
			case "MIERCOLES":
				tabla[hora][3] = contenido;
				break;
			case "JUEVES":
				tabla[hora][4] = contenido;
				break;
			case "VIERNES":
				tabla[hora][5] = contenido;
				break;
			}
		}

		if (vista instanceof Horario h)
			h.actualizarTablaHorarios(tabla);
		if (vista instanceof OtrosHorarios o)
			o.actualizarTablaHorarios(tabla);

	}
	public boolean crearReunion(Reuniones r) {
	    try {
	        dos.writeUTF("CREAR_REUNION");
	        dos.flush();

	        out.reset();
	        out.writeObject(r);
	        out.flush();

	        String respuesta = dis.readUTF();
	        return respuesta.equals("OK");

	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	public Reuniones construirReunionDesdeDialog(
	        String textoDiaHora,
	        String titulo,
	        String tema,
	        String aula,
	        Centro centro,
	        Users alumno
	) {
	    try {
	        // textoDiaHora = "Lunes - 3"
	        String[] partes = textoDiaHora.split(" - ");
	        String dia = partes[0];
	        int hora = Integer.parseInt(partes[1]);

	        // Convertir día + hora a LocalDateTime
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(new Date());

	        String[] dias = {"Domingo","Lunes","Martes","Miércoles","Jueves","Viernes","Sábado"};
	        int indice = -1;
	        for (int i = 0; i < dias.length; i++) {
	            if (dias[i].equalsIgnoreCase(dia)) indice = i;
	        }

	        cal.set(Calendar.DAY_OF_WEEK, indice + 1);
	        cal.set(Calendar.HOUR_OF_DAY, 7 + (hora - 1));
	        cal.set(Calendar.MINUTE, 0);
	        cal.set(Calendar.SECOND, 0);

	        LocalDateTime fecha = cal.toInstant()
	                                 .atZone(ZoneId.systemDefault())
	                                 .toLocalDateTime();

	        Reuniones r = new Reuniones();
	        r.setTitulo(titulo);
	        r.setAsunto(tema);
	        r.setAula(aula);
	        r.setFecha(Timestamp.valueOf(fecha));
	        r.setEstado("PENDIENTE");
	        r.setUsersByProfesorId(usuario);
	        r.setUsersByAlumnoId(alumno);
	        r.setIdCentro(String.valueOf(centro.getCCEN()));

	        return r;

	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}



}
