package br.com.mcf.view;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.formdev.flatlaf.FlatLightLaf;

public class App {

	private static final boolean skipLoad = false;
	private static final boolean debug = true;
	private static final String APPNOME = "Finance Org";
	private static final String VERSAO = "1.0.0";

	public static final String getVersao() {
		return VERSAO;
	}

	public static final String getNome() {
		return APPNOME;
	}

	public static final boolean isDebug() {
		return debug;
	}

	private static void defineTheme(String tema) {

		try {

			switch (tema) {
			case "Windows":
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
				break;
			case "Nimbus":
				UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
				break;

			default:
				UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
				break;
			}

		} catch (Exception ex) {
			System.err.println("Failed to initialize LaF");
		}

		// Windows
		// UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

		// Windows Classic
		// UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");

		// metal
		// UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

		// Nimbus
		// UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

		// Motif
		// UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");

	}

	public static void main(String[] args) {
		System.out.println("Iniciando a aplicação!");
		System.out.println("Versão: " + getVersao());

		defineTheme("Nimbus");

		if (skipLoad) {
			FrmHome home = new FrmHome();
			home.setVisible(true);
			return;
		}

		FrmLogin login = new FrmLogin();
		login.setVisible(true);
	}

}
