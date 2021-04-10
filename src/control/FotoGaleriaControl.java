package control;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import dao.FotoGaleriaDao;
import dao.GaleriaDao;
import model.FotoGaleria;
import model.Galeria;

public class FotoGaleriaControl {

	public String cadastrarFoto(String dados) {
		boolean verificador = false;
		FotoGaleriaDao fotoDao = new FotoGaleriaDao();
		Galeria galeria = new Gson().fromJson(dados, Galeria.class);
		for (FotoGaleria fotoGaleria : galeria.getListaFotoGaleria()) {
			return String.valueOf(fotoDao.cadastrarFoto(fotoGaleria, galeria));
		}

		return null;
	}

	public String listarFotosGaleria(int id) {

		GaleriaDao galeriaDao = new GaleriaDao();
		FotoGaleriaDao fotoGaleriaDao = new FotoGaleriaDao();
		
		List<Galeria> listaGaleria = galeriaDao.listarGaleria(id);
		if (listaGaleria != null) {
			for (int i = 0; i < listaGaleria.size(); i++) {
				listaGaleria.get(i).setListaFotoGaleria(fotoGaleriaDao.listarFotosdaGaleria(listaGaleria.get(i).getIdGaleria()));
			}
		}
		
		return new Gson().toJson(listaGaleria);

	}

	public String deletarFotoGaleria(String dados) {

		FotoGaleriaDao fotoDao = new FotoGaleriaDao();
		FotoGaleria fotoGaleria = new Gson().fromJson(dados, FotoGaleria.class);
		if (fotoDao.deletarFotoGaleria(fotoGaleria)) {
			return "true";
		} else {
			return null;
		}
	}
}
