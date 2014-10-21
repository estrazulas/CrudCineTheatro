package com.rhcloud.pugmg.cinetheatrosys.controle.uteis;
import java.text.ParseException;

import javax.swing.text.MaskFormatter;

import com.rhcloud.pugmg.cinetheatrosys.controle.exceptions.ExceptionSistema;
/**
 * Funções úteis para trabalhar com formatação de strings
 * @author Daniel Severo Estrázulas
 */
public class StringUteis {
	static MaskFormatter mf = null;
	public static String aplicaMascara(String valor, String mascara) {
		
		String valorformatado=valor;
		try {
			mf =  new MaskFormatter(mascara);
			mf.setValueContainsLiteralCharacters(false);
			valorformatado = mf.valueToString(valor);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return valorformatado;
	}
	
	public static void validarString(int linhareal,String... string) throws ExceptionSistema {
		if (string == null){
			throw new ExceptionSistema("Campo (string) vazio!");
		}
		int cont = 0;
		for (String s : string) {
			if (s == null || s.isEmpty()){
				throw new ExceptionSistema("Erro linha = "+linhareal+" numero/';' = ("+cont+") vazio!");
			}
			cont++;
		}
	}
	
}
