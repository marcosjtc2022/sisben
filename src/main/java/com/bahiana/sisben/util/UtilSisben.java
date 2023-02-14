package com.bahiana.sisben.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.TimeZone;

import com.bahiana.sisben.service.impl.ProgramacaoEntregaServiceImpl;

public class UtilSisben {
	
	
	public Integer calculaDiasMes(LocalDate mesAnoProgramacao, LocalDate dataAtual) {

		//Recupera o mês da programação e o mês atual.
		Integer mesProgramacao = mesAnoProgramacao.getMonth().getValue();
		Integer mesAtual = dataAtual.getMonth().getValue();
		
		//Recuperao último dia do mês das programação.
		LocalDate utlimaDataMes = LocalDate.now().withMonth(mesProgramacao).with(TemporalAdjusters.lastDayOfMonth());
		
		//Recupera a quantidade de dias do mês da programação.
		Integer qtdDiasmes = utlimaDataMes.getDayOfMonth();
		
		//Verifica se o mês atual é igual ao mês da programação.
		if (mesProgramacao == mesAtual ) { 
			  //Calcula a diferença entre a data corrente do mês e o final do mês da programação.
			  Integer diferencadias = (int) ChronoUnit.DAYS.between(dataAtual, utlimaDataMes );
			  diferencadias +=1;
			  qtdDiasmes = diferencadias;
			  
			  //Variável boolean de classe.  
			  ProgramacaoEntregaServiceImpl.setMesCorrente(true);
		}
		
		return qtdDiasmes;
	}
	
	
	public String getDiaDaSemana(LocalDate dataProgramacao){ //ex 07/03/2017
	   
	         String diaDaSemana = "";
	        switch (dataProgramacao.getDayOfWeek()) {
	            case SUNDAY:
	            	diaDaSemana = "Domingo";
	                break;
	            case MONDAY:
	            	diaDaSemana = "Segunda-Feira";
	                break;
	            case TUESDAY:
	            	diaDaSemana = "Terça-Feira";
	            break;
	            case WEDNESDAY:
	            	diaDaSemana = "Quarta-Feira";
	                break;
	            case THURSDAY:
	            	diaDaSemana = "Quinta-Feira";
	                break;
	            case FRIDAY:
	            	diaDaSemana = "Sexta-Feira";
	                break;
	            case SATURDAY:
	            	diaDaSemana = "Sábado";

	        }
	    
	    return diaDaSemana;
	}
	
	

	 
	
//	  public static void main(String args[]){ 
//	    try{
//	      // constrói a primeira data
//	      DateFormat fm = new SimpleDateFormat(
//	        "dd/MM/yyyy HH:mm:ss");
//	      Date data1 = (Date)fm.parse("20/12/2008 16:20:12");
//	 
//	      // constrói a segunda data
//	      fm = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//	      Date data2 = (Date)fm.parse("30/12/2008 21:30:15");
//	 
//	      // vamos obter a diferença em semanas, dias, horas,
//	      // minutos e segundos
//	      long segundos = (data2.getTime() - 
//	        data1.getTime()) / 1000;
//	      int semanas = (int)Math.floor(segundos / 604800);
//	      segundos -= semanas * 604800;
//	      int dias = (int)Math.floor(segundos / 86400);
//	      segundos -= dias * 86400;
//	      int horas = (int)Math.floor(segundos / 3600);
//	      segundos -= horas * 3600;
//	      int minutos = (int)Math.floor(segundos / 60);
//	      segundos -= minutos * 60;
//	 
//	      // exibe o resultado
//	      System.out.println("As duas datas tem " +
//	        semanas + " semanas, " + dias + " dias, " + 
//	        horas + " horas, " + minutos + " minutos e " +
//	        segundos + " segundos de diferença");
//	    }
//	    catch(ParseException e){
//	      e.printStackTrace();
//	    }
//	  } 
//	

	

}
