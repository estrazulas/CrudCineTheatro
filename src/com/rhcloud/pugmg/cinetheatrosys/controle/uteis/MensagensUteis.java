package com.rhcloud.pugmg.cinetheatrosys.controle.uteis;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.I18nMessage;

public class MensagensUteis {
	public static void geraMensagemUsuario(Validator validator, Exception e) {
		validator.add((new I18nMessage("sistema", e.getMessage())));
	}
	
	public static void getMensagemUsuarioParametros(Validator validator, String mensagem, Object... parametros){
		validator.add((new I18nMessage("sistema", mensagem,parametros)));
	}

	public static void geraMensagemSucesso(String codigoMsg, Result result) {
		result.include("msgsucesso",codigoMsg);
	}

	public static void geraMensagemErro(String mensagem, Result result) {
		result.include("msgerro",mensagem);
	}
}
