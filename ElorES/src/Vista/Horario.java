package Vista;

import java.awt.Image;
import java.awt.Insets;
import java.io.File;

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

import java.awt.Dimension;
import javax.swing.JButton;

public class Horario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	

	public Horario(Controlador controlador) {
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
		} else {
			File f = new File("media/logo1.png");
			if (f.exists()) {
				ImageIcon iconoOriginal = new ImageIcon(f.getAbsolutePath());
				Image imagen = iconoOriginal.getImage();
				Image imagenEscalada = imagen.getScaledInstance(
					logo1.getWidth(), logo1.getHeight(), Image.SCALE_SMOOTH
				);
				logo1.setIcon(new ImageIcon(imagenEscalada));
			}
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

	
		table.setRowHeight(50);
		// 🔹 PERMITIR TEXTO MULTILÍNEA EN LAS CELDAS
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
		    /**
			 * 
			 */
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

		        // 🔹 CENTRAR TEXTO
		        area.setAlignmentX(CENTER_ALIGNMENT);
		        area.setAlignmentY(CENTER_ALIGNMENT);
		        area.setMargin(new Insets(5, 5, 5, 5)); // padding

		        // 🔹 COLORES
		        if (isSelected) {
		            area.setBackground(table.getSelectionBackground());
		            area.setForeground(table.getSelectionForeground());
		        } else {
		            area.setBackground(table.getBackground());
		            area.setForeground(table.getForeground());
		        }

		        // 🔹 AJUSTAR ALTURA SEGÚN CONTENIDO
		        int preferredHeight = area.getPreferredSize().height;
		        if (table.getRowHeight(row) < preferredHeight) {
		            table.setRowHeight(row, preferredHeight);
		        }

		        return area;
		    }
		});



		scrollPane.setViewportView(table);

		table.setModel(new DefaultTableModel(
		        new Object[][] {},
		        new String[] {
		            "Horas", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes"
		        }
		) {
		    private static final long serialVersionUID = 1L;

		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return false; // ninguna celda editable
		    }
		});


		// 🔹 ALTURA Y FUENTE DEL ENCABEZADO
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));
		table.getTableHeader().setPreferredSize(
			new Dimension(table.getTableHeader().getWidth(), 45)
		);
		
		JLabel lblHorario = new JLabel("Mi horario");
		lblHorario.setHorizontalAlignment(SwingConstants.CENTER);
		lblHorario.setForeground(new Color(0, 64, 128));
		lblHorario.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 40));
		lblHorario.setBounds(10, 21, 858, 58);
		contentPane.add(lblHorario);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setForeground(new Color(255, 255, 255));
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnVolver.setBackground(new Color(221, 175, 55));
		btnVolver.addActionListener(e -> controlador.abrirMenu(this));

		btnVolver.setBounds(31, 469, 136, 31);
		contentPane.add(btnVolver);
		
		JLabel logo2 = new JLabel("logo");
		logo2.setBounds(399, 466, 48, 42);
		contentPane.add(logo2);
		java.net.URL imgURL1 = getClass().getResource("/media/logo2.png");
		if (imgURL1 != null) {
			ImageIcon iconoOriginal = new ImageIcon(imgURL1);
			Image imagen = iconoOriginal.getImage();
			Image imagenEscalada = imagen.getScaledInstance(logo2.getWidth(), logo2.getHeight(), Image.SCALE_SMOOTH);
			logo2.setIcon(new ImageIcon(imagenEscalada));
		} else {
			File f = new File("media/logo2.png");
			if (f.exists()) {
				ImageIcon iconoOriginal = new ImageIcon(f.getAbsolutePath());
				Image imagen = iconoOriginal.getImage();
				Image imagenEscalada = imagen.getScaledInstance(logo2.getWidth(), logo2.getHeight(), Image.SCALE_SMOOTH);
				logo2.setIcon(new ImageIcon(imagenEscalada));
			} else {
				System.err.println("error en imagen.");
			}
		}
		

		
	}



	public void actualizarTablaHorarios(String[][] datos) {
	    DefaultTableModel model = (DefaultTableModel) table.getModel();
	    model.setRowCount(0);

	    for (int i = 0; i < datos.length; i++) {
	        model.addRow(datos[i]);
	    }
	}


}
