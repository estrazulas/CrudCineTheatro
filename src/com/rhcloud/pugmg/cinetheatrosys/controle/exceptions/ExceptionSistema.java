package com.rhcloud.pugmg.cinetheatrosys.controle.exceptions;

/**
 * Classe para apresentação de erros
 * @author Daniel Severo Estrázulas
 *
 */
public class ExceptionSistema extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String categoria;

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	/**
	 * Mostra erro simples
	 * @param msg
	 */
	public ExceptionSistema(String msg){
		super(msg);
	}
	/**
	 * Mostra erro informando uma categoria de erro
	 * @param categoria
	 * @param msg
	 */
	public ExceptionSistema(String categoria, String msg){
		super(msg);
		this.categoria=categoria;
	}
}
