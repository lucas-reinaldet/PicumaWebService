package dao;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.AreaAtuacao;
import model.Empresa;
import model.Endereco;
import model.Login;
import util.Conexao;

public class EmpresaDao {

	public int cadastrar(Login login, Array contato, Array horario) {

		Conexao conexao = new Conexao();
		int resultado = 0;
		PreparedStatement stm = null;
		ResultSet resultSet = null;

		String sql = "SELECT * FROM cadastro_dados_empresa(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";

		try {

			stm = conexao.getConexao().prepareStatement(sql);
			stm.setString(1, login.getEmpresa().getNomeFantasia());
			stm.setString(2, login.getEmpresa().getCnpjEmpresa());
			stm.setString(3, login.getEmpresa().getCpfEmpresa());
			stm.setString(4, login.getEmpresa().getDescricaoEmpresa());
			stm.setBytes(5, login.getEmpresa().getLogoEmpresa());
			stm.setInt(6, login.getEmpresa().getAreaAtuacao().getIdAreaAtuacao());
			stm.setArray(7, contato);
			stm.setString(8, login.getEmpresa().getEndereco().getCep());
			stm.setString(9, login.getEmpresa().getEndereco().getLogradouro());
			stm.setString(10, login.getEmpresa().getEndereco().getComplemento());
			stm.setString(11, login.getEmpresa().getEndereco().getNumero());
			stm.setString(12, login.getEmpresa().getEndereco().getBairro());
			stm.setString(13, login.getEmpresa().getEndereco().getCidade());
			stm.setString(14, login.getEmpresa().getEndereco().getEstado());
			stm.setFloat(15, (float) login.getEmpresa().getEndereco().getLatitude());
			stm.setFloat(16, (float) login.getEmpresa().getEndereco().getLongitute());
			stm.setString(17, login.getEmpresa().getPublicoAlvo());
			stm.setArray(18, horario);
			stm.setString(19, login.getUsuario());
			stm.setString(20, login.getSenha());
			stm.setString(21, login.getLoginGoogle());
			resultSet = stm.executeQuery();
			if (resultSet.next()) {
				resultado = resultSet.getInt(1);
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

	public Empresa buscarPorId(int idEmpresa) {

		Conexao conexao = new Conexao();
		PreparedStatement stm = null;
		ResultSet resultSet = null;
		Empresa empresa = new Empresa();

		String sql = "SELECT ep.id_empresa AS id, ep.nome_fantasia AS nome, ep.cpf_empresa AS cpf, ep.cnpj_empresa AS cnpj, ep.logo_empresa AS logo, "
				+ "ep.genero AS genero, ep.descricao_empresa AS descricao_empresa, "
				+ "a.id_area_atuacao AS id_area_atuacao, a.area_atuacao AS area_atuacao, a.foto_area_atuacao AS foto_area_atuacao, "
				+ "e.id_endereco AS id_endereco, e.cep AS cep, e.logradouro AS logradouro, e.complemento AS complemento, e.numero AS numero, e.bairro AS bairro, "
				+ "e.cidade AS cidade, " + "e.estado AS estado, e.latitude AS latitude, e.longitude AS longitude "
				+ "FROM empresa AS ep " + "INNER JOIN area_atuacao AS a ON (ep.id_area_atuacao = a.id_area_atuacao) "
				+ "INNER JOIN endereco AS e ON (ep.id_endereco = e.id_endereco) "
				+ "WHERE ep.empresa_ativada = true AND id_empresa = ?";

		try {
			stm = conexao.getConexao().prepareStatement(sql);
			stm.setInt(1, idEmpresa);
			resultSet = stm.executeQuery();
			if (resultSet.next()) {
				empresa.setIdEmpresa(resultSet.getInt("id"));
				empresa.setNomeFantasia(resultSet.getString("nome"));
				empresa.setCpfEmpresa(resultSet.getString("cpf"));
				empresa.setCnpjEmpresa(resultSet.getString("cnpj"));
				empresa.setLogoEmpresa(resultSet.getBytes("logo"));
				empresa.setPublicoAlvo(resultSet.getString("genero"));
				empresa.setDescricaoEmpresa(resultSet.getString("descricao_empresa"));

				AreaAtuacao areaAtuacao = new AreaAtuacao();
				areaAtuacao.setIdAreaAtuacao(resultSet.getInt("id_area_atuacao"));
				areaAtuacao.setAreaAtuacao(resultSet.getString("area_atuacao"));
				areaAtuacao.setFotoAreaAtuacao(resultSet.getBytes("foto_area_atuacao"));

				empresa.setAreaAtuacao(areaAtuacao);

				Endereco endereco = new Endereco();
				endereco.setIdEndereco(resultSet.getInt("id_endereco"));
				endereco.setCep(resultSet.getString("cep"));
				endereco.setLogradouro(resultSet.getString("logradouro"));
				endereco.setComplemento(resultSet.getString("complemento"));
				endereco.setNumero(resultSet.getString("numero"));
				endereco.setBairro(resultSet.getString("bairro"));
				endereco.setCidade(resultSet.getString("cidade"));
				endereco.setEstado(resultSet.getString("estado"));
				endereco.setLatitude(resultSet.getFloat("latitude"));
				endereco.setLongitute(resultSet.getFloat("longitude"));

				empresa.setEndereco(endereco);
			}
			stm.close();
			resultSet.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.desconectar();
		}
		return empresa;
	}

	public List<Empresa> listarEmpresas() {

		List<Empresa> listaEmpresa = new ArrayList<Empresa>();
		Conexao conexao = new Conexao();
		PreparedStatement stm = null;
		ResultSet resultSet = null;

		String sql = "SELECT ep.id_empresa AS id, ep.nome_fantasia AS nome, ep.cpf_empresa AS cpf, ep.cnpj_empresa AS cnpj, ep.logo_empresa AS logo, "
				+ "ep.genero AS genero, "
				+ "a.id_area_atuacao AS id_area_atuacao, a.area_atuacao AS area_atuacao, a.foto_area_atuacao AS foto_area_atuacao, "
				+ "e.cep AS cep, e.logradouro AS logradouro, e.complemento AS complemento, e.numero AS numero, e.bairro AS bairro, "
				+ "e.cidade AS cidade, " + "e.estado AS estado, e.latitude AS latitude, e.longitude AS longitude "
				+ "FROM empresa AS ep " + "INNER JOIN area_atuacao AS a ON (ep.id_area_atuacao = a.id_area_atuacao) "
				+ "INNER JOIN endereco AS e ON (ep.id_endereco = e.id_endereco) "
				+ "WHERE ep.empresa_ativada = true ORDER BY ep.nome_fantasia ASC";

		try {
			stm = conexao.getConexao().prepareStatement(sql);
			resultSet = stm.executeQuery();

			while (resultSet.next()) {
				Empresa empresa = new Empresa();
				empresa.setIdEmpresa(resultSet.getInt("id"));
				empresa.setNomeFantasia(resultSet.getString("nome"));
				empresa.setCpfEmpresa(resultSet.getString("cpf"));
				empresa.setCnpjEmpresa(resultSet.getString("cnpj"));
				empresa.setLogoEmpresa(resultSet.getBytes("logo"));
				empresa.setPublicoAlvo(resultSet.getString("genero"));

				AreaAtuacao areaAtuacao = new AreaAtuacao();
				areaAtuacao.setIdAreaAtuacao(resultSet.getInt("id_area_atuacao"));
				areaAtuacao.setAreaAtuacao(resultSet.getString("area_atuacao"));
				areaAtuacao.setFotoAreaAtuacao(resultSet.getBytes("foto_area_atuacao"));

				empresa.setAreaAtuacao(areaAtuacao);

				Endereco endereco = new Endereco();
				endereco.setCep(resultSet.getString("cep"));
				endereco.setLogradouro(resultSet.getString("logradouro"));
				endereco.setComplemento(resultSet.getString("complemento"));
				endereco.setNumero(resultSet.getString("numero"));
				endereco.setBairro(resultSet.getString("bairro"));
				endereco.setCidade(resultSet.getString("cidade"));
				endereco.setEstado(resultSet.getString("estado"));
				endereco.setLatitude(resultSet.getFloat("latitude"));
				endereco.setLongitute(resultSet.getFloat("longitude"));

				empresa.setEndereco(endereco);

				listaEmpresa.add(empresa);
			}
			stm.close();
			resultSet.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.desconectar();
		}
		return listaEmpresa;
	}

	public boolean alterarEmpresa(Empresa empresa) {

		Conexao conexao = new Conexao();
		boolean resultado = false;
		PreparedStatement stm = null;

		String sql = "UPDATE empresa SET nome_fantasia = ?, cpf_empresa = ?, cnpj_empresa =?, descricao_empresa = ?, logo_empresa = ?, empresa_ativada = ?, genero?, id_area_atuacao = ?"
				+ " WHERE id_empresa = ?";
		try {

			stm = conexao.getConexao().prepareStatement(sql);
			stm.setString(1, empresa.getNomeFantasia());
			stm.setString(2, empresa.getCpfEmpresa());
			stm.setString(3, empresa.getCnpjEmpresa());
			stm.setString(4, empresa.getDescricaoEmpresa());
			stm.setBytes(5, empresa.getLogoEmpresa());
			stm.setBoolean(6, empresa.isEmpresaAtivada());
			stm.setString(7, empresa.getPublicoAlvo());
			stm.setInt(8, empresa.getAreaAtuacao().getIdAreaAtuacao());
			stm.setInt(9, empresa.getIdEmpresa());

			resultado = stm.execute();
			stm.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.desconectar();
		}
		return resultado;
	}

	public boolean alterarNomePublicoAlvoEmpresa(Empresa empresa) {

		Conexao conexao = new Conexao();
		boolean resultado = false;
		PreparedStatement stm = null;

		String sql = "UPDATE empresa SET nome_fantasia = ?, genero = ? " + " WHERE id_empresa = ?";
		try {

			stm = conexao.getConexao().prepareStatement(sql);
			stm.setString(1, empresa.getNomeFantasia());
			stm.setString(2, empresa.getPublicoAlvo());
			stm.setInt(3, empresa.getIdEmpresa());

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

	public boolean alterarDescricaoEmpresa(Empresa empresa) {

		Conexao conexao = new Conexao();
		boolean resultado = false;
		PreparedStatement stm = null;

		String sql = "UPDATE empresa SET descricao_empresa = ?" + " WHERE id_empresa = ?";
		try {

			stm = conexao.getConexao().prepareStatement(sql);
			stm.setString(1, empresa.getDescricaoEmpresa());
			stm.setInt(2, empresa.getIdEmpresa());

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

	public boolean alterarLogoEmpresa(Empresa empresa) {

		Conexao conexao = new Conexao();
		boolean resultado = false;
		PreparedStatement stm = null;

		String sql = "UPDATE empresa SET logo_empresa = ?" + " WHERE id_empresa = ?";
		try {

			stm = conexao.getConexao().prepareStatement(sql);
			stm.setBytes(1, empresa.getLogoEmpresa());
			stm.setInt(2, empresa.getIdEmpresa());

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
