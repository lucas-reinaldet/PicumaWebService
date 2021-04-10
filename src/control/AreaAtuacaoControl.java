package control;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import dao.AreaAtuacaoDao;
import model.AreaAtuacao;

public class AreaAtuacaoControl {

	public AreaAtuacao buscarAreaAtuacaoPorId(int IdAreaAtuacao) {

		if (IdAreaAtuacao > 0) {

			AreaAtuacao areaAtuacao = new AreaAtuacao();
			AreaAtuacaoDao dao = new AreaAtuacaoDao();

			areaAtuacao = dao.buscaAreaAtuacaoPorId(IdAreaAtuacao);

			if (areaAtuacao != null) {

				return areaAtuacao;
			} else {
				return null;
			}
		} else {
			return null;
		}

	}

	public String listarAreaAtuacao() {
		AreaAtuacaoDao dao = new AreaAtuacaoDao();
		List<AreaAtuacao> lista = new ArrayList<>();
		lista = dao.listarAreaAtuacao();
		if (lista != null) {
			JsonArray dados = new JsonArray();
			for (AreaAtuacao areaAtuacao : lista) {

				Gson dado = new Gson();
				dados.add(dado.toJsonTree(areaAtuacao));
			}
			return dados.toString();
		} else {
			return null;
		}

	}

}
