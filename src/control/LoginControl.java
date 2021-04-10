package control;

import com.google.gson.Gson;

import dao.ClienteDao;
import dao.EmpresaDao;
import dao.LoginDao;
import model.Cliente;
import model.Empresa;
import model.Login;

public class LoginControl {

	public String realizarLogin(String dados) {

		LoginDao loginDao = new LoginDao();
		Login login = new Gson().fromJson(dados.replace("\"", "'"), Login.class);
		login = loginDao.realizarLogin(login);

		if (login.getCliente().getIdCliente() > 0) {
			ClienteDao clienteDao = new ClienteDao();
			login.setCliente(clienteDao.buscaPorId(login.getCliente().getIdCliente()));
			login.setEncontrado(true);
			return new Gson().toJson(login);
		} else if (login.getEmpresa().getIdEmpresa() > 0) {
			EmpresaDao empresaDao = new EmpresaDao();

			login.setEmpresa(empresaDao.buscarPorId(login.getEmpresa().getIdEmpresa()));
			ContatoControl contatoControl = new ContatoControl();
			HorarioEmpresaControl listaHorarioEmpresa = new HorarioEmpresaControl();
			FuncionarioControl funcionarioControl = new FuncionarioControl();

			login.getEmpresa().setListaContato(contatoControl.buscaContatoEmpresa(login.getEmpresa().getIdEmpresa()));
			login.getEmpresa().setListaHorarioEmpresa(listaHorarioEmpresa.buscaHorarios(login.getEmpresa().getIdEmpresa()));
			login.getEmpresa().setListaFuncionarios(funcionarioControl.listaDeFuncionarios(login.getEmpresa().getIdEmpresa()));
			login.setEncontrado(true);
			return new Gson().toJson(login);
		} else {
			return null;
		}
	}

}
