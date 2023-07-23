package org.finance.init;

import javax.swing.UIManager;
import com.formdev.flatlaf.FlatLightLaf;

public class App {

	private static final boolean skipLoad = false;
	private static final boolean debug = true;
	private static final String APPNOME = "Finance Org";
	private static final String VERSAO = "1.1.0";

	public static final String getVersao() {
		return VERSAO;
	}

	public static final String getNome() {
		return APPNOME;
	}

	public static final boolean isDebug() {
		return debug;
	}

	private static void defineTheme() {
		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
		} catch (Exception ex) {
			System.err.println("Failed to initialize LaF");
		}
	}

	public static void main(String[] args) {
		System.out.println("Iniciando a aplicação!");
		System.out.println("Versão: " + getVersao());

		defineTheme();

		if (skipLoad) {
			FrmHome home = new FrmHome();
			home.setVisible(true);
			return;
		}

		FrmLoad load = new FrmLoad();
		load.setVisible(true);
	}
}
