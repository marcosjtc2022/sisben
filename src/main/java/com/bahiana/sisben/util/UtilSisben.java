package com.bahiana.sisben.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

public class UtilSisben {
	
	
	public Integer calculaDiasMes(LocalDate dataAtual) {
		
		Integer mes = dataAtual.getMonth().getValue();
		
		LocalDate utlimaDataMes = LocalDate.now().withMonth(mes).with(TemporalAdjusters.lastDayOfMonth());
		
		//LocalDate dtEngravidou = Mommy.getDtPregnantDate();
		Integer Diferencadias = (int) ChronoUnit.DAYS.between(dataAtual, utlimaDataMes );
		Diferencadias +=1;
		
		//Integer dias = utlimaDataMes.getDayOfMonth();
		
		return Diferencadias;
	}

}
