package dao;

import java.sql.Array;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import model.DiaSemana;
import model.HorarioEmpresa;
import util.Conexao;

public class HorarioEmpresaDao {

	public List<HorarioEmpresa> buscaHorarioEmpresaPorIdEmpresa(int idEmpresa) {

		Conexao conexao = new Conexao();
		PreparedStatement stm = null;
		ResultSet resultado = null;

		List<HorarioEmpresa> listaContato = new ArrayList<HorarioEmpresa>();

		String sql = "SELECT  h.id_horario_empresa, h.inicio_expediente AS ini_exp, h.inicio_intervalo AS ini_int, h.fim_intervalo AS fim_int, h.fim_expediente AS fim_exp, s.dia_semana AS dia FROM horario_empresa AS h INNER JOIN dia_semana AS s ON (h.id_dia_semana = s.id_dia_semana) WHERE id_empresa = ? ORDER BY s.id_dia_semana ASC";

		try {

			stm = conexao.getConexao().prepareStatement(sql);
			stm.setInt(1, idEmpresa);
			resultado = stm.executeQuery();

			while (resultado.next()) {

				HorarioEmpresa horarioEmpresa = new HorarioEmpresa();
				horarioEmpresa.setIdHorarioEmpresa(resultado.getInt("id_horario_empresa"));

				Time date = resultado.getTime("ini_exp");
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				horarioEmpresa.setInicioExpediente(calendar);

				date = resultado.getTime("ini_int");
				calendar = Calendar.getInstance();
				calendar.setTime(date);
				horarioEmpresa.setInicioIntervalo(calendar);

				date = resultado.getTime("fim_int");
				calendar = Calendar.getInstance();
				calendar.setTime(date);
				horarioEmpresa.setFimIntervalo(calendar);

				date = resultado.getTime("fim_exp");
				calendar = Calendar.getInstance();
				calendar.setTime(date);
				horarioEmpresa.setFimExpediente(calendar);

				DiaSemana diaSemana = new DiaSemana();
				diaSemana.setDiaSemana(resultado.getString("dia"));
				horarioEmpresa.setDiaSemana(diaSemana);
				listaContato.add(horarioEmpresa);
			}
			stm.close();
			resultado.close();

		} catch (Exception e) {

			System.out.println("Erro ao buscar informações do Horario da Empresa");
			e.printStackTrace();

		} finally {
			conexao.desconectar();
		}
		return listaContato;
	}

	public boolean deletarHorarioEmpresa(HorarioEmpresa horarioEmpresa) {

		Conexao conexao = new Conexao();
		PreparedStatement stm = null;
		boolean resultado = false;

		String sql = "DELETE FROM horario_empresa WHERE id_horario_empresa = ?";

		try {
			stm = conexao.getConexao().prepareStatement(sql);
			stm.setInt(1, horarioEmpresa.getIdHorarioEmpresa());

			stm.execute();
			resultado = true;
			stm.close();

		} catch (Exception e) {
			System.out.println("Erro ao tentar deletar Horario Empresa!");
			e.printStackTrace();
		} finally {
			conexao.desconectar();
		}
		return resultado;
	}

	public boolean cadastrarHorarioEmpresa(int idEmpresa, Array horario) {

		Conexao conexao = new Conexao();
		PreparedStatement stm = null;
		boolean resultado = false;
		ResultSet resultSet = null;
		String sql = "SELECT * FROM cadastro_horario_empresa(?, ?)";
		try {
			stm = conexao.getConexao().prepareStatement(sql);
			stm.setArray(1, horario);
			stm.setInt(2, idEmpresa);

			resultSet = stm.executeQuery();
			if (resultSet.next()) {
				if (resultSet.getInt(1) == 3007) {
					resultado = true;
				}
			}
			stm.close();
			resultSet.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.desconectar();
		}
		return resultado;
	}

}
