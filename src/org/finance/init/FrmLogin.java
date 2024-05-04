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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.border.BevelBorder;

public class FrmLogin extends JFrame {

	// componentes
	private JPanel contentPane;
	JLabel background;
	private static MessageDigest md = null;
	private JTextField txtUsuario;
	JPasswordField txtSenha;

	public FrmLogin() {

		setTitle("Login");
		setRootPaneCheckingEnabled(false);

		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 497, 313);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(0, 0, 153, 274);
		contentPane.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_2 = new JLabel("USUARIO");
		lblNewLabel_2.setBounds(155, 128, 96, 34);
		contentPane.add(lblNewLabel_2);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 18));

		txtUsuario = new JTextField();
		txtUsuario.setBounds(263, 128, 206, 34);
		contentPane.add(txtUsuario);
		txtUsuario.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtUsuario.setColumns(10);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(263, 174, 206, 34);
		contentPane.add(txtSenha);
		txtSenha.setFont(new Font("Segoe UI", Font.BOLD, 18));

		JLabel lblNewLabel_3 = new JLabel("SENHA");
		lblNewLabel_3.setBounds(165, 174, 86, 34);
		contentPane.add(lblNewLabel_3);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD, 18));

		JButton btnNewButton = new JButton("ENTRAR");
		btnNewButton.setBounds(263, 228, 206, 34);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 18));

	}

	private void login() {

		String usuario = txtUsuario.getText();
		String senha = String.valueOf(txtSenha.getPassword());

	}
}
