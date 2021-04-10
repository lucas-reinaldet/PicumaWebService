package control;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonSyntaxException;

import dao.ContatoDao;
import dao.EmpresaDao;
import dao.EnderecoDao;
import dao.FuncionarioDao;
import dao.HorarioEmpresaDao;
import dao.ServicoPrestadoDao;
import model.AreaAtuacao;
import model.Contato;
import model.Empresa;
import model.Endereco;
import model.Galeria;
import model.HorarioEmpresa;
import model.Login;
import model.ServicoPrestado;
import util.MetodosUteis;

public class EmpresaControl extends MetodosUteis {

	public String cadastrarEmpresa(String dados) {

		EmpresaDao dao = new EmpresaDao();

		Array arrayContatos = null;
		Array arrayHorario = null;

		int resultado = 0;

		try {
			Gson gson = new Gson();
			Login login = gson.fromJson(dados, Login.class);

			arrayContatos = transformaVetorStringToArray(
					transformaListContatoParaVetorString(login.getEmpresa().getListaContato()));
			arrayHorario = transformaVetorStringToArray(
					trasnformaListHorarioParaVetorString(login.getEmpresa().getListaHorarioEmpresa()));

			resultado = dao.cadastrar(login, arrayContatos, arrayHorario);

			if (resultado > 0) {
				login.getEmpresa().setIdEmpresa(resultado);
				return new Gson().toJson(login);
			}
		} catch (IllegalStateException | JsonSyntaxException exception) {
			exception.printStackTrace();
		}
		return null;
	}

	public String buscaEmpresa(int idEmpresa) {

		EmpresaDao dao = new EmpresaDao();

		Empresa empresa = dao.buscarPorId(idEmpresa);
		if (empresa != null) {
			
			ContatoDao contatoDao = new ContatoDao();
			empresa.setListaContato(contatoDao.persquisarContatosEmpresa(empresa.getIdEmpresa()));
			
			HorarioEmpresaDao horarioEmpresaDao = new HorarioEmpresaDao();
			empresa.setListaHorarioEmpresa(horarioEmpresaDao.buscaHorarioEmpresaPorIdEmpresa(empresa.getIdEmpresa()));
			
			FuncionarioDao funcionarioDao = new FuncionarioDao();
			empresa.setListaFuncionarios(funcionarioDao.listarFuncionariosPorEmpresa(empresa.getIdEmpresa()));
			
			return new Gson().toJson(empresa);
		} else {
			return null;
		}

	}

	public String listarEmpresasParaCliente() {

		EmpresaDao empresaDao = new EmpresaDao();
		ServicoPrestadoDao servicoPrestadoDao = new ServicoPrestadoDao();
		
		List<Empresa> listaEmpresa = empresaDao.listarEmpresas();

		if (listaEmpresa != null) {
			
			for (int i = 0; i < listaEmpresa.size(); i++) {
				listaEmpresa.get(i).setListaServicoPrestado(servicoPrestadoDao.listaServicoPrestadoParaCliente(listaEmpresa.get(i).getIdEmpresa()));
			}
			return new Gson().toJson(listaEmpresa);
		} else {
			return null;
		}

	}

	public boolean alterarEmpresa(String dados) {

		if (dados != null) {
			Empresa empresa;
			EmpresaDao dao = new EmpresaDao();
			Gson gson = new Gson();
			boolean resultado = false;

			empresa = gson.fromJson(dados, Empresa.class);

			resultado = dao.alterarEmpresa(empresa);
			return resultado;
		} else {
			return false;
		}
	}

	public boolean alterarNomePublicoAlvoEmpresa(String dados) {

		Empresa empresa = new Empresa();
		EmpresaDao dao = new EmpresaDao();
		Gson gson = new Gson();

		empresa = gson.fromJson(dados, Empresa.class);

		return dao.alterarNomePublicoAlvoEmpresa(empresa);

	}

	public boolean alterarDescricaoEmpresa(String dados) {

		Empresa empresa = new Empresa();
		EmpresaDao dao = new EmpresaDao();
		Gson gson = new Gson();

		empresa = gson.fromJson(dados, Empresa.class);

		return dao.alterarDescricaoEmpresa(empresa);

	}

	public boolean alterarLogoEmpresa(String dados) {

		Empresa empresa = new Empresa();
		EmpresaDao dao = new EmpresaDao();
		Gson gson = new Gson();

		empresa = gson.fromJson(dados, Empresa.class);

		return dao.alterarLogoEmpresa(empresa);
	}

	public String alterarEnderecoEmpresa(String dados) {
		EnderecoDao dao = new EnderecoDao();

		Endereco endereco = new Gson().fromJson(dados, Endereco.class);
		if (dao.alterarEnderecoEmpresa(endereco)) {
			return "true";
		} else {
			return null;
		}

	}
}
