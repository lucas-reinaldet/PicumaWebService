package control;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import dao.ClienteDao;
import model.Cliente;
import model.Contato;
import model.Favoritos;
import model.Login;
import util.MetodosUteis;

public class ClienteControl extends MetodosUteis {

	private boolean resultado = false;

	public String cadastrarCliente(String dados) {

		ClienteDao dao = new ClienteDao();
		Login login = new Gson().fromJson(dados.replace("\"", "'"), Login.class);
		int idCliente = dao.cadastrar(
				transformaVetorStringToArray(
						MetodosUteis.transformaListContatoParaVetorString(login.getCliente().getListaContato())),
				login);
		if (idCliente > 0) {
			login.getCliente().setIdCliente(idCliente);
			return new Gson().toJson(login);
		} else {
			return null;
		}
	}

	public String buscarClientePorId(int idCliente) {

		Cliente cliente = new Cliente();
		ClienteDao dao = new ClienteDao();
		cliente = dao.buscaPorId(idCliente);

		if (cliente != null) {

			List<Contato> listaContato = new ArrayList<Contato>();
			ContatoControl contatoControl = new ContatoControl();
			listaContato = contatoControl.buscaContatoCliente(cliente.getIdCliente());
			cliente.setListaContato(listaContato);
			return new Gson().toJson(cliente);
		} else {
			return null;
		}
	}

	public String listarFavoritosCliente(Integer idCliente) {

		ClienteDao clienteDao = new ClienteDao();
		List<Favoritos> listaFavoritos = clienteDao.listarFavoritos(idCliente);
		return new Gson().toJson(listaFavoritos);
	}

	public String cadastrarFavoritoCliente(String ids) {

		ClienteDao clienteDao = new ClienteDao();

		String id[] = ids.split(",");

		if (clienteDao.cadastrarFavorito(Integer.valueOf(id[0]), Integer.valueOf(id[1]))) {
			return "true";
		} else {
			return null;
		}
	}

	public String excluirFavoritoCliente(String ids) {

		ClienteDao clienteDao = new ClienteDao();

		String id[] = ids.split(",");

		if (clienteDao.excluirFavorito(Integer.valueOf(id[0]), Integer.valueOf(id[1]))) {
			return "true";
		} else {
			return null;
		}
	}

}