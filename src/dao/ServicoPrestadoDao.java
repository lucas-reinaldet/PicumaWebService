package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Funcionario;
import model.GrupoServico;
import model.Servico;
import model.ServicoPrestado;
import util.Conexao;

public class ServicoPrestadoDao {

	public int cadastrarServicoPrestado(ServicoPrestado dado) {
		Conexao conexao = new Conexao();
		PreparedStatement stm = null;
		ResultSet result = null;
		int id = 0;

		String sql = "INSERT INTO servico_prestado (valor_servico, tempo_aproximado_servico, informacoes_servico, id_servico, id_empresa)"
				+ " VALUES (?, ?, ?, ?, ?)";
		;
		try {
			stm = conexao.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stm.setString(1, dado.getValorServico());
			stm.setInt(2, dado.getTempoAproxServico());
			stm.setString(3, dado.getInformacoesServico());
			stm.setInt(4, dado.getServico().getIdServico());
			stm.setInt(5, dado.getIdEmpresa());
			stm.execute();
			result = stm.getGeneratedKeys();

			if (result.next()) {
				id = result.getInt(1);
			}
			stm.close();
			result.close();

		} catch (Exception e) {
			System.out.println(
					"Erro ao inserir informações do Servico Prestado no banco de dador; Função cadastro de Servico Prestado!!");
			e.printStackTrace();
		} finally {
			conexao.desconectar();
		}

		return id;
	}

	public List<ServicoPrestado> buscaServicosPrestadoEmpresa(int idEmpresa) {

		List<ServicoPrestado> listaServicoPrestado = new ArrayList<>();
		ServicoPrestado servicoPrestado = new ServicoPrestado();
		Servico servico = new Servico();

		Conexao conexao = new Conexao();
		PreparedStatement stm = null;
		ResultSet resultado = null;

		String sql = "SELECT id_servico_prestado, valor_servico,  tempo_aproximado_servico, informacoes_servico,"
				+ " id_servico FROM  servico_prestado WHERE id_empresa = ?";

		try {
			stm = conexao.getConexao().prepareStatement(sql);
			stm.setInt(1, idEmpresa);
			resultado = stm.executeQuery();

			while (resultado.next()) {

				servicoPrestado.setIdServicoPrestado(resultado.getInt("id_servico_prestado"));
				servicoPrestado.setValorServico(resultado.getString("valor_servico"));
				servicoPrestado.setTempoAproxServico(resultado.getInt("tempo_aproximado_servico"));
				servicoPrestado.setInformacoesServico(resultado.getString("informacoes_servico"));
				servico.setIdServico(resultado.getInt("id_servico"));
				servicoPrestado.setServico(servico);
				listaServicoPrestado.add(servicoPrestado);
			}

			stm.close();
			resultado.close();

		} catch (Exception e) {

			System.out.println("Erro ao buscar informacoes dos Servicos prestados.!");
			e.printStackTrace();

		} finally {
			conexao.desconectar();
		}

		return listaServicoPrestado;
	}

	public boolean alterarServicoPrestado(ServicoPrestado dado) {

		Conexao conexao = new Conexao();
		boolean resultado = false;
		PreparedStatement stm = null;

		String sql = "UPDATE servico_prestado SET valor_servico = ?, tempo_aproximado_servico = ?, informacoes_servico = ? WHERE id_empresa = ? AND id_servico = ? ";

		try {
			stm = conexao.getConexao().prepareStatement(sql);
			stm.setDouble(1, Double.valueOf(dado.getValorServico()));
			stm.setInt(2, dado.getTempoAproxServico());
			stm.setString(3, dado.getInformacoesServico());
			stm.setInt(4, dado.getIdEmpresa());
			stm.setInt(5, dado.getServico().getIdServico());
			resultado = stm.execute();
			stm.close();

		} catch (Exception e) {
			System.out.println("Erro ao alterar informações do Servico Prestado!!");
			e.printStackTrace();
		} finally {
			conexao.desconectar();
		}

		return resultado;

	}

