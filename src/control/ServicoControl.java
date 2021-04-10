package control;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import dao.ServicoDao;
import model.Servico;

public class ServicoControl {

//	public Servico buscaServico(int idServico) {
//
//		ServicoDao servicoDao = new ServicoDao();
//		Servico servico = new Servico();
//
//		servico = servicoDao.buscaServico(idServico);
//
//		return servico;
//	}

	public String listarServicoPorAreaAtuacao(int idAreaAtuacao) {

		ServicoDao dao = new ServicoDao();
		ServicoDao servicoDao = new ServicoDao();

		JsonArray jsonArray = new JsonArray();
		
		for (Servico servico : dao.buscaServicoPorAreaAtuacao(idAreaAtuacao)) {
			Gson gson = new Gson();
			jsonArray.add(gson.toJsonTree(servico));
		}
		
		return jsonArray.toString();
	}

}
