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
import javax.swing.border.LineBorder;
import javax.swing.JPasswordField;

public class Perfil extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Perfil frame = new Perfil();
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
	public Perfil() {
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
		panelLogin.setBounds(261, 90, 332, 418);
		contentPane.add(panelLogin);
		panelLogin.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Nombre:");
		lblUsuario.setForeground(new Color(0, 64, 128));
		lblUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsuario.setToolTipText("");
		lblUsuario.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 18));
		lblUsuario.setBounds(10, 11, 312, 29);
		panelLogin.add(lblUsuario);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setToolTipText("");
		lblApellido.setHorizontalAlignment(SwingConstants.LEFT);
		lblApellido.setForeground(new Color(0, 64, 128));
		lblApellido.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 18));
		lblApellido.setBounds(10, 70, 312, 29);
		panelLogin.add(lblApellido);
		
		JLabel lblDNI = new JLabel("DNI:");
		lblDNI.setToolTipText("");
		lblDNI.setHorizontalAlignment(SwingConstants.LEFT);
		lblDNI.setForeground(new Color(0, 64, 128));
		lblDNI.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 18));
		lblDNI.setBounds(10, 127, 312, 29);
		panelLogin.add(lblDNI);
		
		JLabel lblTelfono = new JLabel("Teléfono 1:");
		lblTelfono.setToolTipText("");
		lblTelfono.setHorizontalAlignment(SwingConstants.LEFT);
		lblTelfono.setForeground(new Color(0, 64, 128));
		lblTelfono.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 18));
		lblTelfono.setBounds(10, 182, 312, 29);
		panelLogin.add(lblTelfono);
		
		JLabel lblTelfono_2 = new JLabel("Teléfono 2:");
		lblTelfono_2.setToolTipText("");
		lblTelfono_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblTelfono_2.setForeground(new Color(0, 64, 128));
		lblTelfono_2.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 18));
		lblTelfono_2.setBounds(10, 237, 312, 29);
		panelLogin.add(lblTelfono_2);
		
		JLabel lblTitulo = new JLabel("Tu perfil\r\n");
		lblTitulo.setBounds(10, 17, 858, 58);
		contentPane.add(lblTitulo);
		lblTitulo.setForeground(new Color(0, 64, 128));
		lblTitulo.setToolTipText("");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 50));
		
		JButton btnIniciar = new JButton("Iniciar Sesión");
		btnIniciar.setBounds(98, 440, 120, 23);
		contentPane.add(btnIniciar);
		btnIniciar.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		


	}
}