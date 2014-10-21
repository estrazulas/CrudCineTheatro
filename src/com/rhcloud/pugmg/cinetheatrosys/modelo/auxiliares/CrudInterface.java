package com.rhcloud.pugmg.cinetheatrosys.modelo.auxiliares;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;


public interface CrudInterface<T> {

	@Get
	public abstract void listar();

	@Get
	public abstract void cadastro();

	@Get
	public abstract void cadastro(int id);

	@Post
	public abstract void gravar(T elaborador);
	
}