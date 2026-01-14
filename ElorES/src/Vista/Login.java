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
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JTextField txtContrasena;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 894, 558);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel logo1 = new JLabel("logo");
		logo1.setBounds(30, 20, 133, 134);
		contentPane.add(logo1);
		java.net.URL imgURL = getClass().getResource("/media/logo1.png");
		if (imgURL != null) {
			ImageIcon iconoOriginal = new ImageIcon(imgURL);
			Image imagen = iconoOriginal.getImage();
			Image imagenEscalada = imagen.getScaledInstance(logo1.getWidth(), logo1.getHeight(), Image.SCALE_SMOOTH);
			logo1.setIcon(new ImageIcon(imagenEscalada));
		} else {
			// Fallback: try loading from working directory (useful during development)
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
		panelLogin.setBounds(262, 117, 332, 356);
		contentPane.add(panelLogin);
		panelLogin.setLayout(null);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(21, 129, 281, 46);
		panelLogin.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtContrasena = new JTextField();
		txtContrasena.setColumns(10);
		txtContrasena.setBounds(25, 232, 281, 46);
		panelLogin.add(txtContrasena);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setForeground(new Color(252, 140, 39));
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setToolTipText("");
		lblUsuario.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 30));
		lblUsuario.setBounds(3, 100, 324, 29);
		panelLogin.add(lblUsuario);
		
		JLabel lblContrasena = new JLabel("Contraseña");
		lblContrasena.setForeground(new Color(252, 140, 39));
		lblContrasena.setToolTipText("");
		lblContrasena.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasena.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 30));
		lblContrasena.setBounds(0, 202, 331, 29);
		panelLogin.add(lblContrasena);
		
		JLabel lblLogin = new JLabel("Inicio");
		lblLogin.setForeground(new Color(0, 64, 128));
		lblLogin.setToolTipText("");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 50));
		lblLogin.setBounds(7, 17, 318, 58);
		panelLogin.add(lblLogin);
		
		JButton btnIniciar = new JButton("Iniciar Sesión");
		btnIniciar.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		btnIniciar.setBounds(107, 303, 120, 23);
		panelLogin.add(btnIniciar);
		


	}
}