package org.finance.init;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.finance.init.dao.ModuloConexao;

public class FrmUpdate extends JFrame {

	private JPanel contentPane;
	private Connection conexao = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	private Connection conexaoTecgesco = null;
	private PreparedStatement pstTecgesco = null;
	private ResultSet rsTecgesco = null;
	private String version;
	private String matriz[];
	private String temp = System.getProperty("java.io.tmpdir");
	private JButton btnNewButton;
	private JProgressBar progressBar;
	private JLabel status;
	private App app = new App();
	private JTextArea txtNotasVersao;
	private String urlDownload = "";

	public FrmUpdate() {

		setBackground(SystemColor.menu);
		setTitle("Atualiza\u00E7\u00F5es");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 346, 350);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		btnNewButton = new JButton("ATUALIZAR");
		btnNewButton.setBackground(SystemColor.menu);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				update();

			}
		});
		btnNewButton.setForeground(SystemColor.textHighlight);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.setBounds(10, 230, 310, 39);
		contentPane.add(btnNewButton);

		progressBar = new JProgressBar();
		progressBar.setIndeterminate(true);
		progressBar.setVisible(false);
		progressBar.setBounds(0, 297, 330, 14);
		contentPane.add(progressBar);

		status = new JLabel("PRONTO");
		status.setFont(new Font("Tahoma", Font.BOLD, 11));
		status.setForeground(SystemColor.textHighlight);
		status.setHorizontalAlignment(SwingConstants.CENTER);
		status.setHorizontalTextPosition(SwingConstants.CENTER);
		status.setBounds(0, 274, 330, 14);
		contentPane.add(status);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 310, 209);
		contentPane.add(scrollPane);

		txtNotasVersao = new JTextArea();
		txtNotasVersao.setWrapStyleWord(true);
		txtNotasVersao.setLineWrap(true);
		scrollPane.setViewportView(txtNotasVersao);

		validacao_init();

	}

	private void validacao_init() {
		new Thread() {

			@Override
			public void run() {

				progressBar.setVisible(true);
				btnNewButton.setEnabled(false);

				status.setText("Verificando notas versão.");

				verificaVersao();

				progressBar.setVisible(false);
				btnNewButton.setEnabled(true);

				status.setText("Pronto.");
			}
		}.start();
	}

	private void verificaVersao() {

		String sql = " SELECT v.versao,app.url, v.obs FROM versoes v, apps app WHERE v.app = app.nome and app=? "
				+ " order by v.id desc limit 1";

		try {
			pstTecgesco = conexaoTecgesco.prepareStatement(sql);
			pstTecgesco.setString(1, app.getNome());
			ResultSet rs = pstTecgesco.executeQuery();
			if (rs.next()) {

				urlDownload = rs.getString("url");
				txtNotasVersao.setText(rs.getString("obs"));

			}

			conexaoTecgesco.close();

		} catch (SQLException e1) {
			System.out.println(e1);
			JOptionPane.showMessageDialog(null, e1);
		}

	}

	private void atualizar() {

		String caminhoJar = System.getProperty("user.dir");

		System.out.println("METODO CHAMADO - atualizar()");

		if (urlDownload.isEmpty() || urlDownload.length() <= 0) {

			JOptionPane.showMessageDialog(null, urlDownload + " não é uma URL válida.");

		} else {

			System.out.println("URL = " + urlDownload);
			status.setText("Atualizando aplicação");
			download(urlDownload, caminhoJar, null, 0);

		}
	}

//	public void atualizarBD() {
//		status.setText("Atualizando banco de dados");
//		String temp = System.getProperty("java.io.tmpdir");
//		System.out.println("temp:" + temp);
//		status.setText("temp:" + temp);
//		String urlUpdateFile = "https://drive.google.com/u/0/uc?id=12ZL4zvKG3ztrWm-q7htIkJ4DOSeSJZr4&export=download";
//		download(urlUpdateFile, temp + "updatedb.txt", null, 0);
//		PROCESSAR_ARQUIVO();
//		
//
//	}

	private void download(String address, String localFileName, String host, int porta) {

		InputStream in = null;
		URLConnection conn = null;
		OutputStream out = null;

		try {

			URL url = new URL(address);

			out = new FileOutputStream("MDPlus.exe");

			if (host != "" && host != null) {
				Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(host, porta));
				conn = url.openConnection(proxy);
			} else {
				conn = url.openConnection();
			}

			in = conn.getInputStream();
			byte[] buffer = new byte[1024];
			int numRead;
			long numWritten = 0;
			while ((numRead = in.read(buffer)) != -1) {
				out.write(buffer, 0, numRead);
				numWritten += numRead;
				System.out.println("numWritten:" + numWritten);
			}
			System.out.println(localFileName + "\t" + numWritten);

		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException ioe) {
			}
		}
	}

	private static int QUANTIDADE_LINHAS() {
		String temp = System.getProperty("java.io.tmpdir");
		LineNumberReader lineCounter;
		try {
			lineCounter = new LineNumberReader(new InputStreamReader(new FileInputStream(temp + "updatedb.txt")));

			String nextLine = null;
			try {
				while ((nextLine = lineCounter.readLine()) != null) {
					if (nextLine == null)
						break;

				}
				return lineCounter.getLineNumber();
			} catch (Exception done) {
				done.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}

	private void PROCESSAR_ARQUIVO() {

		String temp = System.getProperty("java.io.tmpdir");
		try {

			int QTD_ITENS = 0;
			Scanner sc = null;
			matriz = new String[QUANTIDADE_LINHAS()];
			int count = 0;

			try {
				sc = new Scanner(new File(temp + "updatedb.txt"));
				while (sc.hasNextLine()) {
					matriz[count] = sc.nextLine();
					count++;

				}

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			for (int i = 0; i < matriz.length; i++) {

				System.out.println(matriz[i]);
				UPDATE_BD(matriz[i]);

			}

		} catch (Exception e) {

		}

	}

	private void UPDATE_BD(String SQL) {
		System.out.println("=======================");
		System.out.println("EXE-UPDATE_BD");

		try {
			pst = conexao.prepareStatement(SQL);

			if (pst.executeUpdate() > 0) {
				System.out.println("UPDATE_BD - SUCESSO SQL:" + SQL);

			}

		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1);
		}

	}

	private synchronized void obtenVersao() {

		new Thread() {

			@Override
			public void run() {
				status.setText("Obtendo versão publicada");
				progressBar.setVisible(true);
				conexaoTecgesco = ModuloConexao.conectorMysql();
				progressBar.setVisible(false);
				status.setText(null);
			}
		}.start();

	}

	private synchronized void update() {

		new Thread() {

			@Override
			public void run() {
				progressBar.setVisible(true);
				btnNewButton.setEnabled(false);
				status.setText("Atualizando aplicação");
				atualizar();
				progressBar.setVisible(false);
				status.setText(null);
				btnNewButton.setEnabled(true);
				JOptionPane.showMessageDialog(null, "Sua aplicação foi atualizada com sucesso.");
				System.exit(0);

			}
		}.start();

	}
}
