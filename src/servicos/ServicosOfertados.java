package servicos;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import control.AreaAtuacaoControl;
import control.ClienteControl;
import control.ComentarioControl;
import control.ContatoControl;
import control.EmpresaControl;
import control.FotoGaleriaControl;
import control.FuncionarioControl;
import control.GrupoServicoControl;
import control.HorarioEmpresaControl;
import control.HorarioMarcadoControl;
import control.LoginControl;
import control.ServicoControl;
import control.ServicoPrestadoControl;
import model.GrupoServico;
import util.Constantes;

@Path(Constantes.PATH_SERVICOS_OFERTADOS)
public class ServicosOfertados {

	@POST
	@Path(Constantes.PATH_CADASTRAR_CLIENTE)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String cadastrarCliente(String dados) {

		ClienteControl control = new ClienteControl();
		return control.cadastrarCliente(dados);

	}

	@POST
	@Path(Constantes.PATH_CADASTRAR_EMPRESA)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String cadastrarEmpresa(String dados) {

		String resultado;

		if (dados != null) {
			EmpresaControl control = new EmpresaControl();
			resultado = control.cadastrarEmpresa(dados);

			if (resultado != null) {
				return resultado;
			} else {
				System.out.println(
						"Nao foi possivel cadastrar a emoresa, funcao control.cadastrarEmpresa retornou false!");
				return resultado;
			}
		} else {
			System.out.println("Dados da empresa chegarma vazios ou nulos no webService!");
			return null;
		}
	}

	@POST
	@Path(Constantes.PATH_DELETAR_HORARIO_EMPRESA)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String deletarHorarioEmpresa(String dados) {

		boolean resultado = false;
		HorarioEmpresaControl control = new HorarioEmpresaControl();

		if (dados != null) {
			resultado = control.deletarHorarioEmpresa(dados);
			if (resultado) {
				return "true";
			} else {
				return null;
			}
		} else {
			return null;
		}

	}

	@POST
	@Path(Constantes.PATH_ALTERAR_NOME_ATENDIMENTO_EMPRESA)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String alterarNomePublicoAlvoEmpresa(String dados) {

		boolean resultado = false;
		EmpresaControl control = new EmpresaControl();

		if (dados != null) {

			resultado = control.alterarNomePublicoAlvoEmpresa(dados);
			if (resultado) {
				return "true";
			} else {
				return null;
			}
		} else {
			return null;
		}

	}

	@POST
	@Path(Constantes.PATH_ALTERAR_DESCRICAO_EMPRESA)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String alterarDescricaoEmpresa(String dados) {

		boolean resultado = false;
		EmpresaControl control = new EmpresaControl();

		if (dados != null) {

			resultado = control.alterarDescricaoEmpresa(dados);
			if (resultado) {
				return "true";
			} else {
				return null;
			}
		} else {
			return null;
		}

	}

	@POST
	@Path(Constantes.PATH_ALTERAR_LOGO_EMPRESA)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String alterarLogoEmpresa(String dados) {

		boolean resultado = false;
		EmpresaControl control = new EmpresaControl();

		if (dados != null) {

			resultado = control.alterarLogoEmpresa(dados);
			if (resultado) {
				return "true";
			} else {
				return null;
			}
		} else {
			return null;
		}

	}

	@POST
	@Path(Constantes.PATH_ALTERAR_ENDERECO_EMPRESA)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String alterarEnderecoEmpresa(String dados) {

		EmpresaControl control = new EmpresaControl();
		return control.alterarEnderecoEmpresa(dados);
	}

	@POST
	@Path(Constantes.PATH_CADASTRAR_HORARIO_EMPRESA)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String cadastrarHorarioEmpresa(String dados) {

		String resultado = null;
		HorarioEmpresaControl control = new HorarioEmpresaControl();

		if (dados != null) {
			resultado = control.cadastrarHorarioEmpresa(dados);
			return resultado;
		} else {
			return resultado;
		}

	}

