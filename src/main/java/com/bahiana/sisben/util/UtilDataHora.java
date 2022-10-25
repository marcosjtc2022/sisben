package com.bahiana.sisben.util;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//import br.com.correios.st.apiSoap.FeriadoClientService;

@Component
public class UtilDataHora {
	
	@Autowired
	//private FeriadoClientService feriadoClientService;

    public static Date asDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDateTime asLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static LocalDate asLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

     public static String dataFormatada(Date data){
        SimpleDateFormat formato = new SimpleDateFormat();
        formato.applyPattern("dd/MM/yyyy");
        return formato.format(data);
    }

    public static Boolean dataMaiorQueDataAtual(Date data)  {
        Date dataAtual = asDate(LocalDateTime.now());
        if (data.compareTo(dataAtual)>0) {
            return true;
        }
        return false;
    }

    public static Date dataBDEquivalenteNula() {
        return asDate(LocalDateTime.of(1900, 1, 1, 0, 0, 0));
    }

    public static boolean dataEquivalenteNula(Date data) {
        if(asDate(asLocalDate(data)).equals(asDate(LocalDate.of(1900, 1, 1)))){
            return true;
        }
        return false;
    }
    
    public static LocalDateTime asLocalDateTimeEquivalenteNula() {
    	return LocalDateTime.of(1900, 1, 1, 0, 0, 0);
    }


	public static Date ultimoDiaMesComHora(Integer mes, Integer ano){
        LocalDateTime dataNova = LocalDateTime.of(ano, mes, 1, 23, 59, 59);
        return  asDate(dataNova.with(TemporalAdjusters.lastDayOfMonth()));
    }

    public static String dataHoraFormatada(Date data){
        SimpleDateFormat formato = new SimpleDateFormat();
        formato.applyPattern("dd/MM/yyyy HH:mm:ss");
        return formato.format(data);
    }

//    public static String tempoDeExecucao(LocalDateTime horaInicio, LocalDateTime horaFim) {
//        if (horaInicio == null || horaFim == null) {
//            return "";
//        }
//        StringBuilder retorno = new StringBuilder();
//        Duration duracao = Duration.between(horaInicio, horaFim);
//        long segundosTotal = Math.abs(duracao.toMillis() / 1000);
//        if (segundosTotal < 1) {
//            return "Menor que 1 segundo";
//        }
//        long minutosTotal = Math.abs(duracao.toMinutes());
//        long horasTotal = Math.abs(duracao.toHours());
//        long qtSegundos = 0;
//        long qtMinutos = 0;
//        long qtHoras = 0;
//        String txSegundos = "";
//        String txMinutos = "";
//        String txHoras = "";
//        if (segundosTotal < 60) {
//            qtSegundos = segundosTotal;
//            txSegundos = StringUtils.right("00" + String.valueOf(qtSegundos), 2);
//        } else if (minutosTotal < 60) {
//            qtMinutos = minutosTotal;
//            qtSegundos = segundosTotal - (minutosTotal * 60);
//            txMinutos = StringUtils.right("00" + String.valueOf(qtMinutos), 2);
//            txSegundos = StringUtils.right("00" + String.valueOf(qtSegundos), 2);
//        } else {
//            qtHoras = horasTotal;
//            qtMinutos = minutosTotal - (qtHoras * 60);
//            qtSegundos = segundosTotal - (minutosTotal * 60);
//            txHoras = StringUtils.right("00" + String.valueOf(qtHoras), 2);
//            txMinutos = StringUtils.right("00" + String.valueOf(qtMinutos), 2);
//            txSegundos = StringUtils.right("00" + String.valueOf(qtSegundos), 2);
//        }
//
//        retorno.append(qtHoras > 0 ? txHoras.concat(" hora(s)") : "")
//                .append(qtMinutos > 0
//                        ? (qtHoras > 0 ? ", ".concat(txMinutos.concat(" minuto(s)")) : txMinutos.concat(" minuto(s)"))
//                        : "")
//                .append(qtSegundos > 0 ? (qtHoras > 0 || qtMinutos > 0 ? ", ".concat(txSegundos.concat(" segundo(s)"))
//                        : txSegundos.concat(" segundo(s)")) : "");
//        return retorno.toString();
//    }
    
	public static LocalDateTime converteStringDataParaLocalDateTime(final String data) {
		LocalDateTime ldt;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		TemporalAccessor parse = formatter.parse(data.replace('T', ' '));
		ldt = LocalDateTime.from(parse);
		return ldt; 
	}
	
	public static LocalDate converteStringDataParaLocalDate(final String data) {
		LocalDate ld;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		TemporalAccessor parse = formatter.parse(data);
		ld = LocalDate.from(parse);
		return ld; 
	}
	
	public static LocalDate converteStringParaLocalDatePattern(final String data, String pattern) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		LocalDate ld = LocalDate.parse(data, formatter);
		
		return ld;
	}

	public static boolean dataFimDeSemana(LocalDate dataVerificar) {
		// retorna verdadeiro se uma data cai no fim de semana
		DayOfWeek d = dataVerificar.getDayOfWeek();
	    return d == DayOfWeek.SATURDAY || d == DayOfWeek.SUNDAY;	
	   }
	
	

	// Verifica se a data passada é maior que a data atual mais 30 dias
	public static boolean tempoMaiorTrintaDias(LocalDateTime dataVerificar){
		LocalDateTime hoje = LocalDateTime.now();
		LocalDateTime dtFutura = hoje.plusDays(30);
		if(dataVerificar.isAfter(dtFutura)) {
			return true;
		}
		return false;
	
	}
	
	// Verifica se a data passada é maior que a data atual mais 30 dias
	public static boolean tempoMaiorTrintaDias(LocalDate dataVerificar)
	{
		LocalDate hoje = LocalDate.now();
		LocalDate dtFutura = hoje.plusDays(30);
		if(dataVerificar.isAfter(dtFutura)) {
			return true;
		}
		return false;
	}
	
	
	public static LocalDateTime trataDatas(String dt) {
		return dt != null && !dt.isBlank()  ? converteStringDataParaLocalDateTime(dt + "T00:00:00") : null;
	}
	

//	public boolean ehDiaUtil(LocalDate dataVerificar) throws Exception
//	{
//		try 
//		{
//			if (dataFimDeSemana(dataVerificar)  || feriadoClientService.ehFeriado(dataVerificar)) 
//			{
//				return false;
//			}
//			
//			return true;
//		}
//		catch(Exception e) 
//		{
//			throw new Exception("Componente de Feriado OFFLINE. Entre em contato com a equipe técnica.");
//		}
//	}

//	public  LocalDate proximoDiaUtil() throws Exception 
//	{
//		LocalDate retorno 	= LocalDate.now();
//		Boolean sair 		= false;
//				
//		while(!sair)
//		{
//			retorno = retorno.plusDays(1);
//			
//			if (ehDiaUtil(retorno)) 
//			{
//				sair = true;
//			}
//		}
//		return retorno;
//	}
}

