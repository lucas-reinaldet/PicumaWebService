package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.FotoGaleria;
import model.Galeria;
import util.Conexao;

public class FotoGaleriaDao {

	public int cadastrarFoto(FotoGaleria fotoGaleria, Galeria galeria) {

		Conexao conexao = new Conexao();
		int resultado = 0;
		PreparedStatement stm = null;
		ResultSet resultSet = null;
		String sql = "SELECT * FROM cadastro_foto_galeria(?, ?, ?, ?)";

		try {
			stm = conexao.getConexao().prepareStatement(sql);
			stm.setInt(1, galeria.getIdEmpresa());
			stm.setInt(2, galeria.getGrupoServico().getIdGrupoServico());
			stm.setBytes(3, fotoGaleria.getFotoAntesGaleria());
			stm.setBytes(4, fotoGaleria.getFotoDepoisGaleria());
			resultSet = stm.executeQuery();
			if (resultSet.next()) {
				resultado = resultSet.getInt(1);
			}
			resultSet.close();
			stm.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.desconectar();
		}

		return resultado;

	}

	public List<FotoGaleria> listarFotosdaGaleria(int id) {

		Conexao conexao = new Conexao();
		PreparedStatement stm = null;
		ResultSet resultado = null;

		String sql = "SELECT id_foto_galeria, foto_antes_galeria, foto_depois_galeria FROM foto_galeria WHERE id_galeria = ?";

		List<FotoGaleria> lista = new ArrayList<>();

		try {

			stm = conexao.getConexao().prepareStatement(sql);
			stm.setInt(1, id);
			resultado = stm.executeQuery();

			while (resultado.next()) {

				FotoGaleria foto = new FotoGaleria();
				foto.setIdFotoGaleria(resultado.getInt("id_foto_galeria"));
				foto.setFotoAntesGaleria(resultado.getBytes("foto_antes_galeria"));
				foto.setFotoDepoisGaleria(resultado.getBytes("foto_depois_galeria"));
				foto.setIdGaleria(id);
				lista.add(foto);
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

	public boolean deletarFotoGaleria(FotoGaleria fotoGaleria) {
		boolean resultado = false;
		Conexao conexao = new Conexao();
		PreparedStatement stm = null;
		ResultSet resultSet = null;
		String sql = "SELECT * FROM delete_foto_galeria_empresa(?)";

		try {
			stm = conexao.getConexao().prepareStatement(sql);
			stm.setInt(1, fotoGaleria.getIdFotoGaleria());
			resultSet = stm.executeQuery();
			if (resultSet.next()) {
				if (resultSet.getInt(1) == 3009) {
					resultado = true;
				}
			}
			resultSet.close();
			stm.close();
		} catch (Exception e) {
			System.out.println("Erro ao deletar a foto do banco de dados!!!");
			e.printStackTrace();
		}
		return resultado;
	}
}
