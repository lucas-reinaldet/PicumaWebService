package control;

import java.util.List;

import com.google.gson.Gson;

import dao.ServicoPrestadoDao;
import model.GrupoServico;
import model.ServicoPrestado;

public class ServicoPrestadoControl {

	public boolean cadastrarServicosPrestado(String dados) {
		boolean resultado = false;
		int idServicoPrestado;
		if (dados != null) {

			Gson gson = new Gson();
			ServicoPrestado servicoPrestado = gson.fromJson(dados, ServicoPrestado.class);

			ServicoPrestadoDao dao = new ServicoPrestadoDao();
			idServicoPrestado = dao.cadastrarServicoPrestado(servicoPrestado);

			if (idServicoPrestado > 0) {
				resultado = true;
				if (servicoPrestado.getListaIdFuncionario() != null) {
					for (Integer func : servicoPrestado.getListaIdFuncionario()) {

						resultado = dao.cadastrarServicoPrestadoFuncionario(idServicoPrestado, func);

					}
				}
			}
		}
		return resultado;

	}

	// public List<ServicoPrestado> buscaServicosPrestados(int idEmpresa) {
	//
	// List<ServicoPrestado> listaServicoPrestado = new ArrayList<>();
	// ServicoPrestadoDao servicoPrestadoDao = new ServicoPrestadoDao();
	//
	// listaServicoPrestado =
	// servicoPrestadoDao.buscaServicosPrestadoEmpresa(idEmpresa);
	//
	//
	// return listaServicoPrestadoFinal;
	// }

	public boolean alterarServicosPrestado(String dados) {

		boolean resultado = false;
		int ok = 0;
		Gson gson = new Gson();
		ServicoPrestadoDao dao = new ServicoPrestadoDao();
		ServicoPrestado servicoPrestado = new ServicoPrestado();
		servicoPrestado = gson.fromJson(dados, ServicoPrestado.class);

		if (servicoPrestado != null) {
			ok = dao.cadastrarServicoPrestado(servicoPrestado);
			resultado = true;
			return resultado;
		} else {
			return resultado;
		}
	}

	public boolean deletarServicosPrestado(int id) {

		boolean resultado = false;

		if (id > 0) {
			ServicoPrestadoDao dao = new ServicoPrestadoDao();
			resultado = dao.deletarServicoPrestado(id);
			return resultado;
		} else {
			return resultado;
		}
	}

	public String listaServicoPrestado(int idEmpresa) {

		ServicoPrestadoDao dao = new ServicoPrestadoDao();
		List<ServicoPrestado> listaServicoPrestado = dao.listaServicoPrestado(idEmpresa);

		for (int i = 0; i < listaServicoPrestado.size(); i++) {
			listaServicoPrestado.get(i).setListaIdFuncionario(
					dao.listaIdFuncionarioServicoPrestado(listaServicoPrestado.get(i).getIdServicoPrestado()));
		}

		return new Gson().toJson(listaServicoPrestado).toString().replace("\"", "'");
	}

	public String listaGrupoServicoPrestado(int idEmpresa) {

		ServicoPrestadoDao dao = new ServicoPrestadoDao();
		List<GrupoServico> listaGrupoServicoPrestado = dao.listaGrupoServicoPrestado(idEmpresa);

		return new Gson().toJson(listaGrupoServicoPrestado).toString().replace("\"", "'");
	}

}
