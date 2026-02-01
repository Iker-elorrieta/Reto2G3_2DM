package Vista;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controlador.Controlador;
import modelo.Centro;
import modelo.Reuniones;
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
        ArrayList<Users> alumnos = controlador.cargarAlumnosDialog();

        JLabel lblDiaHora = new JLabel("Día y hora:");
        lblDiaHora.setBounds(20, 20, 120, 25);
        getContentPane().add(lblDiaHora);

        JLabel lblDiaHoraValor = new JLabel();
        lblDiaHoraValor.setBounds(150, 20, 250, 25);
        getContentPane().add(lblDiaHoraValor);

        if (dia != null && !dia.isBlank() && hora > 0) {
            lblDiaHoraValor.setText(dia + " - " + hora);
        } else {
            lblDiaHoraValor.setText("No seleccionado");
        }

        JLabel lblTitulo = new JLabel("Título:");
        lblTitulo.setBounds(20, 90, 120, 25);
        getContentPane().add(lblTitulo);

        JTextField txtTitulo = new JTextField();
        txtTitulo.setBounds(150, 90, 250, 25);
        getContentPane().add(txtTitulo);

        JLabel lblTema = new JLabel("Tema:");
        lblTema.setBounds(20, 130, 120, 25);
        getContentPane().add(lblTema);

        JTextArea txtTema = new JTextArea();
        txtTema.setBounds(150, 130, 250, 80);
        getContentPane().add(txtTema);

        JLabel lblAula = new JLabel("Aula:");
        lblAula.setBounds(20, 220, 120, 25);
        getContentPane().add(lblAula);

        JTextField txtAula = new JTextField();
        txtAula.setBounds(150, 220, 250, 25);
        getContentPane().add(txtAula);

        JLabel lblUbicacion = new JLabel("Ubicación:");
        lblUbicacion.setBounds(20, 260, 120, 25);
        getContentPane().add(lblUbicacion);

        JComboBox<Centro> comboUbicacion = new JComboBox<>();
        for (Centro c : centros) comboUbicacion.addItem(c);
        comboUbicacion.setBounds(150, 260, 250, 25);
        getContentPane().add(comboUbicacion);

        JLabel lblAlumno = new JLabel("Alumno:");
        lblAlumno.setBounds(20, 300, 120, 25);
        getContentPane().add(lblAlumno);

        JComboBox<Users> comboAlumnos = new JComboBox<>();
        for (Users u : alumnos) comboAlumnos.addItem(u);
        comboAlumnos.setBounds(150, 300, 250, 25);
        getContentPane().add(comboAlumnos);

        JButton btnCrear = new JButton("Crear reunión");
        btnCrear.setBounds(150, 350, 150, 35);
        getContentPane().add(btnCrear);

        btnCrear.setEnabled(!lblDiaHoraValor.getText().equals("No seleccionado"));

        btnCrear.addActionListener(e -> {

            if (lblDiaHoraValor.getText().equals("No seleccionado") ||
                txtTitulo.getText().trim().isEmpty() ||
                txtTema.getText().trim().isEmpty() ||
                txtAula.getText().trim().isEmpty() ||
                comboUbicacion.getSelectedItem() == null ||
                comboAlumnos.getSelectedItem() == null) {

                JOptionPane.showMessageDialog(this,
                        "Debes rellenar todos los campos",
                        "Datos incompletos",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            Reuniones nueva = controlador.construirReunionDesdeDialog(
                    lblDiaHoraValor.getText(),
                    txtTitulo.getText(),
                    txtTema.getText(),
                    txtAula.getText(),
                    (Centro) comboUbicacion.getSelectedItem(),
                    (Users) comboAlumnos.getSelectedItem()
            );

            boolean ok = controlador.crearReunion(nueva);

            if (ok) {
                controlador.cargarHorariosyReuniones((VistaReuniones) getParent());
                dispose();
            } else {
                System.out.println("Error creando reunión");
            }
        });
    }
}
