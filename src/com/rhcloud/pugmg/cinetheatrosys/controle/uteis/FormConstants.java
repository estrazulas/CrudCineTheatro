package com.rhcloud.pugmg.cinetheatrosys.controle.uteis;

/**
 * Constantes que podem ser acessadas no controller e no view
 * @author Daniel Severo Estrázulas
 */
public class FormConstants {

	private final static String ACT_INSERE="INSERE", ACT_ALTERA="ALTERA", ACT_UPLOAD="UPLOAD";


	private final static String MODO_JANELA="JANELA", MODO_JANELA_NOUNLOAD="JANELASEMUNLOAD";

	public static String getModoJanela() {
		return MODO_JANELA;
	}
	public static String getModoJanelaNoUnload() {
		return MODO_JANELA_NOUNLOAD;
	}

	public static String getActInsere() {
		return ACT_INSERE;
	}

	public static String getActAltera() {
		return ACT_ALTERA;
	}
	
	public static String getActUpload() {
		return ACT_UPLOAD;
	}
	
}
