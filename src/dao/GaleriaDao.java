package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Empresa;
import model.Galeria;
import model.GrupoServico;
import util.Conexao;

public class GaleriaDao {

	public List<Galeria> listarGaleria(int idEmpresa) {

		List<Galeria> listaGaleria = new ArrayList<Galeria>();
		Conexao conexao = new Conexao();
		PreparedStatement stm = null;
		ResultSet result = null;

		String sql = "SELECT g.id_galeria AS id, g.id_grupo_servico AS id_grupo_servico, gs.grupo_servico AS grupo_servico, "
				+ "gs.foto_grupo_servico AS foto FROM galeria AS g "
				+ "INNER JOIN grupo_servico AS gs ON (g.id_grupo_servico = gs.id_grupo_servico) "
				+ "WHERE id_empresa = ? ORDER BY gs.grupo_servico ASC";

		try {
			stm = conexao.getConexao().prepareStatement(sql);
			stm.setInt(1, idEmpresa);
			result = stm.executeQuery();
			
			while(result.next()) {
				Galeria galeria = new Galeria();
				galeria.setIdGaleria(result.getInt("id"));
				
				GrupoServico grupoServico = new GrupoServico();				
				grupoServico.setIdGrupoServico(result.getInt("id_grupo_servico"));
				grupoServico.setGrupoServico(result.getString("grupo_servico"));
				grupoServico.setFotoGrupoServico(result.getBytes("foto"));
				
				galeria.setGrupoServico(grupoServico);
				listaGaleria.add(galeria);
			}
			stm.close();
			result.close();

		} catch (Exception e) {
			System.out.println("Nao foi possivel retornar lista de galeria do banco de dados!");
		} finally {
			conexao.desconectar();
		}
		return listaGaleria;
	}

}
