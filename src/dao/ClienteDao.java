package dao;

import java.sql.Array;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import model.Cliente;
import model.Favoritos;
import model.Login;
import util.Conexao;

public class ClienteDao {

	public int cadastrar(Array dados, Login login) {

		Conexao conexao = new Conexao();
		boolean resultado = false;
		PreparedStatement stm = null;
		ResultSet result = null;
		int idCadastro = 0;

		String sql = "SELECT * FROM cadastro_dados_cliente(?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			stm = conexao.getConexao().prepareStatement(sql);
			stm.setString(1, login.getCliente().getNomeCliente());
			stm.setDate(2, new Date(login.getCliente().getNascimentoCliente().getTimeInMillis()));
			stm.setString(3, login.getCliente().getCpfCliente());
			stm.setBytes(4, login.getCliente().getFotoCliente());
			stm.setString(5, login.getCliente().getGenero());
			stm.setArray(6, dados);
			stm.setString(7, login.getUsuario());
			stm.setString(8, login.getSenha());
			stm.setString(9, login.getLoginGoogle());
			result = stm.executeQuery();
			if (result.next()) {
				idCadastro = result.getInt(1);
			}
			stm.close();
			result.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.desconectar();
		}
		return idCadastro;
	}

	public Cliente buscaPorId(int id) {
		Conexao conexao = new Conexao();
		PreparedStatement stm = null;
		ResultSet resultado = null;
		Cliente cliente = new Cliente();

		String sql = "SELECT nome_cliente, nascimento_cliente, cpf_cliente, foto_cliente,"
				+ "genero FROM cliente WHERE id_cliente = ?";

		try {

			stm = conexao.getConexao().prepareStatement(sql);
			stm.setInt(1, id);
			resultado = stm.executeQuery();

			while (resultado.next()) {
				Date date = Date.valueOf(resultado.getDate("nascimento_cliente").toString());
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				cliente.setIdCliente(id);
				cliente.setNomeCliente(resultado.getString("nome_cliente"));
				cliente.setNascimentoCliente(calendar);
				cliente.setCpfCliente(resultado.getString("cpf_cliente"));
				cliente.setFotoCliente(resultado.getBytes("foto_cliente"));
				cliente.setGenero("genero");
			}
			stm.close();
			resultado.close();

		} catch (Exception e) {

			System.out.println("Erro ao buscar informações do Cliente");
			e.printStackTrace();

		} finally {
			conexao.desconectar();
		}
		return cliente;
	}

	public List<Favoritos> listarFavoritos(Integer idCliente) {

		Conexao conexao = new Conexao();
		PreparedStatement stm = null;
		ResultSet resultSet = null;

		List<Favoritos> listaFavoritos = new ArrayList<>();

		String sql = "SELECT e.id_empresa AS id, e.nome_fantasia AS nome, e.logo_empresa AS logo, "
				+ "aa.foto_area_atuacao AS foto_area_atuacao FROM cliente_empresa AS ce "
				+ "INNER JOIN empresa AS e ON (ce.id_empresa = e.id_empresa) "
				+ "INNER JOIN area_atuacao AS aa ON (e.id_area_atuacao = aa.id_Area_atuacao) "
				+ "WHERE e.empresa_ativada = true AND ce.id_cliente = ? ORDER BY e.id_empresa ASC";

		try {
			stm = conexao.getConexao().prepareStatement(sql);
			stm.setInt(1, idCliente);
			resultSet = stm.executeQuery();
			while (resultSet.next()) {
				Favoritos favoritos = new Favoritos();
				favoritos.setIdEmpresa(resultSet.getInt("id"));
				favoritos.setNomeFantasia(resultSet.getString("nome"));
				favoritos.setLogoEmpresa(resultSet.getBytes("logo"));
				favoritos.setFotoAreaAtuacao(resultSet.getBytes("foto_area_atuacao"));

				listaFavoritos.add(favoritos);
			}
			resultSet.close();
			stm.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listaFavoritos;
	}

	public boolean cadastrarFavorito(int idCliente, int idEmpresa) {

		Conexao conexao = new Conexao();
		PreparedStatement stm = null;
		boolean resultado = false;
		String sql = "INSERT INTO cliente_empresa (id_cliente, id_empresa) VALUES (?, ?)";

		try {
			stm = conexao.getConexao().prepareStatement(sql);
			stm.setInt(1, idCliente);
			stm.setInt(2, idEmpresa);
			stm.execute();
			stm.close();
			resultado = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultado;
	}
	
	
	public boolean excluirFavorito(int idCliente, int idEmpresa) {

		Conexao conexao = new Conexao();
		PreparedStatement stm = null;
		boolean resultado = false;
		String sql = "DELETE FROM cliente_empresa WHERE id_cliente = ? AND id_empresa = ?";

		try {
			stm = conexao.getConexao().prepareStatement(sql);
			stm.setInt(1, idCliente);
			stm.setInt(2, idEmpresa);
			stm.execute();
			stm.close();
			resultado = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultado;
	}

}
