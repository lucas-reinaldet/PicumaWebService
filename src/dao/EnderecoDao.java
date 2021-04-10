package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Endereco;
import util.Conexao;

public class EnderecoDao {

	public Endereco buscaEndereco(int idEndereco) {

		Conexao conexao = new Conexao();
		PreparedStatement stm = null;
		ResultSet resultado = null;
		Endereco endereco = new Endereco();

		String sql = "SELECT cep, logradouro, complemento, numero, bairro, cidade, estado, latitude, longitude FROM endereco WHERE id_endereco = ?";

		try {
			stm = conexao.getConexao().prepareStatement(sql);
			stm.setInt(1, idEndereco);
			resultado = stm.executeQuery();

			while (resultado.next()) {
				endereco.setIdEndereco(idEndereco);
				endereco.setCep(resultado.getString("cep"));
				endereco.setLogradouro(resultado.getString("logradouro"));
				endereco.setComplemento(resultado.getString("complemento"));
				endereco.setNumero(resultado.getString("numero"));
				endereco.setBairro(resultado.getString("bairro"));
				endereco.setCidade(resultado.getString("cidade"));
				endereco.setEstado(resultado.getString("estado"));
				endereco.setLatitude(resultado.getFloat("latitude"));
				endereco.setLongitute(resultado.getFloat("longitude"));
			}
			stm.close();
			resultado.close();
		} catch (Exception e) {
			System.out.println("Erro ao buscar os dados do endereco no banco de dados!");
			e.printStackTrace();
		}
		return endereco;
	}

	public boolean alterarEnderecoEmpresa(Endereco endereco) {

		Conexao conexao = new Conexao();
		boolean resultado = false;
		PreparedStatement stm = null;

		String sql = "UPDATE endereco SET cep = ?, logradouro = ?, complemento = ?, numero = ?, bairro = ?, cidade = ?, estado =?, latitude = ?, longitude = ? WHERE id_endereco = ?";

		try {
			stm = conexao.getConexao().prepareStatement(sql);
			stm.setString(1, endereco.getCep());
			stm.setString(2, endereco.getLogradouro());
			stm.setString(3, endereco.getComplemento());
			stm.setString(4, endereco.getNumero());
			stm.setString(5, endereco.getBairro());
			stm.setString(6, endereco.getCidade());
			stm.setString(7, endereco.getEstado());
			stm.setFloat(8, (float) endereco.getLatitude());
			stm.setFloat(9, (float) endereco.getLongitute());
			stm.setInt(10, endereco.getIdEndereco());
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
