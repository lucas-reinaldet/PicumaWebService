package util;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import dao.AreaAtuacaoDao;
import model.AreaAtuacao;

public class CadastrarAreaAtuacao {

	public static void main(String[] args) {

		boolean resultado = false;
		AreaAtuacao areaAtuacao = new AreaAtuacao();
		AreaAtuacaoDao dao = new AreaAtuacaoDao();
		List<AreaAtuacao> listaAreaAtuacao = new ArrayList<>();
		
		String[] vetorAreaAtuacao = new 
				String[]{"Clinica de Estética", "Salão de Beleza", "Pés & Mãos"};
		
		
		int tam = vetorAreaAtuacao.length;
		
		for(int i = 0; i < tam; i++) {
			
			areaAtuacao.setAreaAtuacao(vetorAreaAtuacao[i]);
			
			File file = new File("foto\\"+vetorAreaAtuacao[i]+".jpg");
			int tama = (int) file.length();
			byte[] fileArray = new byte[tama];
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(file);
				fis.read(fileArray, 0, tama);
			} catch (Exception e) {
				System.out.println(e);
			}
			
			areaAtuacao.setFotoAreaAtuacao(fileArray);
			
			listaAreaAtuacao.add(areaAtuacao);	
			
			areaAtuacao = new AreaAtuacao();
		}
		
		for(AreaAtuacao dado : listaAreaAtuacao) {
			
			resultado = dao.cadastrarAreaAtuacao(dado);
			System.out.println(resultado);
		}
		
		
	}
	
	public static byte[] resize(byte[] imgByte, String imageType, int newW, int newH) {
		BufferedImage img;
		try {
			img = ImageIO.read(new ByteArrayInputStream(imgByte));
		int w = img.getWidth();  
		int h = img.getHeight();  
		BufferedImage dimg = new BufferedImage(newW, newH, img.getType());  
		Graphics2D g = dimg.createGraphics();  
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);  
		g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);  
		g.dispose();
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		ImageIO.write(dimg, imageType, buffer);
		return buffer.toByteArray();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}  

}
