package org.finance.init.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ModuloConexao {

	static ConfigDao configDao = new ConfigDao();

	public static Connection conector() {

		java.sql.Connection conexao = null;

		String url;
		String user = "sysdba";
		String password = "@CHx2021$";
		try {

			url = configDao.lerConfig("db.url");

			String driver = "org.firebirdsql.jdbc.FBDriver";
			Class.forName(driver);
			conexao = DriverManager.getConnection(url, user, password);

			return conexao;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
