package Vista;

import java.awt.EventQueue;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 894, 558);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel logo1 = new JLabel("logo");
		logo1.setBounds(37, 11, 85, 79);
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
		btnMiHorario.setBackground(UIManager.getColor("Button.background"));
		btnMiHorario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnMiHorario.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 18));
		btnMiHorario.setBounds(53, 113, 176, 50);
		panelLogin.add(btnMiHorario);
		
		JButton btnOtrosHorarios = new JButton("Otros horarios");
		btnOtrosHorarios.setBackground(UIManager.getColor("Button.background"));
		btnOtrosHorarios.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 18));
		btnOtrosHorarios.setBounds(53, 211, 176, 50);
		panelLogin.add(btnOtrosHorarios);
		
		JButton btnAlumnos = new JButton("Alumnos");
		btnAlumnos.setBackground(UIManager.getColor("Button.background"));
		btnAlumnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAlumnos.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 18));
		btnAlumnos.setBounds(343, 113, 163, 50);
		panelLogin.add(btnAlumnos);
		
		JButton btnReuniones = new JButton("Reuniones");
		btnReuniones.setBackground(UIManager.getColor("Button.background"));
		btnReuniones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		btnCerrarSesion.setBackground(UIManager.getColor("Button.background"));
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCerrarSesion.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 12));


	}
}