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
		panelLogin.setBounds(209, 86, 466, 411);
		contentPane.add(panelLogin);
		panelLogin.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Nombre:");
		lblUsuario.setForeground(new Color(0, 64, 128));
		lblUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsuario.setToolTipText("");
		lblUsuario.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 14));
		lblUsuario.setBounds(10, 11, 72, 29);
		panelLogin.add(lblUsuario);
		
		JLabel lblTitulo = new JLabel("Tu perfil\r\n");
		lblTitulo.setBounds(10, 17, 858, 58);
		contentPane.add(lblTitulo);
		lblTitulo.setForeground(new Color(0, 64, 128));
		lblTitulo.setToolTipText("");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 50));
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnVolver.setBounds(32, 474, 120, 23);
		contentPane.add(btnVolver);
		btnVolver.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		


	}
}