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

import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Reuniones extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	

	public Reuniones(Controlador controlador) {
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
		panelLogin.setBounds(157, 90, 554, 313);
		contentPane.add(panelLogin);
		panelLogin.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new LineBorder(new Color(0, 64, 128), 1, true));
		scrollPane.setBounds(10, 11, 534, 291);
		panelLogin.add(scrollPane);
		
		table = new JTable();
		table.setBorder(new LineBorder(new Color(0, 64, 128), 1, true));
		table.setFont(new Font("Tahoma", Font.BOLD, 15));

		// 🔹 ALTURA DE LAS FILAS
		table.setRowHeight(40);

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
		
		JLabel lblTitulo = new JLabel("Mis reuniones");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setForeground(new Color(0, 64, 128));
		lblTitulo.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 40));
		lblTitulo.setBounds(10, 21, 858, 58);
		contentPane.add(lblTitulo);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setForeground(new Color(255, 255, 255));
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnVolver.setBackground(new Color(221, 175, 55));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				Menu menu = new Menu(controlador);
				menu.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(355, 424, 136, 31);
		contentPane.add(btnVolver);
		
		JLabel logo2 = new JLabel("logo");
		logo2.setBounds(399, 466, 48, 42);
		contentPane.add(logo2);
		
		JButton btnCrear = new JButton("Crear reunión");
		btnCrear.setForeground(Color.WHITE);
		btnCrear.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCrear.setBackground(new Color(221, 175, 55));
		btnCrear.setBounds(721, 90, 147, 31);
		contentPane.add(btnCrear);
		
		JButton btnAceptarReunion = new JButton("Aceptar");
		btnAceptarReunion.setForeground(Color.WHITE);
		btnAceptarReunion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAceptarReunion.setBackground(new Color(34, 139, 34));
		btnAceptarReunion.setBounds(721, 132, 147, 31);
		contentPane.add(btnAceptarReunion);
		
		JButton btnRechazar = new JButton("Rechazar");
		btnRechazar.setForeground(Color.WHITE);
		btnRechazar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnRechazar.setBackground(new Color(139, 0, 0));
		btnRechazar.setBounds(721, 174, 147, 31);
		contentPane.add(btnRechazar);
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
}
