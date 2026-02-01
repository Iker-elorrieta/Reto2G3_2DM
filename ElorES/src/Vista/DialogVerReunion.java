package Vista;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import controlador.Controlador;
import modelo.Centro;
import modelo.Reuniones;
import modelo.Users;

public class DialogVerReunion extends JDialog {

    private static final long serialVersionUID = 1L;

    public DialogVerReunion(JFrame parent,
                            ArrayList<Reuniones> reuniones,
                            Controlador controlador,
                            String diaTabla,
                            int horaTabla) {

        super(parent, "Datos de las reuniones", true);
        setSize(520, 520);
        setLocationRelativeTo(parent);
        getContentPane().setLayout(null);

        JLabel lblDiaHora = new JLabel("Día y hora:");
        lblDiaHora.setBounds(20, 20, 120, 25);
        getContentPane().add(lblDiaHora);

        JLabel lblDiaHoraValor = new JLabel(diaTabla + " - " + horaTabla);
        lblDiaHoraValor.setBounds(150, 20, 200, 25);
        getContentPane().add(lblDiaHoraValor);

        JLabel lblLista = new JLabel("Reuniones en esta hora:");
        lblLista.setBounds(20, 60, 200, 25);
        getContentPane().add(lblLista);

        JPanel panelLista = new JPanel();
        panelLista.setLayout(new BoxLayout(panelLista, BoxLayout.Y_AXIS));
        panelLista.setBackground(new Color(240, 240, 240));

        JScrollPane scroll = new JScrollPane(panelLista);
        scroll.setBounds(20, 90, 470, 330);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(new Color(240, 240, 240));
        getContentPane().add(scroll);

        // Cargar alumnos una sola vez
        ArrayList<Users> alumnos = controlador.cargarAlumnosDialog();

        for (Reuniones r : reuniones) {

            JPanel card = new JPanel();
            card.setLayout(new GridLayout(0, 1));
            card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 130));
            card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                    BorderFactory.createEmptyBorder(10, 15, 10, 15)
            ));
            card.setBackground(Color.WHITE);

            String centro = controlador.obtenerCentros().stream()
                    .filter(c -> String.valueOf(c.getCCEN()).equals(r.getIdCentro()))
                    .findFirst()
                    .map(Centro::getNOM)
                    .orElse("Desconocido");

    

         // Si la reunión tiene alumno asignado, usar su ID
            Users alumno = null;

         if (r.getUsersByAlumnoId() != null) {

             int idAlumno = r.getUsersByAlumnoId().getId();

             alumno = alumnos.stream()
                     .filter(a -> a.getId() == idAlumno)
                     .findFirst()
                     .orElse(null);
         }



            card.add(new JLabel("Título: " + r.getTitulo()));
            card.add(new JLabel("Tema: " + r.getAsunto()));
            card.add(new JLabel("Aula: " + r.getAula()));
            card.add(new JLabel("Centro: " + centro));
            card.add(new JLabel("Alumno: " + (alumno != null ? alumno.getNombre() + " "+ alumno.getApellidos() : "No asignado")));
            card.add(new JLabel("Estado: " + r.getEstado()));

            panelLista.add(card);
            panelLista.add(Box.createVerticalStrut(10));
        }

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(180, 430, 150, 35);
        btnVolver.addActionListener(e -> dispose());
        getContentPane().add(btnVolver);
    }
}
