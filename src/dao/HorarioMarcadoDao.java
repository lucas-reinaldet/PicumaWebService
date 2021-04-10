package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import model.AreaAtuacao;
import model.Cliente;
import model.Empresa;
import model.Funcionario;
import model.HorarioMarcado;
import model.Servico;
import model.ServicoPrestado;
import util.Conexao;

public class HorarioMarcadoDao {

	public boolean cadastrarHorarioMarcado(HorarioMarcado dado) {
		Conexao conexao = new Conexao();
		boolean resultado = false;
		PreparedStatement stm = null;

		String sql = "";
		if (dado.getFuncionario() != null) {
			sql = "INSERT INTO horario_marcado (horario_inicio, horario_fim, data_marcada, id_cliente, id_empresa, "
					+ " id_funcionario, id_servico_prestado)" + " VALUES (?, ?, ?, ?, ?, ?, ?)";
		} else {
			sql = "INSERT INTO horario_marcado (horario_inicio, horario_fim, data_marcada, id_cliente, id_empresa, "
					+ "id_servico_prestado)" + " VALUES (?, ?, ?, ?, ?, ?)";
		}

		try {
			stm = conexao.getConexao().prepareStatement(sql);
			stm.setTime(1, new Time(dado.getHorarioInicio().getTimeInMillis()));
			stm.setTime(2, new Time(dado.getHorarioFim().getTimeInMillis()));
			stm.setDate(3, new Date(dado.getDataMarcada().getTimeInMillis()));
			stm.setInt(4, dado.getCliente().getIdCliente());
			stm.setInt(5, dado.getEmpresa().getIdEmpresa());
			if (dado.getFuncionario() != null) {
				stm.setInt(6, dado.getFuncionario().getIdFuncionario());
				stm.setInt(7, dado.getServicoPrestado().getIdServicoPrestado());
			} else {
				stm.setInt(6, dado.getServicoPrestado().getIdServicoPrestado());
			}

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

	public HorarioMarcado buscarHorarioMarcadoPorId(int idHorarioMarcado) {

		HorarioMarcado horarioMarcado = new HorarioMarcado();

		Conexao conexao = new Conexao();
		PreparedStatement stm = null;
		ResultSet resultado = null;

		String sql = "select horario_inicio	,horario_fim, data_marcada, status, desativado, id_cliente, "
				+ "id_empresa ,id_funcionario, id_servico FROM horario_marcado WHERE id_horario_marcado = ?";

		try {
			stm = conexao.getConexao().prepareStatement(sql);
			stm.setInt(1, idHorarioMarcado);

			resultado = stm.executeQuery();
			String aux = "";
			java.util.Date data = new java.util.Date();

			while (resultado.next()) {

				horarioMarcado.setIdhorarioMarcado(idHorarioMarcado);

				Calendar calendar = Calendar.getInstance();
				aux = resultado.getString("horario_inicio");
				String[] hora1 = aux.split(":");
				calendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf((hora1[0])));
				calendar.set(Calendar.MINUTE, Integer.valueOf((hora1[1])));
				horarioMarcado.setHorarioInicio(calendar);

				calendar = Calendar.getInstance();
				aux = resultado.getString("horario_fim");
				String[] hora2 = aux.split(":");
				calendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf((hora2[0])));
				calendar.set(Calendar.MINUTE, Integer.valueOf((hora2[1])));
				horarioMarcado.setHorarioFim(calendar);

				data = Date.valueOf(resultado.getDate("data_marcada").toString());
				calendar = Calendar.getInstance();
				calendar.setTime(data);
				horarioMarcado.setDataMarcada(calendar);

				horarioMarcado.setStatus(resultado.getString("status"));

				horarioMarcado.setDesativado(resultado.getBoolean("desativado"));

				Cliente cliente = new Cliente();
				cliente.setIdCliente(resultado.getInt("id_cliente"));

				horarioMarcado.setCliente(cliente);

				ServicoPrestado servicoPrestado = new ServicoPrestado();
				Servico servico = new Servico();

				servico.setIdServico(resultado.getInt("id_servico"));
				servicoPrestado.setServico(servico);

				horarioMarcado.setServicoPrestado(servicoPrestado);

				Funcionario funcionario = new Funcionario();
				funcionario.setIdFuncionario(resultado.getInt("id_funcionario"));

				horarioMarcado.setFuncionario(funcionario);

				Empresa empresa = new Empresa();
				empresa.setIdEmpresa(resultado.getInt("id_empresa"));

				horarioMarcado.setEmpresa(empresa);

			}

			stm.close();
			resultado.close();

		} catch (Exception e) {
			System.out.println("Erro ao buscar o horario marcado pelo id; funcao buscarHorarioMarcadoPorId.dao !! ");
			e.printStackTrace();
		} finally {
			conexao.desconectar();
		}

		return horarioMarcado;
	}

