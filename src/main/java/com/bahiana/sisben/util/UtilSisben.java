package com.bahiana.sisben.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.bahiana.sisben.service.impl.ProgramacaoEntregaServiceImpl;

public class UtilSisben {
	
	
	public Integer calculaDiasMes(LocalDate mesAnoProgramacao, LocalDate dataAtual) {
		
		//Variável boolean de classe.
		ProgramacaoEntregaServiceImpl.setMesCorrente(false);

		//Recupera o ano e o mês da programação, e o ano e o mês atual.
		Integer mesProgramacao = mesAnoProgramacao.getMonth().getValue();
		Integer mesAtual = dataAtual.getMonth().getValue();
		Integer anoProgramacao = mesAnoProgramacao.getYear();
		Integer anoAtual = dataAtual.getYear();
		
		//Recuperao último dia do mês da programação.
		//LocalDate utlimaDataMes = LocalDate.now().withMonth(mesProgramacao).with(TemporalAdjusters.lastDayOfMonth());
		LocalDate utlimaDataMes = mesAnoProgramacao.withMonth(mesProgramacao).with(TemporalAdjusters.lastDayOfMonth());
		
		//Recupera a quantidade de dias do mês da programação.
		Integer qtdDiasmes = utlimaDataMes.getDayOfMonth();
		
		//Verifica se o ano e o mês atuais são iguais ao mês da programação.
		if (anoProgramacao.equals(anoAtual) ) { 
			
			if (mesProgramacao.equals(mesAtual)) { 
				  //Calcula a diferença entre a data corrente do mês e o final do mês da programação.
				  Integer diferencadias = (int) ChronoUnit.DAYS.between(dataAtual, utlimaDataMes );
				  diferencadias +=1;
				  qtdDiasmes = diferencadias;
				  
				  //Variável boolean de classe.  
				  ProgramacaoEntregaServiceImpl.setMesCorrente(true);
			}
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
	
	public Long diferencaEntreDatas() {
		
		String primeiraHora = "08-03-2023 01:10:20";
		String segundaHora = "09-03-2023 06:30:50";
		
		
		SimpleDateFormat sdf
        = new SimpleDateFormat(
            "dd-MM-yyyy HH:mm:ss");
		
		long difference_In_Hours = 0L;
		
		
		try {
			Date d1 = sdf.parse(primeiraHora);
			Date d2 = sdf.parse(segundaHora);
			
            long difference_In_Time
                = d2.getTime() - d1.getTime();
			 
			 //Calculate time difference in seconds,
	            // minutes, hours, years, and days
	            long difference_In_Seconds
	                = TimeUnit.MILLISECONDS
	                      .toSeconds(difference_In_Time)
	                  % 60;
	 
	            long difference_In_Minutes
	                = TimeUnit
	                      .MILLISECONDS
	                      .toMinutes(difference_In_Time)
	                  % 60;
	 
	             difference_In_Hours
	                = TimeUnit
	                      .MILLISECONDS
	                      .toHours(difference_In_Time)
	                  % 24;
	 
	            long difference_In_Days
	                = TimeUnit
	                      .MILLISECONDS
	                      .toDays(difference_In_Time)
	                  % 365;
	 
	            long difference_In_Years
	                = TimeUnit
	                      .MILLISECONDS
	                      .toDays(difference_In_Time)
	                  / 365l;
	 
	            // Print the date difference in
	            // years, in days, in hours, in
	            // minutes, and in seconds
	            System.out.print(
	                "Difference"
	                + " between two dates is: ");
	 
	            // Print result
	            System.out.println(
	                difference_In_Years
	                + " years, "
	                + difference_In_Days
	                + " days, "
	                + difference_In_Hours
	                + " hours, "
	                + difference_In_Minutes
	                + " minutes, "
	                + difference_In_Seconds
	                + " seconds");
	 
			
			
			
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
      

	
		return difference_In_Hours;
		
		
	}

	

}
