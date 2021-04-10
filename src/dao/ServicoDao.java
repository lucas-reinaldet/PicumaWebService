package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.GrupoServico;
import model.Servico;
import util.Conexao;

public class ServicoDao {

	public List<Servico> buscaServicoPorAreaAtuacao(int idAreaAtuacao) {

		Conexao conexao = new Conexao();
		PreparedStatement stm = null;
		ResultSet resultSet = null;
		List<Servico> listaServico = new ArrayList<Servico>();
		String sql = "SELECT s.id_servico AS id, s.servico AS servico, s.id_grupo_servico AS id_grupo_servico FROM servico AS s INNER JOIN grupo_servico AS gs ON (s.id_grupo_servico = gs.id_grupo_servico) WHERE gs.id_area_atuacao = ?";
		
		try {
			stm = conexao.getConexao().prepareStatement(sql);
			stm.setInt(1, idAreaAtuacao);
			resultSet = stm.executeQuery();
			
			while (resultSet.next()) {
				Servico servico = new Servico();
				servico.setIdServico(resultSet.getInt("id"));
				servico.setServico(resultSet.getString("servico"));
				GrupoServico grupoServico = new GrupoServico();
				grupoServico.setIdGrupoServico(resultSet.getInt("id_grupo_servico"));
				servico.setGrupoServico(grupoServico);
				listaServico.add(servico);
			}
			
			resultSet.close();
			stm.close();			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.desconectar();
		}
		
		return listaServico;
	}

}
