package org.finance.init;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import org.finance.init.dao.ModuloConexao;
import javax.swing.JPasswordField;

public class FrmLoad extends JFrame {

	private static final long serialVersionUID = 1L;

	// objetos
	private Connection conexao = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	private Connection conexaoTecgesco = null;
	private PreparedStatement pstTecgesco = null;
	ResultSet rsTecgesco = null;

	// componentes
	private JPanel contentPane;
	JLabel background;
	private static MessageDigest md = null;
	private JTextField txtUsuario;

	public FrmLoad() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_F2) {
					FrmEditor frm = new FrmEditor();
					frm.setVisible(true);
				}
			}
		});

		setUndecorated(true);
		setType(Type.UTILITY);
		setRootPaneCheckingEnabled(false);

		setBackground(Color.WHITE);
		setTitle("MDPLUS BY TECGESCO GESTAO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 250);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(null);
		panel_1.setBounds(0, 1, 500, 249);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("F2 - Conex\u00E3o");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(365, 10, 99, 14);
		panel_1.add(lblNewLabel_1);

		JLabel lblV = new JLabel(App.getNome() + " " + App.getVersao());
		lblV.setBounds(0, 9, 499, 14);
		panel_1.add(lblV);
		lblV.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblV.setHorizontalAlignment(SwingConstants.CENTER);
		lblV.setForeground(Color.WHITE);

		JLabel lblNewLabel = new JLabel("X");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(474, 9, 15, 14);
		panel_1.add(lblNewLabel);
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtUsuario.setBounds(258, 97, 206, 34);
		panel_1.add(txtUsuario);
		txtUsuario.setColumns(10);

		JButton btnNewButton = new JButton("ENTRAR");
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnNewButton.setBounds(258, 197, 206, 34);
		panel_1.add(btnNewButton);

		JLabel lblNewLabel_2 = new JLabel("USUARIO");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(150, 97, 96, 34);
		panel_1.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("SENHA");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(160, 143, 86, 34);
		panel_1.add(lblNewLabel_3);

		JPasswordField txtSenha = new JPasswordField();
		txtSenha.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtSenha.setBounds(258, 143, 206, 34);
		panel_1.add(txtSenha);

		JLabel lblCapaLoad = new JLabel("");
		lblCapaLoad.setIcon(new ImageIcon(FrmLoad.class.getResource("/img/capaLoad.png")));
		lblCapaLoad.setHorizontalAlignment(SwingConstants.CENTER);
		lblCapaLoad.setBounds(0, 0, 499, 249);
		panel_1.add(lblCapaLoad);

		JLabel lblLogo = new JLabel("New label");
		lblLogo.setBounds(26, 97, 100, 100);
		panel_1.add(lblLogo);

		// validacao_init();

	}

	private synchronized void validacao_init() {
		new Thread() {

			@Override
			public void run() {

				if (verificaArqConfig()) {

				} else {
					dispose();
					System.out.println("Arquivo não encontrado");
					FrmEditor tela = new FrmEditor();
					tela.setVisible(true);
				}

			}
		}.start();

	}

	public boolean verificaArqConfig() {
		File f = new File("config.properties");

		if (f.exists()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean verifica_status() {
		conexao = ModuloConexao.conector();
		if (conexao != null) {
			return true;
		} else {
			dispose();
			FrmEditor tela = new FrmEditor();
			tela.setVisible(true);
			return false;

		}
	}

	static {
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}
	}

	private static char[] hexCodes(byte[] text) {
		char[] hexOutput = new char[text.length * 2];
		String hexString;

		for (int i = 0; i < text.length; i++) {
			hexString = "00" + Integer.toHexString(text[i]);
			hexString.toUpperCase().getChars(hexString.length() - 2, hexString.length(), hexOutput, i * 2);
		}
		return hexOutput;
	}

	public static String criptografar(String pwd) {
		if (md != null) {
			return new String(hexCodes(md.digest(pwd.getBytes())));
		}
		return null;
	}
}
