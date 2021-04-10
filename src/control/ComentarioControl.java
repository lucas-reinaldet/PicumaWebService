package control;

import java.util.List;

import com.google.gson.Gson;

import dao.ComentarioDao;
import model.Comentario;

public class ComentarioControl {

	public String cadastrarComentario(String dados) {

		ComentarioDao comentarioDao = new ComentarioDao();
		Comentario comentario = new Gson().fromJson(dados, Comentario.class);

		if (comentarioDao.cadastrarComentario(comentario)) {
			return "true";
		} else {
			return null;
		}

	}

	public String listarComentarios(int idEmpresa) {

		ComentarioDao comentarioDao = new ComentarioDao();
		List<Comentario> listaComentario = comentarioDao.listarComentariosPorEmpresa(idEmpresa);
		if (listaComentario != null) {
			return new Gson().toJson(listaComentario);
		} else {
			return null;
		}
	}

	public boolean alterarComentario(String dados) {
		boolean resultado = false;

		Gson gson = new Gson();
		Comentario comentario = new Comentario();
		comentario = gson.fromJson(dados, Comentario.class);
		ComentarioDao comentarioDao = new ComentarioDao();

		resultado = comentarioDao.alterarComentario(comentario);

		if (resultado) {
			return resultado;
		} else {
			System.out.println(
					"nao foi possivel alterar o comentario, funcao comentarioDao.alterarComentario retornou false!");
			return resultado;
		}

	}

	public boolean desativarComentario(int idComentario) {

		boolean resultado = false;

		ComentarioDao dao = new ComentarioDao();
		resultado = dao.desativaComentario(idComentario);

		if (resultado) {
			return resultado;
		} else {
			System.out.println("Erro ao desativar o comentario!!");
			return resultado;
		}

	}
}
