package br.com.mcf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.mcf.model.Usuario;

public class UsuarioDao {

	private Connection conexao = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;

	public boolean login(Usuario usuario) {

		String sql = "SELECT * FROM USUARIOS WHERE ATIVO='S' AND USUARIO = ? AND SENHA = ?";

		try {

			conexao = Conexao.getInstance();
			pst = conexao.prepareStatement(sql);
			pst.setString(1, usuario.getUsuario());
			pst.setString(2, usuario.getSenha());
			rs = pst.executeQuery();

			if (rs.next()) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;

	}

}
