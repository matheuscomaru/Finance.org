package org.finance.init;

public class Sessao {

	static int iduser = 0, chaveempresa = 0, chaveperfil = 0;
	static String usuario = "", perfil = "", cnpjcpf = "", cnpjprop = "", versao = "";

	public static String getVersao() {
		return versao;
	}

	public static void setVersao(String versao) {
		Sessao.versao = versao;
	}

	public static String getCnpjcpf() {
		return cnpjcpf;
	}

	public static void setCnpjcpf(String cnpjcpf) {
		Sessao.cnpjcpf = cnpjcpf;
	}

	public static String getCnpjprop() {
		return cnpjprop;
	}

	public static void setCnpjprop(String cnpjprop) {
		Sessao.cnpjprop = cnpjprop;
	}

	public static String getUsuario() {
		return usuario;
	}

	public static void setUsuario(String usuario) {
		Sessao.usuario = usuario;
	}

	public int getIduser() {
		return iduser;
	}

	public static void setIduser(int iduser) {
		Sessao.iduser = iduser;
	}

	public static int getChaveperfil() {
		return chaveperfil;
	}

	public static void setChaveperfil(int chaveperfil) {
		Sessao.chaveperfil = chaveperfil;
	}

	public static String getPerfil() {
		return perfil;
	}

	public static void setPerfil(String perfil) {
		Sessao.perfil = perfil;
	}

	public static int getChaveempresa() {
		return chaveempresa;
	}

	public static void setChaveempresa(int chaveempresa) {
		Sessao.chaveempresa = chaveempresa;
	}

}
