package com.rhcloud.pugmg.cinetheatrosys.controle.uteis;

import br.com.caelum.vraptor.Result;

public final class ViewUteis {

	public static void paginaAjax(Result r){
		r.include("ajax", true);
	}
}
