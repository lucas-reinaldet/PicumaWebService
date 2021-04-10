package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.AreaAtuacao;
import util.Conexao;

public class AreaAtuacaoDao {

	public boolean cadastrarAreaAtuacao(AreaAtuacao dados) {
		
		boolean resultado = false;
		Conexao conexao = new Conexao();
		PreparedStatement stm = null;
		
		String sql = "INSERT INTO area_atuacao (area_atuacao, foto_area_atuacao) VALUES (?, ?)";
		
		try {
			stm = conexao.getConexao().prepareStatement(sql);
			stm.setString(1, dados.getAreaAtuacao());
			stm.setBytes(2, dados.getFotoAreaAtuacao());
			
			stm.execute();
			resultado = true;
			
			stm.close();
		} catch (Exception e) {
			System.out.println("Erro ao tentar inserir as informacoes da area de atuacao; cadastrarAreaAtuacao.dao!! ");
			e.printStackTrace();
		} finally {
			conexao.desconectar();
		}
		return resultado;
	}
	
	public AreaAtuacao buscaAreaAtuacaoPorId(int idAreaAtuacao) {

		AreaAtuacao areaAtuacao = new AreaAtuacao();

		Conexao conexao = new Conexao();
		PreparedStatement stm = null;
		ResultSet resultado = null;

		String sql = "SELECT area_atuacao, foto_area_atuacao FROM area_atuacao WHERE id_area_atuacao = ?";

		try {
			stm = conexao.getConexao().prepareStatement(sql);
			stm.setInt(1, idAreaAtuacao);
			resultado = stm.executeQuery();

			while (resultado.next()) {

				areaAtuacao.setIdAreaAtuacao(idAreaAtuacao);
				areaAtuacao.setAreaAtuacao(resultado.getString("area_atuacao"));
				areaAtuacao.setFotoAreaAtuacao(resultado.getBytes("foto_area_atuacao"));
			}
			stm.close();
			resultado.close();
		} catch (Exception e) {
			System.out.println("Erro ao buscar dados da area atuacao no banco de dados!");
			e.printStackTrace();
		} finally {
			conexao.desconectar();
		}

		return areaAtuacao;
	}

	public List<AreaAtuacao> listarAreaAtuacao() {

		AreaAtuacao areaAtuacao = new AreaAtuacao();
		List<AreaAtuacao> lista = new ArrayList<AreaAtuacao>();

		Conexao conexao = new Conexao();
		PreparedStatement stm = null;
		ResultSet resultado = null;

		String sql = "SELECT id_area_atuacao, area_atuacao, foto_area_atuacao FROM area_atuacao";

		try {
			stm = conexao.getConexao().prepareStatement(sql);
			resultado = stm.executeQuery();

			while (resultado.next()) {

				areaAtuacao.setIdAreaAtuacao(resultado.getInt("id_area_atuacao"));
				areaAtuacao.setAreaAtuacao(resultado.getString("area_atuacao"));				
				areaAtuacao.setFotoAreaAtuacao(resultado.getBytes("foto_area_atuacao"));

				lista.add(areaAtuacao);
				
				areaAtuacao = new AreaAtuacao();

			}
			stm.close();
			resultado.close();
		} catch (Exception e) {
			System.out.println("Ero ao buscar dados da area atuacao no banco de dados!");
			e.printStackTrace();
		} finally {
			conexao.desconectar();
		}

		return lista;
	}

}
