package control;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import dao.FotoGaleriaDao;
import dao.FuncionarioDao;
import model.FotoGaleria;
import model.Funcionario;

public class FuncionarioControl {

	public Funcionario buscaFuncionario(int idFuncionario) {

		FuncionarioDao funcionarioDao = new FuncionarioDao();
		Funcionario funcionario = new Funcionario();

		funcionario = funcionarioDao.buscaFuncionario(idFuncionario);

		if (funcionario != null) {
			return funcionario;
		} else {
			System.out.println("Banco de dados retornou um objeto vazio ou nulo!");
			return funcionario;
		}
	}

	public String cadastrarFuncionario(String dados) {

		Type transformer = new TypeToken<List<Funcionario>>() {
		}.getType();

		List<Funcionario> funcionarios = new Gson().fromJson(dados, transformer);
		FuncionarioDao dao = new FuncionarioDao();

		int idEmpresa = 0;
		for (Funcionario funcionario : funcionarios) {
			dao.cadastrarFuncionario(funcionario);
			idEmpresa = funcionario.getIdEmpresa();
		}

		return listarFuncionarios(idEmpresa);
	}

	public String listarFuncionarios(int id) {

		FuncionarioDao dao = new FuncionarioDao();
		List<Funcionario> lista = new ArrayList<>();
		lista = dao.listarFuncionariosPorEmpresa(id);

		if (lista != null) {

			JsonArray dados = new JsonArray();

			for (Funcionario funcionario : lista) {

				Gson dado = new Gson();

				dados.add(dado.toJsonTree(funcionario));
			}
			return dados.toString();
		} else {
			System.out.println("Lista de funcionarios retornou vazia!!");
			return null;
		}
	}

	public List<Funcionario> listaDeFuncionarios(int id) {

		FuncionarioDao dao = new FuncionarioDao();
		List<Funcionario> lista = new ArrayList<>();
		return dao.listarFuncionariosPorEmpresa(id);
	}

	public boolean alterarFuncionario(String dados) {

		boolean resultado = false;
		Gson gson = new Gson();
		Funcionario funcionario = new Funcionario();
		FuncionarioDao dao = new FuncionarioDao();

		funcionario = gson.fromJson(dados, Funcionario.class);
		resultado = dao.alterarFuncionario(funcionario);
		return resultado;
	}

	public String deletarFuncionario(String dados) {
		FuncionarioDao dao = new FuncionarioDao();
		if (dao.deletarFuncionario(Integer.valueOf(dados))) {
			return "true";
		} else {
			return null;
		}
	}
	
	public String desassociarFuncionarioServicoPrestado(String dados) {

		FuncionarioDao funcionarioDao = new FuncionarioDao();
		String ids[] = dados.split(",");
		if (funcionarioDao.desassociarFuncionarioServicoPrestado(Integer.valueOf(ids[0]), Integer.valueOf(ids[1]))) {
			return "true";
		} else {
			return null;
		}
	}
}
