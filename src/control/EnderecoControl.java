package control;

import dao.EnderecoDao;
import model.Endereco;

public class EnderecoControl {

	public Endereco buscaEndereco(int idEndereco) {
		if (idEndereco > 0) {
			Endereco endereco = new Endereco();
			EnderecoDao dao = new EnderecoDao();
			endereco = dao.buscaEndereco(idEndereco);
			if (endereco != null) {
				return endereco;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

}
