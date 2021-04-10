package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;
import model.Comentario;
import util.Conexao;

public class ComentarioDao {

	public boolean cadastrarComentario(Comentario dados) {

		Conexao conexao = new Conexao();
		boolean resultado = false;
		PreparedStatement stm = null;

		String sql = "INSERT INTO comentario (comentario, id_cliente, id_empresa) VALUES (?, ?, ?)";

		try {
			stm = conexao.getConexao().prepareStatement(sql);
			stm.setString(1, dados.getComentario());
			stm.setInt(2, dados.getCliente().getIdCliente());
			stm.setInt(3, dados.getIdEmpresa());
			stm.execute();
			stm.close();
			resultado = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.desconectar();
		}
		return resultado;
	}

	public boolean desativaComentario(int idComentario) {

		Conexao conexao = new Conexao();
		boolean resultado = false;
		PreparedStatement stm = null;

		String sql = "INSERT INTO comentario (comentario_ativado) VALUES (?) WHERE id_comentario = ?";

		try {
			stm = conexao.getConexao().prepareStatement(sql);
			stm.setBoolean(1, false);
			stm.setInt(2, idComentario);
			resultado = stm.execute();
			stm.close();

		} catch (Exception e) {
			System.out.println("Erro ao tentar desativar um comentario !!!");
			e.printStackTrace();
		} finally {
			conexao.desconectar();
		}
		return resultado;
	}

	public List<Comentario> listarComentariosPorEmpresa(int id) {
		List<Comentario> lista = new ArrayList<>();
		Conexao conexao = new Conexao();
		PreparedStatement stm = null;
		ResultSet resultSet = null;

		String sql = "SELECT c.id_comentario AS id, c.comentario AS comentario, cl.id_cliente AS id_cliente, " + 
				"cl.nome_cliente AS cliente, cl.foto_cliente AS foto " + 
				"FROM comentario AS c " + 
				"INNER JOIN cliente AS cl ON (c.id_cliente = cl.id_cliente) " + 
				"WHERE c.id_empresa = ? AND c.comentario_ativado = true ORDER BY c.data_comentario DESC";

		try {
			stm = conexao.getConexao().prepareStatement(sql);
			stm.setInt(1, id);
			resultSet = stm.executeQuery();
			while (resultSet.next()) {
				Comentario comentario = new Comentario();
				comentario.setIdComentario(resultSet.getInt("id"));
				comentario.setComentario(resultSet.getString("comentario"));

				Cliente cliente = new Cliente();
				cliente.setIdCliente(resultSet.getInt("id_cliente"));
				cliente.setNomeCliente(resultSet.getString("cliente"));
				cliente.setFotoCliente(resultSet.getBytes("foto"));
				
				comentario.setCliente(cliente);
				lista.add(comentario);
			}
			stm.close();
			resultSet.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.desconectar();
		}
		return lista;
	}

	public boolean alterarComentario(Comentario dados) {

		Conexao conexao = new Conexao();
		boolean resultado = false;
		PreparedStatement stm = null;

		String sql = "UPDATE comentario SET data_comentario = ?, comentario = ? WHERE id_comentario = ?";

		try {
			stm = conexao.getConexao().prepareStatement(sql);
			stm.setDate(1, new Date(dados.getDataComentario().getTimeInMillis()));
			stm.setString(2, dados.getComentario());
			stm.setInt(3, dados.getIdComentario());
			resultado = stm.execute();
			stm.close();

		} catch (Exception e) {
			System.out.println("Erro ao tentar alterar um comentario !!!");
			e.printStackTrace();
		} finally {
			conexao.desconectar();
		}
		return resultado;
	}
}
