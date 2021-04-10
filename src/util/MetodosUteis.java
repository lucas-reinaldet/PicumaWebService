package util;

import java.sql.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import model.Contato;
import model.HorarioEmpresa;

public class MetodosUteis {

	public static String[] transformaListContatoParaVetorString(List<Contato> dados) {
		int cont = 0;
		String[] dadosAlterados = new String[dados.size() * 2];

		for (Contato contato : dados) {

			dadosAlterados[cont] = contato.getContato();
			dadosAlterados[cont + 1] = contato.getTipoContato();

			cont += 2;
		}
		return dadosAlterados;

	}

	public static String[] trasnformaListHorarioParaVetorString(List<HorarioEmpresa> dados) {
		int cont = 0;
		String[] dadosAlterados = new String[dados.size() * 5];

		for (HorarioEmpresa horario : dados) {

			dadosAlterados[cont] = String.valueOf(horario.getInicioExpediente().getTime());
			if (horario.getInicioIntervalo() != null) {
				dadosAlterados[cont + 1] = String.valueOf(horario.getInicioIntervalo().getTime());
				dadosAlterados[cont + 2] = String.valueOf(horario.getFimIntervalo().getTime());
			} else {
				GregorianCalendar inicioExpCal = new GregorianCalendar();
				inicioExpCal.set(Calendar.HOUR_OF_DAY, 0);
				inicioExpCal.set(Calendar.MINUTE, 0);
				dadosAlterados[cont + 1] = String.valueOf(inicioExpCal.getTime());
				dadosAlterados[cont + 2] = String.valueOf(inicioExpCal.getTime());
			}
			dadosAlterados[cont + 3] = String.valueOf(horario.getFimExpediente().getTime());
			dadosAlterados[cont + 4] = Integer.toString(horario.getDiaSemana().getIdDiaSemana());

			cont += 5;

		}

		return dadosAlterados;
	}

	public static Array transformaVetorStringToArray(String[] dados) {

		Connection obj = new Conexao().getConexao();

		Array dadosProntos = null;

		try {
			dadosProntos = obj.createArrayOf("VARCHAR", dados);
			obj.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dadosProntos;
	}

	public static String formatarDinheiro(Double valor) {

		NumberFormat valorFormatado = NumberFormat.getCurrencyInstance();

		return valorFormatado.format(valor);

	}

    public static String formatacaoCalendarParaHoraMinuto(Calendar calendar) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date hora = calendar.getTime();
        return sdf.format(hora);
    }

}
