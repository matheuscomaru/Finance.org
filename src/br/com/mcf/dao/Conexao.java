package br.com.mcf.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	private static Connection db = null;

	public static Connection getInstance() {

		if (db == null) {
			return conectar();
		} else {
			return db;
		}

	}

	private static Connection conectar() {

		String url = "jdbc:firebirdsql://localhost:3050/C:\\MCF\\DB\\DB.FDB";
		String user = "sysdba";
		String password = "masterkey";

		try {

			String driver = "org.firebirdsql.jdbc.FBDriver";
			Class.forName(driver);
			return DriverManager.getConnection(url, user, password);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
