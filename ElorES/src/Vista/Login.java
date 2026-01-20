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

public class Login extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private JLabel lblError;

    
    public Login(Controlador controlador) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 894, 558);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel logo1 = new JLabel("logo");
        logo1.setBounds(341, 21, 155, 52);
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
                System.err.println("ERROR EN IMAGEN.");
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
        lblUsuario.setForeground(new Color(248, 188, 7));
        lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
        lblUsuario.setToolTipText("");
        lblUsuario.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 22));
        lblUsuario.setBounds(0, 99, 324, 29);
        panelLogin.add(lblUsuario);
        
        lblError = new JLabel("a");
        lblError.setBounds(61, 292, 227, 14);
        lblError.setForeground(Color.RED);
        panelLogin.add(lblError);
        lblError.setVisible(false);

        
        JLabel lblContrasena = new JLabel("Contraseña");
        lblContrasena.setForeground(new Color(248, 188, 7));
        lblContrasena.setToolTipText("");
        lblContrasena.setHorizontalAlignment(SwingConstants.CENTER);
        lblContrasena.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 22));
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
        btnIniciar.setForeground(Color.WHITE);
        btnIniciar.setBackground(new Color(248, 188, 7));
        btnIniciar.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
        btnIniciar.setBounds(107, 322, 120, 23);
        panelLogin.add(btnIniciar);

        // EL CONTROLADOR GESTIONA EL LOGIN
        btnIniciar.addActionListener(e -> controlador.iniciarSesion(this));
        
        txtContrasena = new JPasswordField();
        txtContrasena.setFont(new Font("Tahoma", Font.PLAIN, 18));
        txtContrasena.setBounds(21, 235, 281, 46);
        panelLogin.add(txtContrasena);

        
        JLabel logo2 = new JLabel("logo2");
        logo2.setBounds(408, 457, 45, 41);
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
                System.err.println("ERROR EN IMAGEN.");
            }
        }
    }

    // MÉTODOS QUE USA EL CONTROLADOR PARA OBTENER LOS DATOS DE LOGIN Y MOSTRAR ERRORES
    public String getUsuario() {
        return txtUsuario.getText();
    }

    public String getContrasena() {
        return new String(txtContrasena.getPassword());
    }

    public void mostrarError(String mensaje) {
        lblError.setText(mensaje);
        lblError.setVisible(true);
    }
}
