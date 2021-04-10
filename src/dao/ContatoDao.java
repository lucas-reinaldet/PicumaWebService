package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Contato;
import util.Conexao;

public class ContatoDao {

	public List<Contato> persquisarContatosEmpresa(int idEmpresa) {

		Conexao conexao = new Conexao();
		PreparedStatement stm = null;
		ResultSet resultado = null;
		List<Contato> listaContato = new ArrayList<Contato>();
		String sql = "select ec.id_contato AS id, c.contato AS contato, c.tipo_contato AS tipo FROM empresa_contato AS ec INNER JOIN contato AS c ON (ec.id_contato = c.id_contato) WHERE ec.id_empresa = ?";

		try {
			stm = conexao.getConexao().prepareStatement(sql);
			stm.setInt(1, idEmpresa);
			resultado = stm.executeQuery();

			while (resultado.next()) {
				Contato contato = new Contato();
				contato.setIdContato(resultado.getInt("id"));
				contato.setTipoContato(resultado.getString("tipo"));
				contato.setContato(resultado.getString("contato"));
				listaContato.add(contato);
			}
			stm.close();
			resultado.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.desconectar();
		}
		return listaContato;
	}

	public List<Contato> pesquisarContatosCliente(int idCliente) {

		Conexao conexao = new Conexao();
		PreparedStatement stm = null;
		ResultSet resultado = null;
		List<Contato> listaContato = new ArrayList<Contato>();
		String sql = "select cc.id_contato AS id, c.contato AS contato, c.tipo_contato AS tipo FROM cliente_contato AS cc INNER JOIN contato AS c ON (cc.id_contato = c.id_contato) WHERE cc.id_cliente = ?";
		;

		try {
			stm = conexao.getConexao().prepareStatement(sql);
			stm.setInt(1, idCliente);
			resultado = stm.executeQuery();

			while (resultado.next()) {
				Contato contato = new Contato();
				contato.setIdContato(resultado.getInt("id"));
				contato.setTipoContato(resultado.getString("contato"));
				contato.setContato(resultado.getString("tipo"));
				listaContato.add(contato);
			}
			stm.close();
			resultado.close();

		} catch (Exception e) {
			System.out.println("Erro ao buscar informações do id do contato");
			e.printStackTrace();
		} finally {
			conexao.desconectar();
		}
		return listaContato;
	}

	// public List<Contato> buscaContato(int id) {
	// Conexao conexao = new Conexao();
	// PreparedStatement stm = null;
	// ResultSet resultado = null;
	//
	// List<Contato> listaContato = new ArrayList<>();
	//
	// String sql = "select contato, tipo_contato FROM contato WHERE id_contato =
	// ?";
	//
	// try {
	// stm = conexao.getConexao().prepareStatement(sql);
	// stm.setInt(1, id);
	// resultado = stm.executeQuery();
	//
	// while (resultado.next()) {
	// Contato registro = new Contato();
	// registro.setIdContato(id);
	// registro.setContato(resultado.getString("contato"));
	// registro.setTipoContato(resultado.getString("tipo_contato"));
	// listaContato.add(registro);
	// }
	// stm.close();
	// resultado.close();
	//
	// } catch (Exception e) {
	// System.out.println("Erro ao buscar informações do Contato");
	// e.printStackTrace();
	// } finally {
	// conexao.desconectar();
	// }
	// return listaContato;
	// }

	public boolean deletarContatoEmpresa(Contato contato) {
		Conexao conexao = new Conexao();
		PreparedStatement stm = null;
		boolean resultado = false;

		String sql = "delete from empresa_contato where id_contato = ? AND id_empresa = ?";

		try {
			stm = conexao.getConexao().prepareStatement(sql);
			stm.setInt(1, contato.getIdContato());
			stm.setInt(2, contato.getIdEmpresa());

			stm.execute();
			resultado = true;
			stm.close();

		} catch (Exception e) {
			System.out.println("Erro ao tentar deletar associacao contato_empresa!");
			e.printStackTrace();
		} finally {
			conexao.desconectar();
		}
		return resultado;
	}

	public boolean deletarContato(Contato contato) {

		Conexao conexao = new Conexao();
		PreparedStatement stm = null;
		boolean resultado = false;

		String sql = "delete from contato where id_contato = ?";

		try {
			stm = conexao.getConexao().prepareStatement(sql);
			stm.setInt(1, contato.getIdContato());
			stm.execute();
			resultado = true;
			stm.close();

		} catch (Exception e) {
			System.out.println("Erro ao tentar deletar contato!");
			e.printStackTrace();
		} finally {
			conexao.desconectar();
		}
		return resultado;
	}

	public int CadastrarContato(Contato contato) {

		Conexao conexao = new Conexao();
		PreparedStatement stm = null;
		ResultSet result = null;
		int id = 0;

		String sql = "SELECT * FROM cadastro_contato_empresa(?,?,?)";

		try {
			stm = conexao.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stm.setString(1, contato.getContato());
			stm.setString(2, contato.getTipoContato());
			result = stm.getGeneratedKeys();
			if (result.next()) {
				id = result.getInt(1);
			}
			stm.close();
			result.close();

		} catch (Exception e) {
			System.out.println("Erro ao tentar Cadastrar contato!");
			e.printStackTrace();
		} finally {
			conexao.desconectar();
		}
		return id;
	}

	public boolean cadastrarContatoEmpresa(Contato contato) {

		Conexao conexao = new Conexao();
		PreparedStatement stm = null;
		ResultSet result = null;
		int resultado = 0;
		boolean okay = false;
		String sql = "SELECT * FROM cadastro_contato_empresa(?,?,?)";

		try {
			stm = conexao.getConexao().prepareStatement(sql);
			stm.setString(1, contato.getContato());
			stm.setString(2, contato.getTipoContato());
			stm.setInt(3, contato.getIdEmpresa());
			result = stm.executeQuery();
			if (result.next()) {
				resultado = result.getInt(1);
			}
			stm.close();
			result.close();
			
			if (resultado == 3006) {
				okay = true;
			}

		} catch (Exception e) {
			System.out.println("Erro ao tentar Cadastrar contato!");
			e.printStackTrace();
		} finally {
			conexao.desconectar();
		}
		return okay;
	}
}
