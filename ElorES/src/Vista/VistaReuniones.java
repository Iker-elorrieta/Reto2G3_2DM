package Vista;

import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Component;

import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;
import modelo.Reuniones;
import modelo.Users;

import java.awt.Dimension;
import javax.swing.JButton;

public class VistaReuniones extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private String[][] colores;

    public VistaReuniones(Controlador controlador) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 894, 558);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel logo1 = new JLabel("logo");
        logo1.setBounds(21, 21, 162, 58);
        contentPane.add(logo1);

        java.net.URL imgURL = getClass().getResource("/media/logo1.png");
        if (imgURL != null) {
            ImageIcon iconoOriginal = new ImageIcon(imgURL);
            Image imagen = iconoOriginal.getImage();
            Image imagenEscalada = imagen.getScaledInstance(
                logo1.getWidth(), logo1.getHeight(), Image.SCALE_SMOOTH
            );
            logo1.setIcon(new ImageIcon(imagenEscalada));
        }

        JPanel panelLogin = new JPanel();
        panelLogin.setBackground(new Color(255, 255, 255));
        panelLogin.setBorder(new LineBorder(new Color(0, 64, 128), 2, true));
        panelLogin.setBounds(31, 90, 805, 354);
        contentPane.add(panelLogin);
        panelLogin.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportBorder(new LineBorder(new Color(0, 64, 128), 1, true));
        scrollPane.setBounds(0, 0, 805, 354);
        panelLogin.add(scrollPane);

        table = new JTable();
        table.setBorder(new LineBorder(new Color(0, 64, 128), 1, true));
        table.setFont(new Font("Tahoma", Font.BOLD, 11));

        table.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {
                "Horas", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes"
            }
        ) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

        // Renderer con colores + multilinea
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(new MultiLineCellRenderer());
        }

        table.setRowHeight(50);

        table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));
        table.getTableHeader().setPreferredSize(
            new Dimension(table.getTableHeader().getWidth(), 45)
        );

        table.setCellSelectionEnabled(true);
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(true);
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        scrollPane.setViewportView(table);

        JLabel lblHorario = new JLabel("Mis reuniones");
        lblHorario.setHorizontalAlignment(SwingConstants.CENTER);
        lblHorario.setForeground(new Color(0, 64, 128));
        lblHorario.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 40));
        lblHorario.setBounds(10, 21, 858, 58);
        contentPane.add(lblHorario);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnVolver.setBackground(new Color(221, 175, 55));
        btnVolver.addActionListener(e -> controlador.abrirMenu(this));
        btnVolver.setBounds(31, 469, 136, 31);
        contentPane.add(btnVolver);

        JButton btnCrear = new JButton("Crear reunión");
        btnCrear.setForeground(Color.WHITE);
        btnCrear.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnCrear.setBackground(new Color(221, 175, 55));
        btnCrear.setBounds(689, 32, 147, 31);
        contentPane.add(btnCrear);

        btnCrear.addActionListener(e -> {
            int row = table.getSelectedRow();
            int col = table.getSelectedColumn();
            String dia = "";
            int hora = 0;

            if (row != -1 && col != -1) {
                dia = table.getColumnName(col);
                hora = Integer.parseInt(table.getValueAt(row, 0).toString());
            }

            Users alumno = new Users();
            alumno.setId(10);

            DialogCrearReunion dialog = new DialogCrearReunion(
                this, hora, dia, controlador
            );
            dialog.setVisible(true);
        });

        JButton btnAceptarReunion = new JButton("Aceptar");
        btnAceptarReunion.setForeground(Color.WHITE);
        btnAceptarReunion.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnAceptarReunion.setBackground(new Color(34, 139, 34));
        btnAceptarReunion.setBounds(521, 469, 147, 31);
        contentPane.add(btnAceptarReunion);

        JButton btnRechazar = new JButton("Rechazar");
        btnRechazar.setForeground(Color.WHITE);
        btnRechazar.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnRechazar.setBackground(new Color(139, 0, 0));
        btnRechazar.setBounds(689, 469, 147, 31);
        contentPane.add(btnRechazar);
        
        btnAceptarReunion.addActionListener(e -> {
            int row = table.getSelectedRow();
            int col = table.getSelectedColumn();
            if (row == -1 || col == -1) return;

            String contenido = table.getValueAt(row, col).toString();
            Reuniones r = controlador.buscarReunionPorTexto(contenido);
            if (r == null) return;

         String estado = r.getEstado().trim().toLowerCase();
         if (estado.equals("aceptada") || estado.equals("denegada")) {
             return;
         }

            if (r != null) {
                boolean ok = controlador.actualizarEstadoReunionPorId(r.getIdReunion(), "aceptada");
                if (ok) controlador.cargarHorariosyReuniones(this);
            }
        });



        btnRechazar.addActionListener(e -> {
            int row = table.getSelectedRow();
            int col = table.getSelectedColumn();
            if (row == -1 || col == -1) return;

            String contenido = table.getValueAt(row, col).toString();
            Reuniones r = controlador.buscarReunionPorTexto(contenido);
            String estado = r.getEstado().trim().toLowerCase();
            if (estado.equals("aceptada") || estado.equals("denegada")) {
                return;
            }
            if (r != null) {
                boolean ok = controlador.actualizarEstadoReunionPorId(r.getIdReunion(), "denegada");
                if (ok) controlador.cargarHorariosyReuniones(this);
            }
        });




        controlador.cargarHorariosyReuniones(this);
    }

    public void actualizarTablaHorarios(String[][] datos, String[][] colores) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        for (int i = 0; i < datos.length; i++) {
            model.addRow(datos[i]);
        }

        this.colores = colores;
        ajustarAlturaFilas();
    }

    private void ajustarAlturaFilas() {
        for (int row = 0; row < table.getRowCount(); row++) {
            int maxHeight = table.getRowHeight();

            for (int column = 0; column < table.getColumnCount(); column++) {
                Component comp = table.prepareRenderer(table.getCellRenderer(row, column), row, column);
                int height = comp.getPreferredSize().height;
                maxHeight = Math.max(maxHeight, height);
            }

            table.setRowHeight(row, maxHeight);
        }
    }

    // Renderer multilinea + colores
    class MultiLineCellRenderer extends DefaultTableCellRenderer {

        private static final long serialVersionUID = 1L;

        @Override
        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {

            JTextArea area = new JTextArea();
            area.setText(value == null ? "" : value.toString());
            area.setLineWrap(true);
            area.setWrapStyleWord(true);
            area.setOpaque(true);
            area.setFont(table.getFont());
            area.setMargin(new Insets(5, 5, 5, 5));

            // Colores según estado
            if (colores != null && row < colores.length && column < colores[row].length) {
                switch (colores[row][column]) {
                    case "GRIS": area.setBackground(Color.LIGHT_GRAY); break;
                    case "AMARILLO": area.setBackground(new Color(255, 255, 153)); break;
                    case "VERDE": area.setBackground(new Color(144, 238, 144)); break;
                    case "ROJO": area.setBackground(new Color(255, 102, 102)); break;
                    default: area.setBackground(Color.WHITE); break;
                }
            }

            if (isSelected) {
                area.setBackground(table.getSelectionBackground());
                area.setForeground(table.getSelectionForeground());
            }

            int columnWidth = table.getColumnModel().getColumn(column).getWidth();
            area.setSize(columnWidth, Short.MAX_VALUE);

            return area;
        }
    }
}
