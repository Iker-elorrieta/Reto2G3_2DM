package Vista;

import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.border.LineBorder;

import controlador.Controlador;

import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtContrasena;

	
	public Login(Controlador controlador) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 894, 558);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel logo1 = new JLabel("logo");
		logo1.setBounds(770, 11, 98, 101);
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
				System.err.println("Resource not found: /media/logo1.png and media/logo1.png not found in working dir.");
			}
		}
		
		JPanel panelLogin = new JPanel();
		panelLogin.setBackground(new Color(255, 255, 255));
		panelLogin.setForeground(new Color(196, 225, 255));
		panelLogin.setBorder(new LineBorder(new Color(0, 64, 128), 2, true));
		panelLogin.setBounds(261, 90, 332, 356);
		contentPane.add(panelLogin);
		panelLogin.setLayout(null);
		
		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtUsuario.setBounds(21, 129, 281, 46);
		panelLogin.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setForeground(new Color(252, 140, 39));
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setToolTipText("");
		lblUsuario.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 25));
		lblUsuario.setBounds(0, 99, 324, 29);
		panelLogin.add(lblUsuario);
		
		JLabel lblContrasena = new JLabel("Contraseña");
		lblContrasena.setForeground(new Color(252, 140, 39));
		lblContrasena.setToolTipText("");
		lblContrasena.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasena.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 25));
		lblContrasena.setBounds(0, 202, 331, 29);
		panelLogin.add(lblContrasena);
		
		JLabel lblLogin = new JLabel("Inicio");
		lblLogin.setForeground(new Color(0, 64, 128));
		lblLogin.setToolTipText("");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 50));
		lblLogin.setBounds(3, 11, 324, 58);
		panelLogin.add(lblLogin);
		
		JButton btnIniciar = new JButton("Iniciar Sesión");
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (controlador.login(txtUsuario.getText(), new String (txtContrasena.getPassword()))) {
					contentPane.setVisible(false);
					Menu menu = new Menu(controlador);
					menu.setVisible(true);
					dispose();
				} else {
					System.out.println("Login fallido");
				}
			}
		});
		btnIniciar.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		btnIniciar.setBounds(107, 303, 120, 23);
		panelLogin.add(btnIniciar);
		
		txtContrasena = new JPasswordField();
		txtContrasena.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtContrasena.setBounds(21, 235, 281, 46);
		panelLogin.add(txtContrasena);
		


	}
}