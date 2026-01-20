package Vista;

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

public class Perfil extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    private JTextField textField_usuario;
    private JTextField textField_nombre;
    private JTextField textField_apellido;
    private JTextField textField_correo;
    private JTextField textField_tlf1;
    private JTextField textField_tlf2;
    private JTextField textField_dni;
    private JTextField textField_direccion;

    // CONSTRUCTOR DEL PERFIL
    public Perfil(Controlador controlador) {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 894, 558);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // LOGO SUPERIOR
        JLabel logo1 = new JLabel("logo");
        logo1.setBounds(683, 17, 168, 58);
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
            }
        }

        // PANEL PRINCIPAL
        JPanel panelLogin = new JPanel();
        panelLogin.setBackground(new Color(255, 255, 255));
        panelLogin.setForeground(new Color(196, 225, 255));
        panelLogin.setBorder(new LineBorder(new Color(0, 64, 128), 2, true));
        panelLogin.setBounds(207, 108, 466, 335);
        contentPane.add(panelLogin);
        panelLogin.setLayout(null);

        // LABELS
        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setForeground(new Color(0, 64, 128));
        lblUsuario.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 14));
        lblUsuario.setBounds(10, 11, 72, 29);
        panelLogin.add(lblUsuario);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setForeground(new Color(0, 64, 128));
        lblNombre.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 14));
        lblNombre.setBounds(10, 51, 72, 29);
        panelLogin.add(lblNombre);

        JLabel lblApellido = new JLabel("Apellido:");
        lblApellido.setForeground(new Color(0, 64, 128));
        lblApellido.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 14));
        lblApellido.setBounds(10, 91, 72, 29);
        panelLogin.add(lblApellido);

        JLabel lblCorreo = new JLabel("Correo:");
        lblCorreo.setForeground(new Color(0, 64, 128));
        lblCorreo.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 14));
        lblCorreo.setBounds(10, 131, 72, 29);
        panelLogin.add(lblCorreo);

        JLabel lblDireccion = new JLabel("Dirección:");
        lblDireccion.setForeground(new Color(0, 64, 128));
        lblDireccion.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 14));
        lblDireccion.setBounds(10, 171, 72, 29);
        panelLogin.add(lblDireccion);

        JLabel lblTelefono = new JLabel("Teléfono 1:");
        lblTelefono.setForeground(new Color(0, 64, 128));
        lblTelefono.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 14));
        lblTelefono.setBounds(10, 211, 82, 29);
        panelLogin.add(lblTelefono);

        JLabel lblTelefono1 = new JLabel("Teléfono 2:");
        lblTelefono1.setForeground(new Color(0, 64, 128));
        lblTelefono1.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 14));
        lblTelefono1.setBounds(10, 251, 82, 29);
        panelLogin.add(lblTelefono1);

        JLabel lblDni = new JLabel("DNI:");
        lblDni.setForeground(new Color(0, 64, 128));
        lblDni.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 14));
        lblDni.setBounds(10, 291, 82, 29);
        panelLogin.add(lblDni);

        // CAMPOS DE TEXTO (SOLO VISTA)
        textField_usuario = crearCampo(panelLogin, 92, 11);
        textField_nombre = crearCampo(panelLogin, 92, 51);
        textField_apellido = crearCampo(panelLogin, 92, 91);
        textField_correo = crearCampo(panelLogin, 92, 131);
        textField_direccion = crearCampo(panelLogin, 92, 171);
        textField_tlf1 = crearCampo(panelLogin, 92, 211);
        textField_tlf2 = crearCampo(panelLogin, 92, 251);
        textField_dni = crearCampo(panelLogin, 92, 291);

        // TÍTULO
        JLabel lblTitulo = new JLabel("Tu perfil");
        lblTitulo.setBounds(10, 17, 858, 58);
        contentPane.add(lblTitulo);
        lblTitulo.setForeground(new Color(0, 64, 128));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 50));

        // BOTÓN VOLVER
        JButton btnVolver = new JButton("Volver");
        btnVolver.setBackground(new Color(248, 188, 7));
        btnVolver.setForeground(new Color(255, 255, 255));
        btnVolver.setBounds(55, 455, 98, 25);
        btnVolver.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
        contentPane.add(btnVolver);

        // EL CONTROLADOR GESTIONA LA ACCIÓN
        btnVolver.addActionListener(e -> controlador.abrirMenu(this));

        // LOGO INFERIOR
        JLabel logo2 = new JLabel("logo2");
        logo2.setBounds(411, 454, 45, 41);
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
            }
        }

        // EL CONTROLADOR CARGA LOS DATOS
        controlador.rellenarPerfil(this);
    }

    // MÉTODO AUXILIAR PARA CREAR CAMPOS
    private JTextField crearCampo(JPanel panel, int x, int y) {
        JTextField campo = new JTextField();
        campo.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
        campo.setEnabled(false);
        campo.setEditable(false);
        campo.setBounds(x, y, 364, 29);
        panel.add(campo);
        return campo;
    }

    // SETTERS PARA QUE EL CONTROLADOR PUEDA RELLENAR LOS DATOS
    public void setUsuario(String s) { textField_usuario.setText(s); }
    public void setNombre(String s) { textField_nombre.setText(s); }
    public void setApellido(String s) { textField_apellido.setText(s); }
    public void setCorreo(String s) { textField_correo.setText(s); }
    public void setDireccion(String s) { textField_direccion.setText(s); }
    public void setTelefono1(String s) { textField_tlf1.setText(s); }
    public void setTelefono2(String s) { textField_tlf2.setText(s); }
    public void setDni(String s) { textField_dni.setText(s); }
}
