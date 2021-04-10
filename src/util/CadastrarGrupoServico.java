package util;

import java.io.File;
import java.io.FileInputStream;

import dao.GrupoServicoDao;
import model.GrupoServico;

public class CadastrarGrupoServico {


	public static void main(String[] args) {	
	
		boolean resultado = false;
		GrupoServico grupoServico = new GrupoServico();
		GrupoServicoDao dao = new GrupoServicoDao();
	
		String[] vetorGrupoClinicaEstetica = new String[]{"Estética Corporal","Estética Facial","Massoterapia"};
	
		int tam = vetorGrupoClinicaEstetica.length;
	
		for(int i =0; i < tam; i++) {
	
			grupoServico.setGrupoServico(vetorGrupoClinicaEstetica[i]);
	
			File file = new File("foto\\" + vetorGrupoClinicaEstetica[i] + ".jpg");
			int tama = (int) file.length();
			byte[] fileArray = new byte[tama];
			FileInputStream fis = null;
			
			try {
				fis = new FileInputStream(file);
				fis.read(fileArray, 0, tama);
			} catch (Exception e) {
				System.out.println(e);
			}
			
			grupoServico.setFotoGrupoServico(fileArray);
			grupoServico.setIdAreaAtuacao(1);
			
			resultado = dao.cadastrarGrupoServico(grupoServico);
			System.out.println(resultado);

		}
		
		vetorGrupoClinicaEstetica =
		new String[]{"Química","Corte & Modelagem","Depilação", "Maquiagem", "Sobrancelha", "Massoterapia"};
		grupoServico = new GrupoServico();
		
		tam = vetorGrupoClinicaEstetica.length;
		
		for(int i =0; i < tam; i++) {
	
			grupoServico.setGrupoServico(vetorGrupoClinicaEstetica[i]);
	
			File file = new File("foto\\" + vetorGrupoClinicaEstetica[i] + ".jpg");
			int tama = (int) file.length();
			byte[] fileArray = new byte[tama];
			FileInputStream fis = null;
			
			try {
				fis = new FileInputStream(file);
				fis.read(fileArray, 0, tama);
			} catch (Exception e) {
				System.out.println(e);
			}
			
			grupoServico.setFotoGrupoServico(fileArray);
			grupoServico.setIdAreaAtuacao(2);
			
			resultado = dao.cadastrarGrupoServico(grupoServico);
			System.out.println(resultado);

		}
		
		vetorGrupoClinicaEstetica =	new String[]{"Manicure & Pedicure", "Podologia"};
		grupoServico = new GrupoServico();
		
		tam = vetorGrupoClinicaEstetica.length;
		
		for(int i =0; i < tam; i++) {
	
			grupoServico.setGrupoServico(vetorGrupoClinicaEstetica[i]);
	
			File file = new File("foto\\" + vetorGrupoClinicaEstetica[i] + ".jpg");
			int tama = (int) file.length();
			byte[] fileArray = new byte[tama];
			FileInputStream fis = null;
			
			try {
				fis = new FileInputStream(file);
				fis.read(fileArray, 0, tama);
			} catch (Exception e) {
				System.out.println(e);
			}
			
			grupoServico.setFotoGrupoServico(fileArray);
			grupoServico.setIdAreaAtuacao(3);
			
			resultado = dao.cadastrarGrupoServico(grupoServico);
			System.out.println(resultado);
		}
		
		
	}
}
