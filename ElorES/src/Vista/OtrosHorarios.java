package Vista;

import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;
import modelo.Users;

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class OtrosHorarios extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JComboBox<Users> comboProfesores;

	

	public OtrosHorarios(Controlador controlador) {
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
		table.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
		    public java.awt.Component getTableCellRendererComponent(
		            JTable table, Object value, boolean isSelected, boolean hasFocus,
		            int row, int column) {

		        javax.swing.JTextArea area = new javax.swing.JTextArea();
		        area.setText(value == null ? "" : value.toString());
		        area.setLineWrap(true);
		        area.setWrapStyleWord(true);
		        area.setOpaque(true);
		        area.setFont(table.getFont());

		        // 🔹 CENTRAR TEXTO
		        area.setAlignmentX(CENTER_ALIGNMENT);
		        area.setAlignmentY(CENTER_ALIGNMENT);
		        area.setMargin(new java.awt.Insets(5, 5, 5, 5)); // padding

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
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"Horas", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, true, true, true, true, true
			};

			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		// 🔹 ALTURA Y FUENTE DEL ENCABEZADO
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));
		table.getTableHeader().setPreferredSize(
			new Dimension(table.getTableHeader().getWidth(), 45)
		);
		
		JLabel lblHorario = new JLabel("Otros horarios");
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
		
		comboProfesores = new JComboBox<Users>();
		comboProfesores.setBounds(551, 452, 285, 22);
		contentPane.add(comboProfesores);
		Users[] profesores = controlador.obtenerProfesores();
		cargarProfesores(profesores);
		comboProfesores.addActionListener(e -> controlador.cargarHorarios(this));


		
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
	
	public void cargarProfesores(Users[] profesores) {
	    comboProfesores.removeAllItems();
	    comboProfesores.addItem(null);
	    for (Users u : profesores) {
	        comboProfesores.addItem(u);
	    }
	    comboProfesores.setSelectedIndex(0);
	}




	public int getProfesorSeleccionado() {
	    Users u = (Users) comboProfesores.getSelectedItem();
	    if (u == null) return -1; 
	    return u.getId();
	}



	public void actualizarTablaHorarios(String[][] datos) {
	    DefaultTableModel model = (DefaultTableModel) table.getModel();
	    model.setRowCount(0);

	    for (int i = 0; i < datos.length; i++) {
	        model.addRow(datos[i]);
	    }
	}
}
