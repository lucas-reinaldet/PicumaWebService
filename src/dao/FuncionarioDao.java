package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Funcionario;
import util.Conexao;

public class FuncionarioDao {

	public int cadastrarFuncionario(Funcionario dados) {

		Conexao conexao = new Conexao();
		PreparedStatement stm = null;
		int resultado = 0;

		String sql = "INSERT INTO funcionario (nome_funcionario, foto_funcionario, id_empresa) VALUES (?, ?, ?)";

		try {

			stm = conexao.getConexao().prepareStatement(sql);
			stm.setString(1, dados.getNomeFuncionario());
			stm.setBytes(2, dados.getFotoFuncionario());
			stm.setInt(3, dados.getIdEmpresa());
			stm.execute();
			stm.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.desconectar();
		}
		return resultado;
	}

	public Funcionario buscaFuncionario(int idFuncionario) {

		Conexao conexao = new Conexao();
		PreparedStatement stm = null;
		ResultSet resultado = null;
		Funcionario funcionario = new Funcionario();

		String sql = "SELECT  nome_funcionario, foto_funcionario  FROM funcionario WHERE id_funcionario = ?";

		try {
			stm = conexao.getConexao().prepareStatement(sql);
			stm.setInt(1, idFuncionario);
			resultado = stm.executeQuery();

			while (resultado.next()) {
				funcionario.setIdFuncionario(idFuncionario);
				funcionario.setNomeFuncionario(resultado.getString("nome_funcionario"));
				funcionario.setFotoFuncionario(resultado.getBytes("foto_funcionario"));
			}
			stm.close();
			resultado.close();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			conexao.desconectar();
		}

		return funcionario;
	}

	public List<Funcionario> listarFuncionariosPorEmpresa(int id) {

		Conexao conexao = new Conexao();
		PreparedStatement stm = null;
		ResultSet resultado = null;

		String sql = "SELECT id_funcionario, nome_funcionario, foto_funcionario FROM funcionario "
				+ "WHERE id_empresa = ? AND funcionario_ativo = true";
		List<Funcionario> lista = new ArrayList<>();

		try {

			stm = conexao.getConexao().prepareStatement(sql);
			stm.setInt(1, id);
			resultado = stm.executeQuery();

			while (resultado.next()) {

				Funcionario funcionario = new Funcionario();
				funcionario.setIdFuncionario(resultado.getInt("id_funcionario"));
				funcionario.setNomeFuncionario(resultado.getString("nome_funcionario"));
				funcionario.setFotoFuncionario(resultado.getBytes("foto_funcionario"));
				funcionario.setIdEmpresa(id);
				lista.add(funcionario);
			}
			stm.close();
			resultado.close();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			conexao.desconectar();
		}
		return lista;
	}

	public boolean alterarFuncionario(Funcionario funcionario) {
		boolean resultado = false;

		Conexao conexao = new Conexao();
		PreparedStatement stm = null;

		String sql = "UPDATE funcionario SET nome_funcionario = ?, foto_funcionario = ? WHERE id_funcionario = ?";

		try {
			stm = conexao.getConexao().prepareStatement(sql);
			stm.setString(1, funcionario.getNomeFuncionario());
			stm.setBytes(2, funcionario.getFotoFuncionario());
			stm.setInt(3, funcionario.getIdFuncionario());
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

	public boolean deletarFuncionario(Integer idFuncionario) {
		boolean resultado = false;

		Conexao conexao = new Conexao();
		PreparedStatement stm = null;
		ResultSet resultSet = null;

		String sql = "SELECT * FROM desativar_funcionario_empresa(?)";
		try {
			stm = conexao.getConexao().prepareStatement(sql);
			stm.setInt(1, idFuncionario);
			resultSet = stm.executeQuery();
			if(resultSet.next()) {
				if (resultSet.getInt(1) == 3010) {
					resultado = true;
				}
			}
			stm.execute();
			stm.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.desconectar();
		}
		return resultado;
	}

	public boolean desassociarFuncionarioServicoPrestado(Integer idServicoPrestado, Integer idFuncionario) {
		
		boolean resultado = false;
		Conexao conexao = new Conexao();
		PreparedStatement stm = null;

		String sql = "DELETE FROM servico_prestado_funcionario WHERE id_servico_prestado = ? AND id_funcionario = ?";
		try {
			stm = conexao.getConexao().prepareStatement(sql);
			stm.setInt(1, idServicoPrestado);
			stm.setInt(2, idFuncionario);
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
}
