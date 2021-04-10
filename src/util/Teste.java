package util;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import control.ClienteControl;
import control.EmpresaControl;
import control.LoginControl;
import dao.ClienteDao;
import dao.EmpresaDao;
import model.AreaAtuacao;
import model.Cliente;
import model.Contato;
import model.DiaSemana;
import model.Empresa;
import model.Endereco;
import model.Funcionario;
import model.HorarioEmpresa;
import model.Login;

public class Teste {

//	public static void main(String[] args) {
//
//		Cliente cliente = new Cliente();
//		Empresa empresa = new Empresa();
//		Contato contato = new Contato();
//		Login login = new Login();
//		HorarioEmpresa horarioEmpresa = new HorarioEmpresa();
//		DiaSemana diaSemana = new DiaSemana();
//		AreaAtuacao areaAtuacao = new AreaAtuacao();
//		Endereco endereco = new Endereco();
//		Funcionario funcionario = new Funcionario();
//		
//		ClienteDao dao = new ClienteDao();
//		EmpresaDao empresaDao = new EmpresaDao();
// 		ClienteControl control = new ClienteControl();
// 		EmpresaControl empresaControl = new EmpresaControl();
// 		LoginControl loginControl = new LoginControl();
//		MetodosUteis mu = new MetodosUteis();		
//		List<Contato> listaContatos = new ArrayList<Contato>();
//		List<HorarioEmpresa> listaHorariosEmpresa = new ArrayList<HorarioEmpresa>();		
//
//		boolean resultado = false; 
//		Gson gson = new Gson();
//		Calendar calender = Calendar.getInstance();
//		calender.set( Calendar.HOUR_OF_DAY, Calendar.MINUTE);
//		SimpleDateFormat s = new SimpleDateFormat("hh:mm");
//		s.format(calender.getTime());
//
//		contato.setContato("4599686952");
//		contato.setTipoContato("celular");
//		listaContatos.add(contato);
//		
//		contato = new Contato();
//		contato.setContato("4599379127");
//		contato.setTipoContato("celular");
//		listaContatos.add(contato);
//		
//		areaAtuacao.setIdAreaAtuacao(1);
//		
//		endereco.setBairro("jd Caralho");
//		endereco.setCep("12345679");
//		endereco.setCidade("foz do iguacu");
//		endereco.setComplemento("do seu lado");
//		endereco.setEstado("PR");
//		endereco.setLogradouro("rua do caralho");
//		endereco.setNumero("123");
//		diaSemana.setIdDiaSemana(7);
//
//		horarioEmpresa.setDiaSemana(diaSemana);
//		horarioEmpresa.setFimExpediente(calender);
//		horarioEmpresa.setFimIntervalo(calender);
//		horarioEmpresa.setInicioExpediente(calender);
//		horarioEmpresa.setInicioIntervalo(calender);
//		
//		listaHorariosEmpresa.add(horarioEmpresa);	
//		diaSemana = new DiaSemana();
//		diaSemana.setIdDiaSemana(3);
//		
//		horarioEmpresa = new HorarioEmpresa();
//		horarioEmpresa.setDiaSemana(diaSemana);
//		horarioEmpresa.setFimExpediente(calender);
//		horarioEmpresa.setFimIntervalo(calender);
//		horarioEmpresa.setInicioExpediente(calender);
//		horarioEmpresa.setInicioIntervalo(calender);
//		
//		listaHorariosEmpresa.add(horarioEmpresa);
//		
//		File file = new File("beyonce.jpg");
//		int tam = (int) file.length();
//		byte[] fileArray = new byte[tam];
//		FileInputStream fis = null;
//		try {
//			fis = new FileInputStream(file);
//			fis.read(fileArray, 0, tam);
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//
//		empresa.setAreaAtuacao(areaAtuacao);
//		empresa.setCnpjEmpresa("1238568780");
//		empresa.setContato(listaContatos);
//		empresa.setDescricaoEmpresa("Empresa boa.");
//		empresa.setEndereco(endereco);
//		empresa.setPuclicoAlvo("homem");
//		empresa.setHorarioEmpresa(listaHorariosEmpresa);
//		empresa.setLogoEmpresa(fileArray);
//		empresa.setNomeFantasia("Salaosalao");
//
//		cliente.setNomeCliente("junior");
//		cliente.setNascimentoCliente(calender);
//		cliente.setGenero("homem");
//		cliente.setContato(listaContatos);
//		cliente.setCpf("15646761231231");
//		cliente.setFotoCliente(fileArray);
//		
//		login.setLoginGoogle("idsalaocaralho");
//		login.setUsuario("SalaoSalao");
//		login.setSenha("salaosalao");
//		login.setCliente(cliente);
//		login.setEmpresa(empresa);
//
//
//	
//		String aux = gson.toJson(login).toString();
//		resultado = 	empresaControl.cadastrarEmpresa(aux);
//		System.out.println(aux);
//		
//		String aux2 = gson.toJson(empresaDao.buscarPorId(7));
//		System.out.println(aux2);
//
//		 resultado = dao.cadastrar(mu.transformaVetorStringToArray(MetodosUteis.transformaListContatoParaVetorString(listaContatos)), login);
//		 System.out.println(resultado);
//		JsonArray array = new JsonArray();
//		array = empresaControl.listarEmpresas();
//		
//		System.out.println(resultado);
		
//	}
}
