package control;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import dao.HorarioEmpresaDao;
import model.DiaSemana;
import model.Empresa;
import model.HorarioEmpresa;
import util.MetodosUteis;

public class HorarioEmpresaControl {

	public List<HorarioEmpresa> buscaHorarios(int idEmpresa) {

		HorarioEmpresaDao horarioEmpresaDao = new HorarioEmpresaDao();

		return horarioEmpresaDao.buscaHorarioEmpresaPorIdEmpresa(idEmpresa);
	}

	public boolean deletarHorarioEmpresa(String dados) {
		boolean resultado = false;
		Gson gson = new Gson();
		HorarioEmpresa horarioEmpresa = new HorarioEmpresa();
		HorarioEmpresaDao dao = new HorarioEmpresaDao();

		horarioEmpresa = gson.fromJson(dados, HorarioEmpresa.class);

		resultado = dao.deletarHorarioEmpresa(horarioEmpresa);

		if (resultado) {
			return resultado;
		} else {
			System.out.println("Erro ao tentar deletar, funcao DeletarHorarioEmpresa retornou false!");
			return resultado;
		}

	}

	public String cadastrarHorarioEmpresa(String dados) {

		boolean resultado = false;
		Gson gson = new Gson();
		HorarioEmpresa horarioEmpresa = new HorarioEmpresa();
		HorarioEmpresaDao dao = new HorarioEmpresaDao();

		horarioEmpresa = gson.fromJson(dados, HorarioEmpresa.class);

		List<HorarioEmpresa> lista = new ArrayList<>();
		lista.add(horarioEmpresa);

		resultado = dao.cadastrarHorarioEmpresa(horarioEmpresa.getIdEmpresa(),
				MetodosUteis.transformaVetorStringToArray(MetodosUteis.trasnformaListHorarioParaVetorString(lista)));

		if (resultado) {
			lista = dao.buscaHorarioEmpresaPorIdEmpresa(horarioEmpresa.getIdEmpresa());

			JsonArray aux = new JsonArray();

			for (HorarioEmpresa horario : lista) {

				Gson dado = new Gson();

				aux.add(dado.toJsonTree(horario));
			}

			return aux.toString();
		} else {
			System.out.println(
					"Erro ao tentar cadastrar horario empresa, funcao cadastrarHorarioEmpresa retornou false!");
			return null;
		}
	}

}
