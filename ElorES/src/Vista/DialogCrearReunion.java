package Vista;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controlador.Controlador;
import modelo.Users;

public class DialogCrearReunion extends JDialog {

    private static final long serialVersionUID = 1L;

    public DialogCrearReunion(JFrame parent, int hora, String dia, 
                              Controlador controlador, List<Users> alumnos) {

        super(parent, "Crear reunión", true);
        setSize(450, 450);
        setLocationRelativeTo(parent);
        setLayout(null);

        JLabel lblDiaHora = new JLabel("Día y hora:");
        lblDiaHora.setBounds(20, 20, 120, 25);
        add(lblDiaHora);

        JLabel lblDiaHoraValor = new JLabel(dia + " - " + hora);
        lblDiaHoraValor.setBounds(150, 20, 200, 25);
        add(lblDiaHoraValor);

        JLabel lblTitulo = new JLabel("Título:");
        lblTitulo.setBounds(20, 60, 120, 25);
        add(lblTitulo);

        JTextField txtTitulo = new JTextField();
        txtTitulo.setBounds(150, 60, 250, 25);
        add(txtTitulo);

        JLabel lblTema = new JLabel("Tema:");
        lblTema.setBounds(20, 100, 120, 25);
        add(lblTema);

        JTextArea txtTema = new JTextArea();
        txtTema.setBounds(150, 100, 250, 80);
        add(txtTema);

        JLabel lblAula = new JLabel("Aula:");
        lblAula.setBounds(20, 190, 120, 25);
        add(lblAula);

        JTextField txtAula = new JTextField();
        txtAula.setBounds(150, 190, 250, 25);
        add(txtAula);

        JLabel lblUbicacion = new JLabel("Ubicación:");
        lblUbicacion.setBounds(20, 230, 120, 25);
        add(lblUbicacion);

        JTextField txtUbicacion = new JTextField("CIFP Elorrieta-Errekamari LHII");
        txtUbicacion.setBounds(150, 230, 250, 25);
        add(txtUbicacion);

        JLabel lblAlumno = new JLabel("Alumno:");
        lblAlumno.setBounds(20, 270, 120, 25);
        add(lblAlumno);

        JComboBox<Users> comboAlumnos = new JComboBox<>();
        for (Users u : alumnos) comboAlumnos.addItem(u);
        comboAlumnos.setBounds(150, 270, 250, 25);
        add(comboAlumnos);

        JButton btnCrear = new JButton("Crear reunión");
        btnCrear.setBounds(150, 330, 150, 35);
        add(btnCrear);

        btnCrear.addActionListener(e -> {

            // Users alumno = (Users) comboAlumnos.getSelectedItem();

         /*   controlador.crearReunion(
                dia,
                hora,
                txtTitulo.getText(),
                txtTema.getText(),
                txtAula.getText(),
                txtUbicacion.getText(),
                alumno.getId()
            );

            dispose();*/
        });
    }
}
