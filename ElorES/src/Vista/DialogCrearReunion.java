package Vista;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSpinner;

import com.toedter.calendar.JCalendar;

import controlador.Controlador;
import modelo.Centro;
import modelo.Users;

public class DialogCrearReunion extends JDialog {

    private static final long serialVersionUID = 1L;

    public DialogCrearReunion(JFrame parent, int hora, String dia,
                              Controlador controlador) {

        super(parent, "Crear reunión", true);
        setSize(450, 450);
        setLocationRelativeTo(parent);
        getContentPane().setLayout(null);
        ArrayList<Centro> centros = controlador.obtenerCentros();

        ArrayList<Users> alumnos = controlador.cargarAlumnosDialog(this);

        JLabel lblDiaHora = new JLabel("Día y hora:");
        lblDiaHora.setBounds(20, 20, 120, 25);
        getContentPane().add(lblDiaHora);

        JLabel lblDiaHoraValor = new JLabel();
        lblDiaHoraValor.setBounds(150, 20, 200, 25);
        getContentPane().add(lblDiaHoraValor);

        // Si no hay día u hora válidos, abrir selector
        if (dia == null || dia.isBlank() || hora == 0) {
            abrirSelectorFechaHora(lblDiaHoraValor);
        } else {
            lblDiaHoraValor.setText(dia + " - " + hora);
        }

        JLabel lblTitulo = new JLabel("Título:");
        lblTitulo.setBounds(20, 60, 120, 25);
        getContentPane().add(lblTitulo);

        JTextField txtTitulo = new JTextField();
        txtTitulo.setBounds(150, 60, 250, 25);
        getContentPane().add(txtTitulo);

        JLabel lblTema = new JLabel("Tema:");
        lblTema.setBounds(20, 100, 120, 25);
        getContentPane().add(lblTema);

        JTextArea txtTema = new JTextArea();
        txtTema.setBounds(150, 100, 250, 80);
        getContentPane().add(txtTema);

        JLabel lblAula = new JLabel("Aula:");
        lblAula.setBounds(20, 190, 120, 25);
        getContentPane().add(lblAula);

        JTextField txtAula = new JTextField();
        txtAula.setBounds(150, 190, 250, 25);
        getContentPane().add(txtAula);

        JLabel lblUbicacion = new JLabel("Ubicación:");
        lblUbicacion.setBounds(20, 230, 120, 25);
        getContentPane().add(lblUbicacion);

        JComboBox<Centro> comboUbicacion = new JComboBox<>();
        for (Centro c : centros) comboUbicacion.addItem(c);
        comboUbicacion.setBounds(150, 230, 250, 25);
        getContentPane().add(comboUbicacion);

        JLabel lblAlumno = new JLabel("Alumno:");
        lblAlumno.setBounds(20, 270, 120, 25);
        getContentPane().add(lblAlumno);

        JComboBox<Users> comboAlumnos = new JComboBox<>();
        for (Users u : alumnos) comboAlumnos.addItem(u);
        comboAlumnos.setBounds(150, 270, 250, 25);
        getContentPane().add(comboAlumnos);

        JButton btnCrear = new JButton("Crear reunión");
        btnCrear.setBounds(150, 330, 150, 35);
        getContentPane().add(btnCrear);
		

        btnCrear.addActionListener(e -> {
            // Lógica de crear la reunión
        });
    }

    private void abrirSelectorFechaHora(JLabel lblDiaHoraValor) {

        JDialog selector = new JDialog(this, "Seleccionar día y hora", true);
        selector.setSize(350, 350);
        selector.setLayout(null);
        selector.setLocationRelativeTo(this);

        JCalendar calendar = new JCalendar();
        calendar.setBounds(20, 20, 300, 200);
        selector.add(calendar);

        JLabel lblHora = new JLabel("Hora:");
        lblHora.setBounds(20, 230, 80, 25);
        selector.add(lblHora);

        JSpinner spinnerHora = new JSpinner(
                new SpinnerNumberModel(1, 1, 6, 1)
        );
        spinnerHora.setBounds(80, 230, 60, 25);
        selector.add(spinnerHora);

        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.setBounds(120, 270, 100, 30);
        selector.add(btnAceptar);

        btnAceptar.addActionListener(e -> {

            Date fecha = calendar.getDate();
            int horaSel = (int) spinnerHora.getValue();

            Calendar cal = Calendar.getInstance();
            cal.setTime(fecha);

            String[] dias = {"Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"};
            String diaSel = dias[cal.get(Calendar.DAY_OF_WEEK) - 1];

            lblDiaHoraValor.setText(diaSel + " - " + horaSel);

            selector.dispose();
        });

        selector.setVisible(true);
    }
}
