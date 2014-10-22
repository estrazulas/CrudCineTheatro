package com.rhcloud.pugmg.cinetheatrosys.controle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.caelum.vraptor.view.Results;

import com.rhcloud.pugmg.cinetheatrosys.controle.anotacoes.RestritoVisitante;
import com.rhcloud.pugmg.cinetheatrosys.controle.exceptions.ExceptionSistema;
import com.rhcloud.pugmg.cinetheatrosys.controle.uteis.MensagensUteis;
import com.rhcloud.pugmg.cinetheatrosys.controle.uteis.paginacao.GenericDataTables;
import com.rhcloud.pugmg.cinetheatrosys.controle.uteis.paginacao.PaginacaoController;
import com.rhcloud.pugmg.cinetheatrosys.controle.validacoes.ValidacoesUsuario;
import com.rhcloud.pugmg.cinetheatrosys.modelo.Estoria;
import com.rhcloud.pugmg.cinetheatrosys.modelo.TipoCategoria;
import com.rhcloud.pugmg.cinetheatrosys.modelo.TipoPeriodo;
import com.rhcloud.pugmg.cinetheatrosys.modelo.TipoUsuario;
import com.rhcloud.pugmg.cinetheatrosys.modelo.Usuario;
import com.rhcloud.pugmg.cinetheatrosys.modelo.auxiliares.UsuarioWeb;
import com.rhcloud.pugmg.cinetheatrosys.modelo.repositorios.EstoriaRepository;
import com.rhcloud.pugmg.cinetheatrosys.modelo.repositorios.UsuarioRepository;

@Resource
public class EstoriaController extends PaginacaoController{

	@Autowired
	private Result result;

	@Autowired
	private UsuarioWeb usuarioWeb;

	@Autowired
	private EstoriaRepository estorias;

	@Autowired
	private UsuarioRepository usuarios;

	@Autowired
	private Validator validator;

	@RestritoVisitante
	@Get
	public void pgCadastroEstoriaLogado() {
		result.include("userlogado", this.usuarioWeb);
		result.redirectTo(EstoriaController.class).pgCadastroEstoria();
	}

	/**
	 * Inclusão nova estoria
	 */
	@Get
	public void pgCadastroEstoria() {
		listas();
	}

	private void listas() {
		result.include("periodos",TipoPeriodo.values());
		result.include("categorias",TipoCategoria.values());
	}

	/**
	 * Inclusão, Alteração , post
	 * 
	 * @param estoria
	 */
	@Post
	public void fazCadastroEstoria(Usuario usuario, Estoria estoria,UploadedFile arquivoImagem, UploadedFile arquivoAudio) {
		Usuario usuarioBD = null;
		try{
		
			//se não está logado então é porque é o primeiro cadastro de estória, então precisa verificar se o usuário existe antes de inseri-lo
			if( !usuarioWeb.isLogado() && usuarios.usuarioJaExiste(usuario)){
				throw new ExceptionSistema("controle.estoria.usuario.existe");
			}
			
			if(!usuarioWeb.isLogado()){
				usuarioBD = usuarios.saveNovoAcesso(usuario,
						TipoUsuario.VISITANTE);
				estoria.setUsuario(usuarioBD);
			}else{
				usuarioBD = usuarios.getUsuarioBancoPorId(usuarioWeb.getUserLogado().getId());
				estoria.setUsuario(usuarioBD);
			}
	
			if(arquivoImagem != null){
				try {
					estoria.setImagem(IOUtils.toByteArray(arquivoImagem.getFile()));
					estoria.setFormatoImagem(arquivoImagem.getContentType());
				} catch (IOException e) {
					e.printStackTrace();
					MensagensUteis.geraMensagemErro(e.getMessage(),result);
				}
			}
			
			if(arquivoAudio != null){
				try {
					estoria.setAudio(IOUtils.toByteArray(arquivoAudio.getFile()));
					estoria.setFormatoAudio(arquivoAudio.getContentType());
				} catch (IOException e) {
					e.printStackTrace();
					MensagensUteis.geraMensagemErro(e.getMessage(),result);
				}
			}
			
			estorias.gravaDados(estoria);
	
			MensagensUteis.geraMensagemSucesso(
					"controle.estoria.fazCadastroEstoria.sucesso", result);
			
		}catch(ExceptionSistema e){
			MensagensUteis.geraMensagemUsuario(validator, e);
			e.printStackTrace();
		}finally{
			result.include("usuario",usuario);
			result.include("estoria",estoria);
			validator.onErrorRedirectTo(EstoriaController.class).pgCadastroEstoria();
			result.redirectTo(IndexController.class).index();
		}
		
	}

	/**
	 * Listagem de estórias
	 */
	@RestritoVisitante
	@Get
	public void listar() {
		List<Estoria> listaEstorias = estorias
				.retornaEstoriasDoBanco(this.usuarioWeb);
		result.include("estorias", listaEstorias);
		
	}

	/**
	 * Remoção
	 * @param id
	 */
	@RestritoVisitante
	@Get("/estoria/remover/{id}")
	public void remover(Integer id) {
		try {
			Estoria estoria = this.estorias.buscaEstoria(id);

			ValidacoesUsuario.validarDonoEstoriaParaAlteracao(usuarioWeb,
					estoria);
			this.estorias.removeEstoria(id);
			
			MensagensUteis.geraMensagemSucesso("controle.estoria.remover.sucesso", result);			
		} catch (ExceptionSistema e) {
			MensagensUteis.geraMensagemUsuario(validator, e);
			e.printStackTrace();
		}
		finally{
			validator.onErrorRedirectTo(EstoriaController.class).listar();
			result.redirectTo(EstoriaController.class).listar();
		}
	}

	
	@Post("/estoria/json/paginacao")
	@Override
	public void paginacao() {
		// inicializa campos que irÃ£o para o grid
		List<String> atributosNomes = new ArrayList<String>();
		atributosNomes.add("id");
		atributosNomes.add("nome");
		atributosNomes.add("categoria");
		atributosNomes.add("periodo");

		GenericDataTables<Estoria> usrDataTable = new GenericDataTables<Estoria>(atributosNomes);

		Map<String, Object> parametersDataTable = super.getParametersDataTable();
		String sSearch = (String) parametersDataTable.get("allSearch");
		String sEcho = (String) parametersDataTable.get("sEcho");

		List<Estoria> users = this.estorias.estoriasComPaginacao("nome", parametersDataTable, usrDataTable);

		usrDataTable = new GenericDataTables<Estoria>(users, // AaData
				users.size(), // iTotalRecords
				this.estorias.quantidadeDeEstorias(), // iTotalDisplayRecords
				sSearch, // sSearch
				sEcho, // sEcho
				atributosNomes);

		// retorna em json a consulta paginada
		result.use(Results.json()).withoutRoot().from(usrDataTable).include("aaData").serialize();
	}

}