	public boolean deletarServicoPrestado(int idServicoPrestado) {

		Conexao conexao = new Conexao();
		boolean resultado = false;
		PreparedStatement stm = null;

		String sql = " DELETE FROM servico_prestado WHERE id_servico_prestado = ?";

		try {
			stm = conexao.getConexao().prepareStatement(sql);
			stm.setInt(1, idServicoPrestado);
			resultado = stm.execute();
			stm.close();

		} catch (Exception e) {
			System.out.println("Erro ao alterar informações do Servico Prestado!!");
			e.printStackTrace();
		} finally {
			conexao.desconectar();
		}

		return resultado;

	}

	public List<Funcionario> buscarFuncionarioServicoPrestado(int idServicoPrestado) {

		Conexao conexao = new Conexao();
		PreparedStatement stm = null;
		ResultSet resultado = null;

		List<Funcionario> listaFuncionarios = new ArrayList<>();
		Funcionario funcionario = new Funcionario();

		String sql = "select id_funcionario from servico_prestado_funcionario WHERE id_servico_prestado = ?";

		try {
			stm = conexao.getConexao().prepareStatement(sql);
			stm.setInt(1, idServicoPrestado);
			resultado = stm.executeQuery();

			while (resultado.next()) {

				funcionario.setIdFuncionario(resultado.getInt("id_funcionario"));
				listaFuncionarios.add(funcionario);

			}
			stm.close();
			resultado.close();
		} catch (Exception e) {
			System.out.println("Erro ao buscar funcionarios do servico prestado!!");
			e.printStackTrace();
		} finally {
			conexao.desconectar();
		}

		return listaFuncionarios;
	}

	public boolean cadastrarServicoPrestadoFuncionario(int idServicoPrestado, int idFuncionario) {

		Conexao conexao = new Conexao();
		PreparedStatement stm = null;
		boolean resultado = false;

		String sql = "INSERT INTO servico_prestado_funcionario (id_servico_prestado, id_funcionario)"
				+ " VALUES (?, ?)";

		try {
			stm = conexao.getConexao().prepareStatement(sql);
			stm.setInt(1, idServicoPrestado);
			stm.setInt(2, idFuncionario);
			stm.execute();
			stm.close();
			resultado = true;
		} catch (Exception e) {
			System.out.println("Erro ao inserir idFuncionario e idServicoPrestado na tabela se associacao!!");
			e.printStackTrace();
		} finally {
			conexao.desconectar();
		}
		return resultado;
	}

	public List<ServicoPrestado> listaServicoPrestado(int idEmpresa) {

		Conexao conexao = new Conexao();
		PreparedStatement stm = null;
		ResultSet resultSet = null;
		List<ServicoPrestado> listaServicoPrestado = new ArrayList<>();

		String sql = "SELECT sp.id_servico_prestado AS id, sp.valor_servico AS valor, sp.tempo_aproximado_servico AS tempo, "
				+ "sp.informacoes_servico AS info, s.servico AS servico, s.id_servico AS id_servico, "
				+ "s.id_grupo_servico AS id_grupo_servico FROM servico_prestado As sp "
				+ "INNER JOIN servico AS s ON (sp.id_servico = s.id_servico)" + "WHERE sp.id_empresa = ?";

		try {
			stm = conexao.getConexao().prepareStatement(sql);
			stm.setInt(1, idEmpresa);
			resultSet = stm.executeQuery();
			while (resultSet.next()) {
				ServicoPrestado servicoPrestado = new ServicoPrestado();
				servicoPrestado.setIdServicoPrestado(resultSet.getInt("id"));
				servicoPrestado.setValorServico(resultSet.getString("valor"));
				servicoPrestado.setTempoAproxServico(resultSet.getInt("tempo"));
				servicoPrestado.setInformacoesServico(resultSet.getString("info"));

				Servico servico = new Servico();
				servico.setIdServico(resultSet.getInt("id_servico"));
				servico.setServico(resultSet.getString("servico"));
				
				GrupoServico grupoServico = new GrupoServico();
				grupoServico.setIdGrupoServico(resultSet.getInt("id_grupo_servico"));
				servico.setGrupoServico(grupoServico);
				
				servicoPrestado.setServico(servico);
				listaServicoPrestado.add(servicoPrestado);
			}
			resultSet.close();
			stm.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.desconectar();
		}
		return listaServicoPrestado;
	}
	
