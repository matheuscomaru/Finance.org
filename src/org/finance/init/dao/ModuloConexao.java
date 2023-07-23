package org.finance.init.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import org.finance.init.Sessao;

public class ModuloConexao {

	private static String urlMysql = "sql807.main-hosting.eu";
	private static String databaseMysql = "u597519790_mdplus";
	private static String userMysql = "u597519790_mdplus";
	private static String passwordMysql = "Mdplus2021Master";
	static ConfigDao configDao = new ConfigDao();

	public static Connection conector() {

		java.sql.Connection conexao = null;

		String url;
		String user = "sysdba";
		String password = "@CHx2021$";
		try {

			Sessao auth = new Sessao();

			auth.setCnpjprop(configDao.lerConfig("db.cnpj"));

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

	public static Connection conectorMysql() {

		java.sql.Connection conexao = null;

		try {

			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);

			conexao = DriverManager.getConnection("jdbc:mysql://" + urlMysql + ":3306/" + databaseMysql, userMysql,
					passwordMysql);

			return conexao;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