	public List<HorarioMarcado> listarHorarioMarcadoParaEmpresa(int idEmpresa) {

		List<HorarioMarcado> listaHorarioMarcado = new ArrayList<>();

		Conexao conexao = new Conexao();
		PreparedStatement stm = null;
		ResultSet resultado = null;

		String sql = "SELECT hm.id_horario_marcado AS id, hm.horario_inicio AS inicio, hm.horario_fim AS fim, hm.data_marcada AS data, "
				+ "hm.status AS status, c.id_cliente AS id_cliente, c.nome_cliente AS nome_cliente, c.foto_cliente AS foto_cliente, "
				+ "s.id_servico AS id_servico, s.servico AS servico, "
				+ "sp.id_servico_prestado AS id_servico_prestado " + "FROM horario_marcado AS hm "
				+ "INNER JOIN cliente AS c ON (hm.id_cliente = c.id_cliente) "
				+ "INNER JOIN empresa AS e ON (hm.id_empresa = e.id_empresa) "
				+ "INNER JOIN servico_prestado AS sp ON (hm.id_servico_prestado = sp.id_servico_prestado) "
				+ "INNER JOIN servico AS s ON (sp.id_servico = s.id_servico) "
				+ "WHERE hm.desativado = false AND e.empresa_ativada = true AND c.cliente_ativo = true "
				+ "AND hm.id_empresa = ? " + "ORDER BY hm.data_marcada, hm.horario_inicio  ASC";

		try {
			stm = conexao.getConexao().prepareStatement(sql);
			stm.setInt(1, idEmpresa);
			resultado = stm.executeQuery();
			while (resultado.next()) {
				HorarioMarcado horarioMarcado = new HorarioMarcado();
				horarioMarcado.setIdhorarioMarcado(resultado.getInt("id"));

				Time date = resultado.getTime("inicio");
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				horarioMarcado.setHorarioInicio(calendar);

				date = resultado.getTime("fim");
				calendar = Calendar.getInstance();
				calendar.setTime(date);
				horarioMarcado.setHorarioFim(calendar);

				Date data = resultado.getDate("data");
				calendar = Calendar.getInstance();
				calendar.setTime(data);
				horarioMarcado.setDataMarcada(calendar);

				horarioMarcado.setStatus(resultado.getString("status"));

				Cliente cliente = new Cliente();
				cliente.setNomeCliente(resultado.getString("nome_cliente"));
				cliente.setIdCliente(resultado.getInt("id_cliente"));
				cliente.setFotoCliente(resultado.getBytes("foto_cliente"));

				ServicoPrestado servicoPrestado = new ServicoPrestado();
				Servico servico = new Servico();
				servico.setIdServico(resultado.getInt("id_servico"));
				servico.setServico(resultado.getString("servico"));
				servicoPrestado.setIdServicoPrestado(resultado.getInt("id_servico_prestado"));
				servicoPrestado.setServico(servico);

				horarioMarcado.setCliente(cliente);
				horarioMarcado.setServicoPrestado(servicoPrestado);
				listaHorarioMarcado.add(horarioMarcado);

			}
			stm.close();
			resultado.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.desconectar();
		}

		return listaHorarioMarcado;
	}

