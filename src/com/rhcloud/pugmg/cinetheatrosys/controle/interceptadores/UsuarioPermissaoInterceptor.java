package com.rhcloud.pugmg.cinetheatrosys.controle.interceptadores;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.core.Localization;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.caelum.vraptor.validator.ValidationMessage;

import com.rhcloud.pugmg.cinetheatrosys.controle.IndexController;
import com.rhcloud.pugmg.cinetheatrosys.controle.UsuarioController;
import com.rhcloud.pugmg.cinetheatrosys.controle.anotacoes.RestritoAdmin;
import com.rhcloud.pugmg.cinetheatrosys.controle.anotacoes.RestritoLogado;
import com.rhcloud.pugmg.cinetheatrosys.controle.anotacoes.RestritoOffLine;
import com.rhcloud.pugmg.cinetheatrosys.controle.anotacoes.RestritoVisitante;
import com.rhcloud.pugmg.cinetheatrosys.modelo.auxiliares.UsuarioWeb;

/**
 * Classe que irá impedir que o usuário acesse o que não tem permissão
 * @author Daniel Severo Estrázulas
 */
@Intercepts
public class UsuarioPermissaoInterceptor implements Interceptor {

	@Autowired
	private UsuarioWeb usuario;
	
	@Autowired
	private Result result;
	
	
	@Autowired
	private  Localization localization;
	
	private String motivo ="controle.interceptadores.user.sempermissao";
	
	private String url="";
	
	@Autowired
	private HttpServletRequest request;
	

	public UsuarioPermissaoInterceptor() {
	}

	/**
	 * Verifica se é para interceptar a requisição HTTP
	 */
	public boolean accepts(ResourceMethod method) {
		return (
				   ((!logadoAtivo())
				&& method.containsAnnotation(RestritoLogado.class) ) ||
				
				   (estaLogado() && method.containsAnnotation(RestritoOffLine.class)) ||
				   
				   ((!logadoAtivo() ||!usuario.isVisitante()) && method.containsAnnotation(RestritoVisitante.class)) ||
				   
				   ((!logadoAtivo() ||!usuario.isAdmin()) && method.containsAnnotation(RestritoAdmin.class)) 
				   
				   
				   
				);
	}

	/**
	 * VErificar se o usuário é ativo
	 * @return
	 */
	private boolean ativo() {
		if(!usuario.isAtivo()){
			this.motivo = "controle.interceptadores.user.inativo";
			usuario.logOut();
			return false;
		}
		return true;
	}


	/**
	 * Verificar se o usuário está logado e ativo
	 * @return
	 */
	private boolean logadoAtivo(){
		return (estaLogado() && ativo());
	}
	
	/**
	 * Verificar se o usuário está logado
	 * @return
	 */
	private boolean estaLogado() {
		return this.usuario.isLogado();
	}


	/**
	 * Faz o bloqueio e redirecionamento após execução do método accepts
	 */
	public void intercept(InterceptorStack stack, ResourceMethod method, Object resourceInstance) throws InterceptionException 
	{
		ValidationMessage mensagem = null;
		
		if(url.isEmpty())
			mensagem  = new ValidationMessage(localization.getMessage(motivo),"");
		else
			mensagem  = new ValidationMessage(localization.getMessage(motivo),"",url);
		
		  
		
		request.getSession().setAttribute("URI_CHAMADA", request.getRequestURI().substring(request.getContextPath().length()));
		
		motivo ="";
		
		if(method.containsAnnotation(RestritoOffLine.class)){
			mensagem  = new ValidationMessage(localization.getMessage("controle.interceptadores.user.offline"),"usuario.permissao",url);
			result.include("errors", Arrays.asList(mensagem));
			result.redirectTo(IndexController.class).index();
		}else{
			result.include("errors", Arrays.asList(mensagem));
			result.redirectTo(UsuarioController.class).formlogin();
		}

	}

}
