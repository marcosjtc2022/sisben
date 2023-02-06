package com.bahiana.sisben.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

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
			  Integer diferencadias = (int) ChronoUnit.DAYS.between(dataAtual, utlimaDataMes );
			  diferencadias +=1;
			  qtdDiasmes = diferencadias;
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

}
