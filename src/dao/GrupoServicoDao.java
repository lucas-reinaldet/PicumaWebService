package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.GrupoServico;
import util.Conexao;

public class GrupoServicoDao {

	public GrupoServico buscaGrupo(int idGrupoServico) {

		GrupoServico grupoServico = new GrupoServico();

		Conexao conexao = new Conexao();
		PreparedStatement stm = null;
		ResultSet resultado = null;

		String sql = "SELECT  grupo_servico, foto_grupo_servico, idAreaAtuacao FROM grupo_servico WHERE id_grupo_servico = ?";

		try {

			stm = conexao.getConexao().prepareStatement(sql);
			stm.setInt(1, idGrupoServico);
			resultado = stm.executeQuery();

			while (resultado.next()) {
				
				grupoServico.setIdGrupoServico(idGrupoServico);
				grupoServico.setFotoGrupoServico(resultado.getBytes("foto_grupo_servico"));
				grupoServico.setGrupoServico(resultado.getString("grupo_servico"));
				grupoServico.setIdAreaAtuacao(resultado.getInt("idAreaAtuacao"));
			}

			stm.close();
			resultado.close();
		} catch (Exception e) {

			System.out.println("Erro ao buscar informacoes do grupo de servico");
			e.printStackTrace();

		} finally {
			conexao.desconectar();
		}
		return grupoServico;
	}
	
	public List<GrupoServico> listarGrupoServicos() {

		List<GrupoServico> listaGrupoServico = new ArrayList<>();

		Conexao conexao = new Conexao();
		PreparedStatement stm = null;
		ResultSet resultado = null;

		String sql = "SELECT  id_grupo_servico, grupo_servico, foto_grupo_servico, id_area_atuacao FROM grupo_servico";

		try {

			stm = conexao.getConexao().prepareStatement(sql);
			resultado = stm.executeQuery();

			while (resultado.next()) {
				GrupoServico grupoServico = new GrupoServico();
				grupoServico.setIdGrupoServico(resultado.getInt("id_grupo_servico"));
				grupoServico.setFotoGrupoServico(resultado.getBytes("foto_grupo_servico"));
				grupoServico.setGrupoServico(resultado.getString("grupo_servico"));
				grupoServico.setIdAreaAtuacao(resultado.getInt("id_area_atuacao"));
				
				listaGrupoServico.add(grupoServico);
			}

			stm.close();
			resultado.close();
		} catch (Exception e) {

			System.out.println("Erro ao buscar informacoes do grupo de servico");
			e.printStackTrace();

		} finally {
			conexao.desconectar();
		}
		return listaGrupoServico;
	}

	public boolean cadastrarGrupoServico(GrupoServico grupoServico) {
		
		boolean resultado = false;
		Conexao conexao = new Conexao();
		PreparedStatement stm = null;
		
		String sql = "INSERT INTO grupo_servico (grupo_servico, foto_grupo_servico, id_area_atuacao) VALUES (?, ?, ?)";
		
		try {
			stm = conexao.getConexao().prepareStatement(sql);
			stm.setString(1, grupoServico.getGrupoServico());
			stm.setBytes(2, grupoServico.getFotoGrupoServico());
			stm.setInt(3, grupoServico.getIdAreaAtuacao());
			
			stm.execute();
			resultado = true;
			
			stm.close();
		} catch (Exception e) {
			System.out.println("Erro ao tentar inserir as informacoes do grupo de servico; cadastrarGrupoServico.dao!! ");
			e.printStackTrace();
		} finally {
			conexao.desconectar();
		}
		return resultado;
	}

	public List<GrupoServico> listarGrupoServicosPorAreaAtuacao(int idAreaAtuacao) {
		List<GrupoServico> listaGrupoServico = new ArrayList<>();

		Conexao conexao = new Conexao();
		PreparedStatement stm = null;
		ResultSet resultado = null;

		String sql = "SELECT  id_grupo_servico, grupo_servico FROM grupo_servico WHERE id_area_atuacao = ?";

		try {

			stm = conexao.getConexao().prepareStatement(sql);
			stm.setInt(1, idAreaAtuacao);
			resultado = stm.executeQuery();

			while (resultado.next()) {
				GrupoServico grupoServico = new GrupoServico();
				grupoServico.setIdGrupoServico(resultado.getInt("id_grupo_servico"));
				grupoServico.setGrupoServico(resultado.getString("grupo_servico"));
				
				listaGrupoServico.add(grupoServico);
			}

			stm.close();
			resultado.close();
		} catch (Exception e) {

			System.out.println("Erro ao buscar informacoes do grupo de servico");
			e.printStackTrace();

		} finally {
			conexao.desconectar();
		}
		return listaGrupoServico;
	}

}
