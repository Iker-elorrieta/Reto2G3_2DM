package Vista;

import java.awt.EventQueue;
import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.border.LineBorder;

import controlador.Controlador;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Perfil extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private JTextField textField;

	private JTextField textField_1;

	private JTextField textField_2;

	private JTextField textField_3;

	private JTextField textField_4;

	private JTextField textField_5;

	private JTextField textField_6;

	private JTextField textField_7;
	
	/**
	 * Create the frame.
	 * @param controlador 
	 */
	public Perfil(Controlador controlador) {


			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			setBounds(100, 100, 894, 558);

			contentPane = new JPanel();

			contentPane.setBackground(new Color(255, 255, 255));

			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

			setContentPane(contentPane);

			contentPane.setLayout(null);

			

			JLabel logo1 = new JLabel("logo");

			logo1.setBounds(727, 11, 98, 101);

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

			panelLogin.setBounds(207, 108, 466, 335);

			contentPane.add(panelLogin);

			panelLogin.setLayout(null);

			

			JLabel lblUsuario = new JLabel("Usuario:");

			lblUsuario.setForeground(new Color(0, 64, 128));

			lblUsuario.setHorizontalAlignment(SwingConstants.LEFT);

			lblUsuario.setToolTipText("");

			lblUsuario.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 14));

			lblUsuario.setBounds(10, 11, 72, 29);

			panelLogin.add(lblUsuario);

			

			JLabel lblNombre = new JLabel("Nombre:");

			lblNombre.setToolTipText("");

			lblNombre.setHorizontalAlignment(SwingConstants.LEFT);

			lblNombre.setForeground(new Color(0, 64, 128));

			lblNombre.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 14));

			lblNombre.setBounds(10, 51, 72, 29);

			panelLogin.add(lblNombre);

			

			JLabel lblApellido = new JLabel("Apellido:");

			lblApellido.setToolTipText("");

			lblApellido.setHorizontalAlignment(SwingConstants.LEFT);

			lblApellido.setForeground(new Color(0, 64, 128));

			lblApellido.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 14));

			lblApellido.setBounds(10, 91, 72, 29);

			panelLogin.add(lblApellido);

			

			JLabel lblCorreo = new JLabel("Correo:");

			lblCorreo.setToolTipText("");

			lblCorreo.setHorizontalAlignment(SwingConstants.LEFT);

			lblCorreo.setForeground(new Color(0, 64, 128));

			lblCorreo.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 14));

			lblCorreo.setBounds(10, 131, 72, 29);

			panelLogin.add(lblCorreo);

			

			JLabel lblDireccion = new JLabel("Dirección:");

			lblDireccion.setToolTipText("");

			lblDireccion.setHorizontalAlignment(SwingConstants.LEFT);

			lblDireccion.setForeground(new Color(0, 64, 128));

			lblDireccion.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 14));

			lblDireccion.setBounds(10, 171, 72, 29);

			panelLogin.add(lblDireccion);

			

			JLabel lblTelefono = new JLabel("Teléfono 1:");

			lblTelefono.setToolTipText("");

			lblTelefono.setHorizontalAlignment(SwingConstants.LEFT);

			lblTelefono.setForeground(new Color(0, 64, 128));

			lblTelefono.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 14));

			lblTelefono.setBounds(10, 211, 82, 29);

			panelLogin.add(lblTelefono);

			

			JLabel lblTelefono1 = new JLabel("Teléfono 2:");

			lblTelefono1.setToolTipText("");

			lblTelefono1.setHorizontalAlignment(SwingConstants.LEFT);

			lblTelefono1.setForeground(new Color(0, 64, 128));

			lblTelefono1.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 14));

			lblTelefono1.setBounds(10, 251, 82, 29);

			panelLogin.add(lblTelefono1);

			

			JLabel lblDni = new JLabel("DNI:");

			lblDni.setToolTipText("");

			lblDni.setHorizontalAlignment(SwingConstants.LEFT);

			lblDni.setForeground(new Color(0, 64, 128));

			lblDni.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 14));

			lblDni.setBounds(10, 291, 82, 29);

			panelLogin.add(lblDni);

			

			textField = new JTextField();

			textField.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));

			textField.setEnabled(false);

			textField.setEditable(false);

			textField.setBounds(92, 11, 364, 29);

			panelLogin.add(textField);

			textField.setColumns(10);

			

			textField_1 = new JTextField();

			textField_1.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));

			textField_1.setEnabled(false);

			textField_1.setEditable(false);

			textField_1.setColumns(10);

			textField_1.setBounds(92, 51, 364, 29);

			panelLogin.add(textField_1);

			

			textField_2 = new JTextField();

			textField_2.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));

			textField_2.setEnabled(false);

			textField_2.setEditable(false);

			textField_2.setColumns(10);

			textField_2.setBounds(92, 91, 364, 29);

			panelLogin.add(textField_2);

			

			textField_3 = new JTextField();

			textField_3.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));

			textField_3.setEnabled(false);

			textField_3.setEditable(false);

			textField_3.setColumns(10);

			textField_3.setBounds(92, 131, 364, 29);

			panelLogin.add(textField_3);

			

			textField_4 = new JTextField();

			textField_4.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));

			textField_4.setEnabled(false);

			textField_4.setEditable(false);

			textField_4.setColumns(10);

			textField_4.setBounds(92, 171, 364, 29);

			panelLogin.add(textField_4);

			

			textField_5 = new JTextField();

			textField_5.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));

			textField_5.setEnabled(false);

			textField_5.setEditable(false);

			textField_5.setColumns(10);

			textField_5.setBounds(92, 211, 364, 29);

			panelLogin.add(textField_5);

			

			textField_6 = new JTextField();

			textField_6.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));

			textField_6.setEnabled(false);

			textField_6.setEditable(false);

			textField_6.setColumns(10);

			textField_6.setBounds(92, 251, 364, 29);

			panelLogin.add(textField_6);

			

			textField_7 = new JTextField();

			textField_7.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));

			textField_7.setEnabled(false);

			textField_7.setEditable(false);

			textField_7.setColumns(10);

			textField_7.setBounds(92, 291, 364, 29);

			panelLogin.add(textField_7);

			

			JLabel lblTitulo = new JLabel("Tu perfil\r\n");

			lblTitulo.setBounds(10, 17, 858, 58);

			contentPane.add(lblTitulo);

			lblTitulo.setForeground(new Color(0, 64, 128));

			lblTitulo.setToolTipText("");

			lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

			lblTitulo.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 50));

			

			JButton btnVolver = new JButton("Volver");

			btnVolver.setBackground(new Color(242, 121, 0));

			btnVolver.setForeground(new Color(255, 255, 255));

			btnVolver.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

			}

			});

			btnVolver.setBounds(55, 455, 98, 25);

			contentPane.add(btnVolver);

			btnVolver.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));

			





			}

		}