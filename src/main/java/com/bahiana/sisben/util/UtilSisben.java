package com.bahiana.sisben.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

public class UtilSisben {
	
	
	public Integer calculaDiasMes(LocalDate dataAtual) {
		
		Integer mes = dataAtual.getMonth().getValue();
		
		LocalDate utlimaDataMes = LocalDate.now().withMonth(mes).with(TemporalAdjusters.lastDayOfMonth());
		
		Integer diferencadias = (int) ChronoUnit.DAYS.between(dataAtual, utlimaDataMes );
		diferencadias +=1;
		
		//Integer dias = utlimaDataMes.getDayOfMonth();
		
		return diferencadias;
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

}
