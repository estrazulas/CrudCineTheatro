package com.rhcloud.pugmg.cinetheatrosys.controle;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.I18nMessage;

import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.multitype.MultiTypeCaptchaService;
import com.rhcloud.pugmg.cinetheatrosys.controle.anotacoes.RestritoLogado;
import com.rhcloud.pugmg.cinetheatrosys.controle.anotacoes.RestritoOffLine;
import com.rhcloud.pugmg.cinetheatrosys.controle.exceptions.ExceptionSistema;
import com.rhcloud.pugmg.cinetheatrosys.modelo.Usuario;
import com.rhcloud.pugmg.cinetheatrosys.modelo.auxiliares.UsuarioWeb;
import com.rhcloud.pugmg.cinetheatrosys.modelo.repositorios.UsuarioRepository;

/**
 *	Controle de acesso dos usuários cadastrados 
 * @author Daniel Severo Estrázulas
 */
@Resource
public class UsuarioController {
	
	@Autowired
	private Result result;
 
	@Autowired
	private Validator validator;

	@Autowired
	private UsuarioWeb usuarioWeb;

	@Autowired
	private MultiTypeCaptchaService captchaService;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private UsuarioRepository usuarios;
	
	
	public static String hostadd = "localhost:8080/cinetheatrosys";

	public UsuarioController() {
	}

	/**
	 * Apresenta formulário de login
	 */
	@Get
	public void formlogin() {
		if (this.usuarioWeb.isLogado()) {
			result.redirectTo(IndexController.class).index();
		}
	}

	@RestritoOffLine
	@Get
	public void cadastro() {
	}
	
	@RestritoOffLine
	@Post
	public void fazCadastro(Usuario usuario) {
		
	}

	/**
	 * Tenta fazer login
	 * @param login ( email ou login de usuário )
	 * @param senha ( senha digitada no form )
	 * @param captcha ( imagem de verificação em caso de muitas tentativas inválidas no login )
	 */
	@Post
	public void dologin(String login, String senha, String captcha) {

		Usuario ucarregado = usuarios.getUsuarioBanco(login);
	
		try {
			if(ucarregado == null){
				throw new ExceptionSistema("controle.user.dologin.naocadastrado");
			}
			
			usuarios.autenticacao( senha, login, ucarregado);
			
			//valida imagem captcha somente quando houve muitas tentativas inválidas
			validaCaptcha(captcha);
			

			//valida se o usuário está ativo
			if(!ucarregado.isAtivo())
			{
				throw new ExceptionSistema(
						"controle.user.dologin.inativo");
			}
			
			// finalmente faz login
			usuarioWeb.setLogado(ucarregado);
			
			
			result.include("msgsucesso", "controle.user.dologin.bemvindo");
			
		} catch (ExceptionSistema e) {
			usuarioWeb.addTentativaInvalida();
			e.printStackTrace();
			validator.add((new I18nMessage("user", e.getMessage())));
		} finally {
			validator.onErrorRedirectTo(UsuarioController.class).formlogin();
			String uri = (String)request.getSession().getAttribute("URI_CHAMADA");
			if(uri==null){
				result.redirectTo(IndexController.class).index();
			}else{
				request.getSession().removeAttribute("URI_CHAMADA");
				result.redirectTo(uri);
			}
		}

	}


	private void validaCaptcha(String captcha) throws ExceptionSistema {
		// verifica se precisou digitar o captcha e se este está valido
		if (usuarioWeb.muitasTentativasLogin()
				&& !verificaCaptchaService(captcha)) {
			throw new ExceptionSistema(
					"controle.user.dologin.captchaerro");
		}
	}
	
	/**
	 * Para liberar a sessão e fazer logout
	 */
	@RestritoLogado
	public void logout() {
		this.usuarioWeb.logOut();
		result.redirectTo(IndexController.class).index();
	}
	
	
	/**
	 * Faz a validação do captcha através da string
	 * @param captchaDigitado valor digitado no form
	 * @return se validou ou não
	 */
	private boolean verificaCaptchaService(String captchaDigitado) 
	{
		boolean validCaptcha = false;
		try {

			validCaptcha = captchaService.validateResponseForID(this.request
					.getSession().getId(), captchaDigitado);

		} catch (CaptchaServiceException e) {
			e.printStackTrace();
		}
		return validCaptcha;
	}

}
