package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.DiaSemana;
import util.Conexao;

public class DiaSemanaDao {

	public DiaSemana buscaDiaSemana(int idDiaSemana) {

		DiaSemana diaSemana = new DiaSemana();
		Conexao conexao = new Conexao();
		PreparedStatement stm = null;
		ResultSet resultado = null;

		String sql = "SELECT dia_semana FROM dia_semana WHERE id_dia_semana = ?";

		try {
			stm = conexao.getConexao().prepareStatement(sql);
			stm.setInt(1, idDiaSemana);
			resultado = stm.executeQuery();

			while (resultado.next()) {
				diaSemana.setIdDiaSemana(idDiaSemana);
				diaSemana.setDiaSemana(resultado.getString("dia_semana"));
			}
			stm.close();
			resultado.close();

		} catch (Exception e) {
			System.out.println("Erro ao buscar dia da semana no banco de dados!");
			e.printStackTrace();
		} finally {
			conexao.desconectar();
		}
		return diaSemana;
	}
}
