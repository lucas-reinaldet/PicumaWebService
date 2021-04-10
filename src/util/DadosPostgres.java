package util;

public class DadosPostgres {

	// Criando constantes com os dados do banco POSTGRES

	protected static final String DRIVER = "org.postgresql.Driver";
	protected static final String ENDERECO = "localhost";
	protected static final String USUARIO = "postgres";
	protected static final String SENHA = "30894";
	protected static final String PORTA = "5432";
	protected static final String BANCO = "picuma";
	protected static final String URL = "jdbc:postgresql://" + ENDERECO + ":" + PORTA + "/" + BANCO;

}