	public List<ServicoPrestado> listaServicoPrestadoParaCliente(int idEmpresa) {

		Conexao conexao = new Conexao();
		PreparedStatement stm = null;
		ResultSet resultSet = null;
		List<ServicoPrestado> listaServicoPrestado = new ArrayList<>();

		String sql = "SELECT DISTINCT gs.id_grupo_servico AS id_grupo_servico " + 
				"FROM grupo_servico AS gs " + 
				"INNER JOIN servico AS s ON (gs.id_grupo_servico = s.id_grupo_servico) " + 
				"INNER JOIN servico_prestado AS sp ON (s.id_servico = sp.id_servico) WHERE sp.id_empresa = ?";

		try {
			stm = conexao.getConexao().prepareStatement(sql);
			stm.setInt(1, idEmpresa);
			resultSet = stm.executeQuery();
			while (resultSet.next()) {
				ServicoPrestado servicoPrestado = new ServicoPrestado();
				Servico servico = new Servico();
				GrupoServico grupoServico = new GrupoServico();
				grupoServico.setIdGrupoServico(resultSet.getInt("id_grupo_servico"));
				
				servico.setGrupoServico(grupoServico);
				servicoPrestado.setServico(servico);
				listaServicoPrestado.add(servicoPrestado);
			}
			resultSet.close();
			stm.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.desconectar();
		}
		return listaServicoPrestado;
	}

	public List<Integer> listaIdFuncionarioServicoPrestado(int idServicoPrestado) {

		Conexao conexao = new Conexao();
		PreparedStatement stm = null;
		ResultSet resultSet = null;
		List<Integer> listaIdFuncionario = new ArrayList<>();
		String sql = "SELECT id_funcionario FROM servico_prestado_funcionario WHERE id_servico_prestado = ?";

		try {
			stm = conexao.getConexao().prepareStatement(sql);
			stm.setInt(1, idServicoPrestado);
			resultSet = stm.executeQuery();

			while (resultSet.next()) {
				listaIdFuncionario.add(resultSet.getInt("id_funcionario"));
			}
			resultSet.close();
			stm.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.desconectar();
		}
		return listaIdFuncionario;
	}

	public List<GrupoServico> listaGrupoServicoPrestado(int idEmpresa) {

		Conexao conexao = new Conexao();
		PreparedStatement stm = null;
		ResultSet resultSet = null;
		List<GrupoServico> listaGrupoServico = new ArrayList<>();
		String sql = "SELECT DISTINCT gs.id_grupo_servico AS id, gs.grupo_servico AS grupo_servico, gs.foto_grupo_servico AS foto "
				+ "FROM grupo_servico AS gs " + "INNER JOIN servico AS s ON (gs.id_grupo_servico = s.id_grupo_servico) "
				+ "INNER JOIN servico_prestado sp ON (s.id_servico = sp.id_servico) " + "WHERE sp.id_empresa = ?";

		try {
			stm = conexao.getConexao().prepareStatement(sql);
			stm.setInt(1, idEmpresa);
			resultSet = stm.executeQuery();

			while (resultSet.next()) {
				GrupoServico grupoServico = new GrupoServico();
				grupoServico.setIdGrupoServico(resultSet.getInt("id"));
				grupoServico.setGrupoServico(resultSet.getString("grupo_servico"));
				grupoServico.setFotoGrupoServico(resultSet.getBytes("foto"));
				listaGrupoServico.add(grupoServico);
			}
			resultSet.close();
			stm.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.desconectar();
		}
		return listaGrupoServico;
	}
}
