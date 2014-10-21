package com.rhcloud.pugmg.cinetheatrosys.modelo.auxiliares;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;

import com.rhcloud.pugmg.cinetheatrosys.modelo.TipoUsuario;
import com.rhcloud.pugmg.cinetheatrosys.modelo.Usuario;

/**
 * Classe para controle de acesso do usuário logado e para verificar
 * suas permissões, sempre é carregado na primeira vez que a sessão é iniciada
 * após login
 * @author Daniel Severo Estrázulas
 */
@Component
@SessionScoped
public class UsuarioWeb {

	/** Acesso aos dados do usuário logado */
	private Usuario logado;

	public  int  NV_ADMIN = 9999, NV_VISITANTE = 1111;
	/**
	 * Controle de tentativas de login
	 */
	public int contaTentativas = 0;

	/**
	 * Nivel de permissão do usuário atual
	 */
	private int nivelPermissao = 0;

	public int getNivelPermissao() 
	{
		return nivelPermissao;
	}

	public void setNivelPermissao(int nivelPermissao) {
		this.nivelPermissao = nivelPermissao;
	}

	public Usuario getUserLogado() {
		return logado;
	}

	public void setLogado(Usuario logado) {
		this.logado = logado;
		if(logado.getTipo() == TipoUsuario.ADMIN){
			this.nivelPermissao = NV_ADMIN;
		}else
		if(logado.getTipo() == TipoUsuario.VISITANTE){
			this.nivelPermissao = NV_VISITANTE;
		}
		this.zeraTentativas();
	}

	public boolean isLogado() {
		return logado != null;
	}

	public void addTentativaInvalida() {
		this.contaTentativas++;
	}

	private void zeraTentativas() {
		this.contaTentativas = 0;
	}

	public boolean muitasTentativasLogin() {
		return this.contaTentativas > 3;
	}

	public void logOut() {
		this.logado = null;
		this.nivelPermissao = 0;
	}

	public boolean exclusivo(int nivel) {
		return nivelPermissao == nivel;
	}

	public boolean isAdmin() {
		return nivelPermissao >= NV_ADMIN;
	}
	
	public boolean isVisitante() {
		return nivelPermissao >= NV_VISITANTE;
	}
	
	public boolean temVinculo() {
		return nivelPermissao > 0;
	}

	public void setUsuario(Usuario logado) {
		this.logado = logado;
	}

	public boolean isAtivo() {
		return this.logado.isAtivo();
	}
}