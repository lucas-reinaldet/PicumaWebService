package model;

public class GrupoServico {

	private int idGrupoServico;
	private String grupoServico;
	private byte[] fotoGrupoServico;
	private int idAreaAtuacao;

	public int getIdAreaAtuacao() {
		return idAreaAtuacao;
	}

	public void setIdAreaAtuacao(int idAreaAtuacao) {
		this.idAreaAtuacao = idAreaAtuacao;
	}

	public int getIdGrupoServico() {
		return idGrupoServico;
	}

	public void setIdGrupoServico(int idGrupoServico) {
		this.idGrupoServico = idGrupoServico;
	}

	public String getGrupoServico() {
		return grupoServico;
	}

	public void setGrupoServico(String grupoServico) {
		this.grupoServico = grupoServico;
	}

	public byte[] getFotoGrupoServico() {
		return fotoGrupoServico;
	}

	public void setFotoGrupoServico(byte[] fotoGrupoServico) {
		this.fotoGrupoServico = fotoGrupoServico;
	}
}