	@POST
	@Path(Constantes.PATH_REALIZAR_LOGIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String realizarLogin(String dados) {

		if (dados != null) {
			LoginControl control = new LoginControl();
			String resultado = "";
			resultado = control.realizarLogin(dados);
			if (resultado != null) {
				return resultado;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	@POST
	@Path(Constantes.PATH_LISTAR_EMPRESAS)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String listarEmpresas() {

		EmpresaControl control = new EmpresaControl();
		return control.listarEmpresasParaCliente();
	}

	@POST
	@Path(Constantes.PATH_LISTAR_AREA_ATUACAO)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String listarAreaAtuacao() {

		AreaAtuacaoControl control = new AreaAtuacaoControl();
		String dados = "";

		dados = control.listarAreaAtuacao();

		if (dados != null) {
			return dados.replace("\"", "'");
		} else {
			return null;
		}

	}

	@POST
	@Path(Constantes.PATH_LISTAR_GRUPO_SERVICO)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String buscarGrupoServico() {

		GrupoServicoControl control = new GrupoServicoControl();
		GrupoServico grupoServico = new GrupoServico();
		String dados = "";

		dados = control.listarGrupoServico();

		if (dados != null) {
			return dados.replace("\"", "'");
		} else {
			return null;
		}
	}

	@POST
	@Path(Constantes.PATH_LISTAR_EMPRESA_POR_ID)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String buscarEmpresa(String idEmpresa) {

		EmpresaControl control = new EmpresaControl();
		return control.buscaEmpresa(Integer.valueOf(idEmpresa));

	}

	@POST
	@Path(Constantes.PATH_LISTAR_FOTOS_GALERIA)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String listaFotosGaleria(String idEmpresa) {

		FotoGaleriaControl control = new FotoGaleriaControl();
		return control.listarFotosGaleria(Integer.valueOf(idEmpresa));

	}

	@POST
	@Path(Constantes.PATH_CADASTRAR_FOTO_GALERIA)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String cadastrarFotoGaleria(String dados) {
		FotoGaleriaControl control = new FotoGaleriaControl();
		return control.cadastrarFoto(dados);
	}

	@POST
	@Path(Constantes.PATH_DELETAR_FOTO_GALERIA_EMPRESA)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String deletarFotoGaleria(String dados) {

		FotoGaleriaControl control = new FotoGaleriaControl();
		return control.deletarFotoGaleria(dados);

	}

	@POST
	@Path(Constantes.PATH_CADASTRAR_FUNCIONARIO_EMPRESA)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String cadastrarFuncionario(String dados) {

		if (dados != null) {
			FuncionarioControl control = new FuncionarioControl();
			return control.cadastrarFuncionario(dados);
		} else {
			return null;
		}

	}

	@POST
	@Path(Constantes.PATH_LISTAR_COMENTARIO)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String listarComentario(String idEmpresa) {

		ComentarioControl control = new ComentarioControl();
		return control.listarComentarios(Integer.valueOf(idEmpresa));

	}

	@POST
	@Path(Constantes.PATH_LISTAR_FUNCIONARIOS)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String listarFuncionarios(int idEmpresa) {

		if (idEmpresa > 0) {
			FuncionarioControl control = new FuncionarioControl();
			String dados = "";
			dados = control.listarFuncionarios(idEmpresa);
			if (dados != null) {
				return dados;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	@POST
	@Path(Constantes.PATH_CADASTRAR_COMENTARIO)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String cadastrarComentario(String dados) {

		ComentarioControl control = new ComentarioControl();
		return control.cadastrarComentario(dados);

	}

	@POST
	@Path(Constantes.PATH_ALTERAR_COMENTARIO)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String alterarComentario(String dados) {
		boolean resultado = false;

		if (dados != null) {
			ComentarioControl control = new ComentarioControl();
			resultado = control.alterarComentario(dados);
			if (resultado) {
				return "true";
			} else {
				return null;
			}
		} else {
			System.out.println("Dados do comentario chegaram nulos ou vazios no webService!");
			return null;
		}
	}

	@POST
	@Path(Constantes.PATH_ALTERAR_FUNCIONARIO)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String alterarFuncionario(String dados) {
		boolean resultado = false;

		if (dados != null) {
			FuncionarioControl control = new FuncionarioControl();
			resultado = control.alterarFuncionario(dados);
			if (resultado) {
				return "true";
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	@POST
	@Path(Constantes.PATH_DELETAR_FUNCIONARIO_EMPRESA)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String deletarFuncionario(String dados) {

		FuncionarioControl control = new FuncionarioControl();
		return control.deletarFuncionario(dados);
	}

	@POST
	@Path(Constantes.PATH_DESASSOCIAR_FUNCIONARIO_SERVICO_PRESTADO_EMPRESA)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String desassociarFuncionarioServicoEmpresa(String dados) {

		FuncionarioControl control = new FuncionarioControl();
		return control.desassociarFuncionarioServicoPrestado(dados);
	}

	@POST
	@Path(Constantes.PATH_DESATIVAR_COMENTARIO)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String desativarComentario(int idComentario) {
		boolean resultado = false;
		if (idComentario > 0) {
			ComentarioControl control = new ComentarioControl();
			resultado = control.desativarComentario(idComentario);
			if (resultado) {
				return "true";
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	@POST
	@Path(Constantes.PATH_CADASTRAR_SERVICO_PRESTADO)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String cadastrarServicoPrestado(String dados) {

		boolean resultado = false;
		ServicoPrestadoControl control = new ServicoPrestadoControl();

		if (dados != null) {
			resultado = control.cadastrarServicosPrestado(dados);
			if (resultado) {
				return "true";
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	@POST
	@Path(Constantes.PATH_ALTERAR_SERVICO_PRESTADO)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String alterarServicoPrestado(String dados) {

		boolean resultado = false;
		ServicoPrestadoControl control = new ServicoPrestadoControl();

		if (dados != null) {
			resultado = control.alterarServicosPrestado(dados);
			if (resultado) {
				return "true";
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	@POST
	@Path(Constantes.PATH_DELETAR_SERVICO_PRESTADO)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String deletarServicoPrestado(int id) {

		boolean resultado = false;
		if (id > 0) {
			ServicoPrestadoControl control = new ServicoPrestadoControl();
			resultado = control.deletarServicosPrestado(id);
			if (resultado) {
				return "true";
			} else {
				return null;
			}
		} else {
			return null;
		}

	}

	@POST
	@Path(Constantes.PATH_DELETAR_CONTATO_EMPRESA)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String deletarContatoEmpresa(String dados) {

		boolean resultado = false;
		ContatoControl control = new ContatoControl();

		if (dados != null) {
			resultado = control.deletarContatoEmpresa(dados);
			if (resultado) {
				return "true";
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	@POST
	@Path(Constantes.PATH_CADASTRAR_CONTATO_EMPRESA)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String cadastrarContatoEmpresa(String dados) {
		ContatoControl control = new ContatoControl();
		if (dados != null) {
			return control.CadastrarContatoEmpresa(dados);
		} else {
			return null;
		}
	}

	@POST
	@Path(Constantes.PATH_LISTAR_GRUPO_SERVICO_AREA_ATUACAO)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String listarGrupoServicoAreaAtuacao(String idAreaAtuacao) {

		GrupoServicoControl control = new GrupoServicoControl();
		String dados = "";
		dados = control.listarGrupoServicoAreaAtuacao(Integer.valueOf(idAreaAtuacao));
		if (dados != null) {
			return dados;
		} else {
			return null;
		}

	}

	@POST
	@Path(Constantes.PATH_LISTAR_SERVICO_AREA_ATUACAO)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String listarServicoAreaAtuacao(String idEmpresa) {

		ServicoControl control = new ServicoControl();
		String dados = "";
		dados = control.listarServicoPorAreaAtuacao(Integer.valueOf(idEmpresa));
		if (dados != null) {
			return dados;
		} else {
			return null;
		}

	}

	@POST
	@Path(Constantes.PATH_LISTAR_SERVICO_PRESTADO)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String listarServicoPrestado(String idEmpresa) {
		ServicoPrestadoControl servicoPrestadoControl = new ServicoPrestadoControl();
		return servicoPrestadoControl.listaServicoPrestado(Integer.valueOf(idEmpresa));

	}

	@POST
	@Path(Constantes.PATH_LISTAR_GRUPO_SERVICO_PRESTADO)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String listarGrupoServicoPrestado(String idEmpresa) {
		ServicoPrestadoControl servicoPrestadoControl = new ServicoPrestadoControl();
		return servicoPrestadoControl.listaGrupoServicoPrestado(Integer.valueOf(idEmpresa));

	}

	@POST
	@Path(Constantes.PATH_LISTAR_FAVORITOS_CLIENTE)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String listarFavoritosCliente(String idCliente) {
		ClienteControl clienteControl = new ClienteControl();
		return clienteControl.listarFavoritosCliente(Integer.valueOf(idCliente));

	}

	@POST
	@Path(Constantes.PATH_CADASTRAR_FAVORITO_CLIENTE)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String cadastrarFavoritoCliente(String ids) {
		ClienteControl clienteControl = new ClienteControl();
		return clienteControl.cadastrarFavoritoCliente(ids);

	}

	@POST
	@Path(Constantes.PATH_EXCLUIR_FAVORITO_CLIENTE)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String excluirFavoritoCliente(String ids) {
		ClienteControl clienteControl = new ClienteControl();
		return clienteControl.excluirFavoritoCliente(ids);

	}

	@POST
	@Path(Constantes.PATH_CADASTRO_HORARIO_MARCADO)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String cadastroHorarioMarcado(String dados) {
		HorarioMarcadoControl horarioMarcadoControl = new HorarioMarcadoControl();
		return horarioMarcadoControl.cadastrarHorarioMarcado(dados);
	}

	@POST
	@Path(Constantes.PATH_LISTAR_HORARIO_MARCADO_PARA_EMPRESA)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String listagemHorarioMarcadoParaEmpresa(String idEmpresa) {
		HorarioMarcadoControl horarioMarcadoControl = new HorarioMarcadoControl();
		return horarioMarcadoControl.listarHorarioMarcadoParaEmpresa(Integer.valueOf(idEmpresa));
	}

	@POST
	@Path(Constantes.PATH_LISTAR_HORARIO_MARCADO_PARA_CLIENTE)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String listagemHorarioMarcadoParaCliente(String idCliente) {
		HorarioMarcadoControl horarioMarcadoControl = new HorarioMarcadoControl();
		return horarioMarcadoControl.listarHorarioMarcadoParaCliente(Integer.valueOf(idCliente));
	}

	@POST
	@Path(Constantes.PATH_ALTERAR_STATUS_HORARIO_MARCADO)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String alterarStatusHorarioMarcado(String dados) {
		HorarioMarcadoControl horarioMarcadoControl = new HorarioMarcadoControl();
		return horarioMarcadoControl.alterarStatusHorarioMarcado(dados);
	}
}
