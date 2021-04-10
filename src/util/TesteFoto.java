package util;

import java.util.List;

import dao.AreaAtuacaoDao;
import model.AreaAtuacao;

public class TesteFoto {
	
	public static void main(String[] args) {
		
		AreaAtuacaoDao dao = new AreaAtuacaoDao();
		
		List<AreaAtuacao> lista = dao.listarAreaAtuacao();
		
	}

}
