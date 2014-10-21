package com.rhcloud.pugmg.cinetheatrosys.controle;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

import com.rhcloud.pugmg.cinetheatrosys.controle.anotacoes.RestritoLogado;
import com.rhcloud.pugmg.cinetheatrosys.modelo.auxiliares.UsuarioWeb;

@Resource
public class IndexController {

	
	@Autowired
	private Result result;
	
	@Autowired
	private UsuarioWeb usuarioWeb;
	
	@RestritoLogado
	@Path("/")
	public void index() 
	{

	}
}
