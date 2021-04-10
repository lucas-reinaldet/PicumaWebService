package control;

import java.util.List;

import com.google.gson.Gson;

import dao.ContatoDao;
import dao.HorarioMarcadoDao;
import model.HorarioMarcado;

public class HorarioMarcadoControl {

	public String cadastrarHorarioMarcado(String dados) {

		HorarioMarcadoDao dao = new HorarioMarcadoDao();
		HorarioMarcado horarioMarcado = new Gson().fromJson(dados, HorarioMarcado.class);

		if (dao.cadastrarHorarioMarcado(horarioMarcado)) {
			return "true";
		} else {
			return null;
		}
	}

	public String buscarHorarioMarcadoPorId(int id) {

		HorarioMarcado horarioMarcado = new HorarioMarcado();
		HorarioMarcadoDao dao = new HorarioMarcadoDao();
		String aux = "";
		Gson gson = new Gson();

		horarioMarcado = dao.buscarHorarioMarcadoPorId(id);
		if (horarioMarcado != null) {
			aux = gson.toJson(horarioMarcado);
			return aux;
		} else {
			System.out.println("A Busca retornou um objeto invalido!");
			return null;
		}
	}

	public String listarHorarioMarcadoParaEmpresa(int idEmpresa) {

		HorarioMarcadoDao dao = new HorarioMarcadoDao();
		List<HorarioMarcado> listaHorarioMarcado = dao.listarHorarioMarcadoParaEmpresa(idEmpresa);
		if (listaHorarioMarcado != null) {

			ContatoDao contatoDao = new ContatoDao();
			for (int i = 0; i < listaHorarioMarcado.size(); i++) {
				listaHorarioMarcado.get(i).getCliente().setListaContato(
						contatoDao.pesquisarContatosCliente(listaHorarioMarcado.get(i).getCliente().getIdCliente()));
			}
			return new Gson().toJson(listaHorarioMarcado);
		} else {
			return null;
		}
	}

	public String listarHorarioMarcadoParaCliente(int idCliente) {

		HorarioMarcadoDao dao = new HorarioMarcadoDao();
		List<HorarioMarcado> listaHorarioMarcado = dao.listarHorarioMarcadoParaCliente(idCliente);
		if (listaHorarioMarcado != null) {

			ContatoDao contatoDao = new ContatoDao();
			for (int i = 0; i < listaHorarioMarcado.size(); i++) {
				listaHorarioMarcado.get(i).getEmpresa().setListaContato(
						contatoDao.persquisarContatosEmpresa(listaHorarioMarcado.get(i).getEmpresa().getIdEmpresa()));
			}
			return new Gson().toJson(listaHorarioMarcado);
		} else {
			return null;
		}
	}

	public String alterarStatusHorarioMarcado(String dados) {

		String valores[] = dados.split(",");
		HorarioMarcadoDao horarioMarcadoDao = new HorarioMarcadoDao();
		if (horarioMarcadoDao.alterarStatusHorarioMarcado(Integer.valueOf(valores[0]), valores[1])) {
			return "true";
		} else {
			return null;
		}
	}
}