	public List<HorarioMarcado> listarHorarioMarcadoParaCliente(int idCliente) {

		List<HorarioMarcado> listaHorarioMarcado = new ArrayList<>();

		Conexao conexao = new Conexao();
		PreparedStatement stm = null;
		ResultSet resultado = null;

		String sql = "SELECT hm.id_horario_marcado AS id, hm.horario_inicio AS inicio, hm.horario_fim AS fim, hm.data_marcada AS data, "
				+ "hm.status AS status, e.id_empresa AS id_empresa, "
				+ "e.nome_fantasia AS nome_fantasia, e.logo_empresa AS logo_empresa, a.id_area_atuacao AS id_area_atuacao, "
				+ "a.foto_area_atuacao AS foto_area_atuacao, a.area_atuacao AS area_atuacao, "
				+ "s.id_servico AS id_servico, s.servico AS servico, "
				+ "sp.id_servico_prestado AS id_servico_prestado " + "FROM horario_marcado AS hm "
				+ "INNER JOIN cliente AS c ON (hm.id_cliente = c.id_cliente) "
				+ "INNER JOIN empresa AS e ON (hm.id_empresa = e.id_empresa) "
				+ "INNER JOIN area_atuacao AS a ON (e.id_area_Atuacao = a.id_area_atuacao) "
				+ "INNER JOIN servico_prestado AS sp ON (hm.id_servico_prestado = sp.id_servico_prestado) "
				+ "INNER JOIN servico AS s ON (sp.id_servico = s.id_servico) "
				+ "WHERE hm.desativado = false AND e.empresa_ativada = true AND c.cliente_ativo = true "
				+ "AND hm.id_cliente = ? " + "ORDER BY hm.data_marcada, hm.horario_inicio  ASC";

		try {
			stm = conexao.getConexao().prepareStatement(sql);
			stm.setInt(1, idCliente);
			resultado = stm.executeQuery();

			while (resultado.next()) {

				HorarioMarcado horarioMarcado = new HorarioMarcado();
				horarioMarcado.setIdhorarioMarcado(resultado.getInt("id"));

				Time date = resultado.getTime("inicio");
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				horarioMarcado.setHorarioInicio(calendar);

				date = resultado.getTime("fim");
				calendar = Calendar.getInstance();
				calendar.setTime(date);
				horarioMarcado.setHorarioFim(calendar);

				Date data = resultado.getDate("data");
				calendar = Calendar.getInstance();
				calendar.setTime(data);
				horarioMarcado.setDataMarcada(calendar);

				horarioMarcado.setStatus(resultado.getString("status"));

				Empresa empresa = new Empresa();
				empresa.setNomeFantasia(resultado.getString("nome_fantasia"));
				empresa.setLogoEmpresa(resultado.getBytes("logo_empresa"));
				empresa.setIdEmpresa(resultado.getInt("id_empresa"));

				AreaAtuacao areaAtuacao = new AreaAtuacao();
				areaAtuacao.setIdAreaAtuacao(resultado.getInt("id_area_atuacao"));
				areaAtuacao.setFotoAreaAtuacao(resultado.getBytes("foto_area_atuacao"));
				areaAtuacao.setAreaAtuacao(resultado.getString("area_atuacao"));

				ServicoPrestado servicoPrestado = new ServicoPrestado();
				Servico servico = new Servico();
				servico.setIdServico(resultado.getInt("id_servico"));
				servico.setServico(resultado.getString("servico"));
				servicoPrestado.setIdServicoPrestado(resultado.getInt("id_servico_prestado"));
				servicoPrestado.setServico(servico);

				empresa.setAreaAtuacao(areaAtuacao);
				horarioMarcado.setEmpresa(empresa);
				horarioMarcado.setServicoPrestado(servicoPrestado);
				listaHorarioMarcado.add(horarioMarcado);
			}

			stm.close();
			resultado.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.desconectar();
		}

		return listaHorarioMarcado;
	}

	public boolean desativarHorarioMarcado(int idHorarioMarcado) {

		boolean resultado = false;

		Conexao conexao = new Conexao();
		PreparedStatement stm = null;

		String sql = "UPDATE horario_marcado SET desativado = ? WHERE id_horario_marcado = ?";

		try {
			stm = conexao.getConexao().prepareStatement(sql);
			stm.setBoolean(1, true);
			stm.setInt(2, idHorarioMarcado);
			stm.execute();
			stm.close();
			resultado = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultado;
	}

	public boolean alterarStatusHorarioMarcado(Integer idHorario, String status) {
		boolean resultado = false;
		Conexao conexao = new Conexao();
		PreparedStatement stm = null;
		String sql = "UPDATE horario_marcado SET status = ? WHERE id_horario_marcado = ?";

		try {
			stm = conexao.getConexao().prepareStatement(sql);
			stm.setString(1, status);
			stm.setInt(2, idHorario);
			stm.execute();
			stm.close();
			resultado = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

}
