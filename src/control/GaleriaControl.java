package control;

import java.util.ArrayList;
import java.util.List;

import dao.GaleriaDao;
import model.Galeria;

public class GaleriaControl {

	public List<Galeria> listarGaleria(int idEmpresa) {
		
		if(idEmpresa > 0) {
	
			List<Galeria> listaGaleria = new ArrayList<Galeria>();
			GaleriaDao dao = new GaleriaDao();
			listaGaleria = dao.listarGaleria(idEmpresa);
			
			if(listaGaleria != null) {
				
				
				
				
				return listaGaleria;			
				
			} else {
				System.out.println("Funcao listarGaleria retornou uma lista vazia ou nula!");
				return null;
			}
		
		} else {
			return null;
		}

	}

}
