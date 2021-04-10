package control;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import dao.GrupoServicoDao;
import model.GrupoServico;

public class GrupoServicoControl {

	public GrupoServico buscaGrupo(int idGrupoServico) {

		GrupoServico grupoServico = new GrupoServico();
		GrupoServicoDao gruposervicoDao = new GrupoServicoDao();

		grupoServico = gruposervicoDao.buscaGrupo(idGrupoServico);

		if (grupoServico != null) {
			return grupoServico;
		} else {
			System.out.println("grupoServico retornou vazio ou nulo do banco de dados!");
			return null;
		}

	}

	public String listarGrupoServico() {

		List<GrupoServico> listaGrupoServico = new ArrayList<>();
		GrupoServicoDao dao = new GrupoServicoDao();

		listaGrupoServico = dao.listarGrupoServicos();

		if (listaGrupoServico != null) {

			JsonArray dados = new JsonArray();

			for (GrupoServico grupoServico : listaGrupoServico) {

				Gson dado = new Gson();

				dados.add(dado.toJsonTree(grupoServico));
			}
			return dados.toString();

		} else {

			return null;
		}

	}

	public String listarGrupoServicoAreaAtuacao(int idAreaAtuacao) {
		List<GrupoServico> listaGrupoServico = new ArrayList<>();
		GrupoServicoDao dao = new GrupoServicoDao();

		listaGrupoServico = dao.listarGrupoServicosPorAreaAtuacao(idAreaAtuacao);

		if (listaGrupoServico != null) {

			JsonArray dados = new JsonArray();

			for (GrupoServico grupoServico : listaGrupoServico) {

				Gson dado = new Gson();

				dados.add(dado.toJsonTree(grupoServico));
			}
			return dados.toString();

		} else {

			return null;
		}
	}

}
