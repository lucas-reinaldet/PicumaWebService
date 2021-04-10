package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao extends DadosPostgres{
	
	private Connection conexao = null;

	public Conexao() {

		super();
		
		conectar();

	}

	Connection con() {

		return this.conexao;

	}

	public Connection getConexao() {

		return conexao;

	}

	private void conectar() {

		try {

			Class.forName(DRIVER).newInstance();

			Connection con = null;

			this.conexao = DriverManager.getConnection(URL, USUARIO, SENHA);

		} catch (Exception e) {

			System.out.println("Problema ao connectar com o banco de dados " + URL + ":" + e);

		}

	}

	public void desconectar() {

		try {

			if (this.conexao != null) {

				this.conexao.close();

			}

		} catch (Exception e) {

			System.out.println("Problemas ao desconectar da base ou base já desconectada.");

		}

	}

}
