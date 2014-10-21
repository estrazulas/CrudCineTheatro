package com.rhcloud.pugmg.cinetheatrosys.controle.exceptions;

import java.util.Arrays;
import java.util.List;

public class ExceptionSistemaParametros extends ExceptionSistema {

	private static final long serialVersionUID = -5369467141086771700L;
	private List<String> parametros;

	public ExceptionSistemaParametros(String msg, String... param) {
		super(msg);
		parametros = Arrays.asList(param);
	}

	public List<String> getParametros() {
		return parametros;
	}

	public void setParametros(List<String> parametros) {
		this.parametros = parametros;
	}

}
