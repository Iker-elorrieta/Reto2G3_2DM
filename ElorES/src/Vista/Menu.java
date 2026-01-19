package Vista;

import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.border.LineBorder;

import controlador.Controlador;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;



	/**
	 * Create the frame.
	 * @param controlador 
	 */
	public Menu(Controlador controlador) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 894, 558);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel logo1 = new JLabel("logo");
		logo1.setBounds(21, 21, 129, 51);
		contentPane.add(logo1);
		java.net.URL imgURL = getClass().getResource("/media/logo1.png");
		if (imgURL != null) {
			ImageIcon iconoOriginal = new ImageIcon(imgURL);
			Image imagen = iconoOriginal.getImage();
			Image imagenEscalada = imagen.getScaledInstance(logo1.getWidth(), logo1.getHeight(), Image.SCALE_SMOOTH);
			logo1.setIcon(new ImageIcon(imagenEscalada));
		} else {
			
			File f = new File("media/logo1.png");
			if (f.exists()) {
				ImageIcon iconoOriginal = new ImageIcon(f.getAbsolutePath());
				Image imagen = iconoOriginal.getImage();
				Image imagenEscalada = imagen.getScaledInstance(logo1.getWidth(), logo1.getHeight(), Image.SCALE_SMOOTH);
				logo1.setIcon(new ImageIcon(imagenEscalada));
			} else {
				
			}
		}
		
		JPanel panelLogin = new JPanel();
		panelLogin.setBackground(new Color(255, 255, 255));
		panelLogin.setForeground(new Color(196, 225, 255));
		panelLogin.setBorder(new LineBorder(new Color(0, 64, 128), 2, true));
		panelLogin.setBounds(157, 102, 554, 351);
		contentPane.add(panelLogin);
		panelLogin.setLayout(null);
		
		JLabel lblMenu = new JLabel("Menú");
		lblMenu.setForeground(new Color(0, 64, 128));
		lblMenu.setToolTipText("");
		lblMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenu.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 50));
		lblMenu.setBounds(0, 11, 554, 58);
		panelLogin.add(lblMenu);
		
		JButton btnMiHorario = new JButton("Mi horario");
		btnMiHorario.setForeground(new Color(0, 0, 0));
		btnMiHorario.setBackground(UIManager.getColor("Button.background"));
		btnMiHorario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					contentPane.setVisible(false);
					Horario horario = new Horario(controlador);
					horario.setVisible(true);
					dispose();
			
			}
		});
		btnMiHorario.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 18));
		btnMiHorario.setBounds(53, 113, 176, 50);
		panelLogin.add(btnMiHorario);
		
		JButton btnOtrosHorarios = new JButton("Otros horarios");
		btnOtrosHorarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				Horario horario = new Horario(controlador);
				horario.setVisible(true);
				dispose();
			}
		});
		btnOtrosHorarios.setBackground(UIManager.getColor("Button.background"));
		btnOtrosHorarios.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 18));
		btnOtrosHorarios.setBounds(53, 211, 176, 50);
		panelLogin.add(btnOtrosHorarios);
		
		JButton btnAlumnos = new JButton("Alumnos");
		btnAlumnos.setForeground(new Color(0, 0, 0));
		btnAlumnos.setBackground(UIManager.getColor("Button.background"));
		btnAlumnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				Alumnos alumnos = new Alumnos(controlador);
				alumnos.setVisible(true);
				dispose();
		
				
			}
		});
		btnAlumnos.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 18));
		btnAlumnos.setBounds(343, 113, 163, 50);
		panelLogin.add(btnAlumnos);
		
		JButton btnReuniones = new JButton("Reuniones");
		btnReuniones.setBackground(UIManager.getColor("Button.background"));
		btnReuniones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				Reuniones reuniones = new Reuniones(controlador);
				reuniones.setVisible(true);
				dispose();
			
			}
		});
		btnReuniones.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 18));
		btnReuniones.setBounds(343, 211, 163, 50);
		panelLogin.add(btnReuniones);
		
		JLabel lblBienvenidoUsuario = new JLabel("Bienvenido, usuario.");
		lblBienvenidoUsuario.setToolTipText("");
		lblBienvenidoUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenidoUsuario.setForeground(new Color(0, 64, 128));
		lblBienvenidoUsuario.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 40));
		lblBienvenidoUsuario.setBounds(112, 21, 648, 58);
		contentPane.add(lblBienvenidoUsuario);
		
		JButton btnPerfil = new JButton("");
		btnPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				Perfil perfil = new Perfil(controlador);
				perfil.setVisible(true);
				dispose();
			}
		});
		btnPerfil.setBounds(770, 21, 62, 58);
		contentPane.add(btnPerfil);
		
		java.net.URL imgPerfilURL = getClass().getResource("/media/perfil1.jpg");
		if (imgPerfilURL != null) {
			ImageIcon iconoOriginal = new ImageIcon(imgPerfilURL);
			Image imagen = iconoOriginal.getImage();
			Image imagenEscalada = imagen.getScaledInstance(btnPerfil.getWidth(), btnPerfil.getHeight(), Image.SCALE_SMOOTH);
			btnPerfil.setIcon(new ImageIcon(imagenEscalada));
		} else {
			File f = new File("media/perfil1.jpg");
			if (f.exists()) {
				ImageIcon iconoOriginal = new ImageIcon(f.getAbsolutePath());
				Image imagen = iconoOriginal.getImage();
				Image imagenEscalada = imagen.getScaledInstance(btnPerfil.getWidth(), btnPerfil.getHeight(), Image.SCALE_SMOOTH);
				btnPerfil.setIcon(new ImageIcon(imagenEscalada));
			} else {
				
			}
		}
		btnPerfil.setText("");
		
		JButton btnCerrarSesion = new JButton("Cerrar Sesión");
		btnCerrarSesion.setBounds(732, 102, 136, 25);
		contentPane.add(btnCerrarSesion);
		btnCerrarSesion.setForeground(new Color(0, 0, 0));
		btnCerrarSesion.setBackground(new Color(221, 175, 55));
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				Login login = new Login(controlador);
				login.setVisible(true);
				dispose();
			}
		});
		btnCerrarSesion.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 12));
		
		JLabel logo2 = new JLabel("logo2");
		logo2.setBounds(417, 464, 45, 41);
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
}