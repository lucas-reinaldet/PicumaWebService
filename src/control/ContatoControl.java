package control;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import dao.ContatoDao;
import model.Contato;
import model.Empresa;
import model.ServicoPrestado;

public class ContatoControl {

	public List<Contato> buscaContatoEmpresa(int idEmpresa) {

		ContatoDao contatoDao = new ContatoDao();
		Contato contato = new Contato();

		List<Contato> listaContato = contatoDao.persquisarContatosEmpresa(idEmpresa);

		return listaContato;
	}

	public List<Contato> buscaContatoCliente(int idCliente) {

		List<Contato> listaContato = new ArrayList<Contato>();
		List<Contato> listaContatoFinal = new ArrayList<Contato>();
		ContatoDao contatoDao = new ContatoDao();
		Contato contato = new Contato();

		listaContato = contatoDao.pesquisarContatosCliente(idCliente);

		return listaContatoFinal;
	}

	public boolean deletarContatoEmpresa(String dados) {

		boolean resultado = false;
		Gson gson = new Gson();
		Contato contato = new Contato();
		ContatoDao dao = new ContatoDao();

		contato = gson.fromJson(dados, Contato.class);

		resultado = dao.deletarContatoEmpresa(contato);

		if (resultado) {

			resultado = dao.deletarContato(contato);

			if (resultado) {
				return resultado;
			} else {
				System.out.println("Erro ao tentar excluir o contato!");
			}
		} else {
			System.out.println("Erro o tentar excluir  associacao entre contato e empresa!");
			return resultado;
		}

		return resultado;
	}

	public String CadastrarContatoEmpresa(String dados) {
		Gson gson = new Gson();
		Empresa empresa = new Empresa();
		ContatoDao dao = new ContatoDao();

		empresa = gson.fromJson(dados, Empresa.class);

		for (Contato contato : empresa.getListaContato()) {
			contato.setIdEmpresa(empresa.getIdEmpresa());
			dao.cadastrarContatoEmpresa(contato);
		}
		
		JsonArray jsonArray = new JsonArray();
		for (Contato servicoPrestado : dao.persquisarContatosEmpresa(empresa.getIdEmpresa())) {
			jsonArray.add(gson.toJsonTree(servicoPrestado));
		}
		
		return jsonArray.toString();
	}

}
