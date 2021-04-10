package control;

import dao.DiaSemanaDao;
import model.DiaSemana;


public class DiaSemanaControl {

	public DiaSemana buscaDiaSemana(int idDiaSemana) {

		DiaSemana diaSemana = new DiaSemana();
		DiaSemanaDao diaSemanaDao = new DiaSemanaDao();
		
		diaSemana = diaSemanaDao.buscaDiaSemana(idDiaSemana);
		
		return diaSemana;
	}



}
