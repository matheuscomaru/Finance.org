package com.mcf.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.mcf.dao.ConfigDao;

public class FrmEditor extends JFrame {

	private JPanel contentPane;
	private JTextField txt_ip;
	Connection conexao = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	private JTextField txt_cnpj;
	private JTextField txt_porta;
	private JTextField txt_dir;
	String url, user, senha;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	ConfigDao configDao = new ConfigDao();

	public FrmEditor() {

		setTitle("Conex\u00E3o Firebird");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 271, 229);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"IP/Endere\u00E7o Servidor", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(6, 11, 239, 45);
		contentPane.add(panel);
		panel.setLayout(null);

		txt_ip = new JTextField();
		txt_ip.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyChar() == KeyEvent.VK_ENTER) {

					txt_cnpj.requestFocus();
					txt_cnpj.selectAll();

				}

			}
		});
		txt_ip.setBounds(10, 16, 219, 20);
		panel.add(txt_ip);
		txt_ip.setText("localhost");
		txt_ip.setColumns(10);

		JButton btnSalvar = new JButton("SALVAR DADOS");
		btnSalvar.setBackground(SystemColor.menu);
		btnSalvar.setForeground(new Color(0, 128, 0));
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				salvar();

			}
		});
		btnSalvar.setBounds(6, 154, 239, 25);
		contentPane.add(btnSalvar);

		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"CNPJ (Sem pontua\u00E7\u00E3o)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(6, 58, 239, 45);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		txt_cnpj = new JTextField();
		txt_cnpj.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyChar() == KeyEvent.VK_ENTER) {

					txt_porta.requestFocus();
					txt_porta.selectAll();

				}

			}
		});
		txt_cnpj.setBounds(10, 16, 219, 20);
		panel_1.add(txt_cnpj);
		txt_cnpj.setText("00000000000001");
		txt_cnpj.setColumns(10);

		panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "PORTA",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(6, 103, 76, 45);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		txt_porta = new JTextField();
		txt_porta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyChar() == KeyEvent.VK_ENTER) {

					txt_dir.requestFocus();
					txt_dir.selectAll();

				}

			}
		});
		txt_porta.setBounds(10, 16, 56, 20);
		panel_2.add(txt_porta);
		txt_porta.setText("3050");
		txt_porta.setColumns(10);

		panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"DIRET\u00D3RIO", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.setBounds(92, 103, 92, 45);
		contentPane.add(panel_3);
		panel_3.setLayout(null);

		txt_dir = new JTextField();
		txt_dir.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {

					btnSalvar.requestFocus();

				}
			}
		});
		txt_dir.setBounds(10, 16, 72, 20);
		panel_3.add(txt_dir);
		txt_dir.setText("C");
		txt_dir.setColumns(10);

		ler();
	}

	public void salvar() {

		String ip = txt_ip.getText();
		String porta = txt_porta.getText().toString();
		String dir = txt_dir.getText().toString();
		String cnpj = txt_cnpj.getText().toString().replace("/", "").replace("-", "").replace(".", "");

		configDao.salvarConfig("db.url", "jdbc:firebirdsql:" + ip + "/" + porta + ":" + dir + ":\\\\CHSISTEMAS\\\\"
				+ cnpj + "\\\\DADOS\\\\DADOS.FDB?encoding=ISO8859_1");

		configDao.salvarConfig("db.ip", ip);
		configDao.salvarConfig("db.porta", porta);
		configDao.salvarConfig("db.diretorio", dir);
		configDao.salvarConfig("db.cnpj", cnpj);

		JOptionPane.showMessageDialog(null, "Dados de conex�o salvos com sucesso.");
		System.exit(0);

	}

	public void ler() {

		txt_ip.setText(configDao.lerConfig("db.ip"));
		txt_porta.setText(configDao.lerConfig("db.porta"));
		txt_dir.setText(configDao.lerConfig("db.diretorio"));
		txt_cnpj.setText(configDao.lerConfig("db.cnpj"));

	}
}
