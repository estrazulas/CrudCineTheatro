package com.rhcloud.pugmg.cinetheatrosys.controle.uteis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Funções uteis para trabalhar com datas
 * @author Daniel Severo Estrázulas
 */
public class DataUtil {
	public static  SimpleDateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy"); 
	public static  SimpleDateFormat formatadorAno = new SimpleDateFormat("yyyy");
	
	public static Date atualSomenteData(){
		
		Date dataSomente = null;
		try {
			Calendar cal = Calendar.getInstance();
			String strDate = formatadorData.format(cal.getTime());
			Date date = formatadorData.parse(strDate);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return dataSomente;
	}

	public static Date getDataSistema() {
		return new Date();
	}
	
	public static String dataFormatada(Date date){
		return formatadorData.format(date);
	}
}
