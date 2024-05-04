/**
 * 
 */
package br.com.mcf.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/* 
 * @author Matheus Comaru
 * Data 22 de jun. de 2021
 *
 */
public class ConfigDao {

	public void salvarConfig(String param, String value) {

		try {
			FileInputStream in = new FileInputStream("config.properties");
			Properties props = new Properties();
			props.load(in);
			in.close();

			FileOutputStream out = new FileOutputStream("config.properties");
			props.setProperty(param, value);
			props.store(out, null);
			out.close();

		} catch (IOException io) {
			io.printStackTrace();
		}

	}

	public String lerConfig(String param) {

		try (InputStream input = new FileInputStream("config.properties")) {

			Properties prop = new Properties();
			prop.load(input);
			if (prop.containsKey(param)) {
				return prop.getProperty(param);
			} else {
				System.out.println("A propriedade " + param + " n�o foi encontrada.");
			}

		} catch (IOException ex) {
			ex.printStackTrace();

		}
		return "";
	}

}
