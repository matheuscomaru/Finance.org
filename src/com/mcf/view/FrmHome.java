package com.mcf.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.DebugGraphics;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

import com.mcf.dao.ModuloConexao;

public class FrmHome extends JFrame {
	private JPanel contentPane;
	Connection conexao = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	Connection conexaoTecgesco = null;
	PreparedStatement pstTecgesco = null;
	ResultSet rsTecgesco = null;
	JLabel lbl_nomeuser;
	JLabel background;
	private static MessageDigest md = null;
	JLabel txt_versao;
	JLabel lblNewLabel;
	JLabel txtNovaVersao;
	private JDesktopPane desktopPane;
	private String senhaSuporte = "master";
	App dtuniversal = new App();
	Sessao auth = new Sessao();
	private JLabel txt_status;
	private JLabel lbl_tecgescolicenca;
	private JToolBar toolBar;
	private JLabel iconStar;

	public FrmHome() {

		setBackground(Color.WHITE);
		setTitle("Finance Org");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 785, 422);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));

		JMenuBar menuBar = new JMenuBar();
		menuBar.setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);
		menuBar.setBorder(null);
		menuBar.setBackground(SystemColor.menu);
		setJMenuBar(menuBar);

		toolBar = new JToolBar();
		toolBar.setToolTipText(
				"Para adicionar uma tela como favorito, basta clicar com bot\u00E3o direito em uma \u00E1rea vazia na tela. (Nem todas as telas podem ser colocadas como favorito)");
		toolBar.setBorder(new LineBorder(SystemColor.activeCaptionBorder));
		toolBar.setFloatable(false);
		contentPane.add(toolBar, BorderLayout.NORTH);

		JMenu mnRestrito = new JMenu("Restrito");
		mnRestrito.setFont(new Font("Segoe UI", Font.BOLD, 12));
		menuBar.add(mnRestrito);

		JMenuItem mntmConexo = new JMenuItem("Conex\u00E3o Banco de Dados");
		mntmConexo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mntmConexo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				FrmEditor editor = new FrmEditor();
				editor.setVisible(true);
			}
		});
		mnRestrito.add(mntmConexo);

		JMenuItem mntmNewMenuItem = new JMenuItem("Atualiza\u00E7\u00E3o / Notas vers\u00E3o");
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mntmNewMenuItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				FrmUpdate frm_UPDATE = new FrmUpdate();
				frm_UPDATE.setVisible(true);
			}
		});
		mnRestrito.add(mntmNewMenuItem);

		lblNewLabel = new JLabel("Finance Org");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setForeground(SystemColor.textHighlight);
		panel.add(lblNewLabel);

		txt_versao = new JLabel(dtuniversal.getVersao());
		txt_versao.setForeground(SystemColor.textHighlight);
		txt_versao.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(txt_versao);

		txt_status = new JLabel("STATUS");
		txt_status.setForeground(SystemColor.textHighlight);
		txt_status.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(txt_status);

		lbl_nomeuser = new JLabel("NOME USER");
		lbl_nomeuser.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_nomeuser.setForeground(SystemColor.textHighlight);
		lbl_nomeuser.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel.add(lbl_nomeuser);

		txtNovaVersao = new JLabel(
				"NOVA VERS\u00C3O DISPON\u00CDVEL, CLIQUE AQUI PARA REALIZAR A ATUALIZA\u00C7\u00C3O");
		txtNovaVersao.setVisible(false);
		txtNovaVersao.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				FrmUpdate frm_UPDATE = new FrmUpdate();
				frm_UPDATE.setVisible(true);
			}
		});
		txtNovaVersao.setBackground(Color.BLACK);
		txtNovaVersao.setForeground(Color.RED);
		txtNovaVersao.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(txtNovaVersao);

		lbl_tecgescolicenca = new JLabel("");
		lbl_tecgescolicenca.setBackground(Color.BLACK);
		lbl_tecgescolicenca.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_tecgescolicenca.setForeground(Color.RED);
		panel.add(lbl_tecgescolicenca);

		validacao_init();
	}

	private void validacao_init() {
		new Thread() {

			@Override
			public void run() {
				conexao = ModuloConexao.conector();
				verifica_status();
				lbl_nomeuser.setText(auth.getUsuario() + "-" + auth.getPerfil());

			}
		}.start();
	}

	public void verifica_status() {
		if (conexao != null) {
			txt_status.setText("CONECTADO");
			txt_status.setForeground(new Color(0, 128, 0));
		} else {
			txt_status.setText("DESCONECTADO");
			txt_status.setForeground(Color.red);

		}
	}

}
