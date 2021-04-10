package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Cliente;
import model.Empresa;
import model.Login;
import util.Conexao;

public class LoginDao {

	public Login realizarLogin(Login dados) {

		Conexao conexao = new Conexao();
		PreparedStatement stm = null;
		ResultSet resultado = null;
		Login login = new Login();
		Cliente cliente = new Cliente();
		Empresa empresa = new Empresa();

		String sql = "SELECT id_login, usuario, senha, login_google, id_cliente, id_empresa FROM login WHERE usuario = ? AND senha = ? OR login_google = ?";

		try {
			stm = conexao.getConexao().prepareStatement(sql);
			stm.setString(1, dados.getUsuario());
			stm.setString(2, dados.getSenha());
			stm.setString(3, dados.getLoginGoogle());

			resultado = stm.executeQuery();
			while (resultado.next()) {
				
				cliente.setIdCliente(resultado.getInt("id_cliente"));
				empresa.setIdEmpresa(resultado.getInt("id_empresa"));
				login.setIdLogin(resultado.getInt("id_login"));
				login.setUsuario(resultado.getString("usuario"));
				login.setSenha(resultado.getString("senha"));
				login.setLoginGoogle(resultado.getString("login_google"));
				login.setCliente(cliente);
				login.setEmpresa(empresa);
			}
			stm.close();
			resultado.close();

		} catch (Exception e) {

			System.out.println("Erro ao realizar login ");
			e.printStackTrace();

		} finally {
			conexao.desconectar();
		}

		return login;
	}

}
