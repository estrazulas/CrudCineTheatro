package com.rhcloud.pugmg.cinetheatrosys.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;

import com.rhcloud.pugmg.cinetheatrosys.controle.exceptions.ExceptionSistema;
import com.rhcloud.pugmg.cinetheatrosys.controle.uteis.Hashs;
import com.rhcloud.pugmg.cinetheatrosys.controle.uteis.paginacao.GenericDataTables;
import com.rhcloud.pugmg.cinetheatrosys.dao.daobase.GenericDao;
import com.rhcloud.pugmg.cinetheatrosys.modelo.TipoUsuario;
import com.rhcloud.pugmg.cinetheatrosys.modelo.Usuario;
import com.rhcloud.pugmg.cinetheatrosys.modelo.repositorios.UsuarioRepository;

/**
 * Dao para manipular os usuários do banco
 * @author Daniel Severo Estrázulas
 */
@Component
class DaoUsuario extends GenericDao<Usuario> implements UsuarioRepository {

	public Usuario getUsuarioBanco(String login) 
	{
		Criteria critlogin = this.getSession().createCriteria(Usuario.class);

		critlogin.add(Restrictions.or(
				Restrictions.eq("email",login), 
				Restrictions.eq("login", login)
				));
		
		return (Usuario) critlogin.uniqueResult();
	}
	
	public Usuario autenticacao( String senhadigitada, String logindigitado,Usuario usuarioBanco) throws ExceptionSistema{
		Criteria critlogin = this.getSession().createCriteria(Usuario.class);

		critlogin.add(Restrictions.or(
				Restrictions.eq("email",logindigitado), 
				Restrictions.eq("login",logindigitado)
				));
				
		String hashComSalt = Hashs.getSHA256(senhadigitada+usuarioBanco.getSalt());
		
		critlogin.add(Restrictions.eq("senha", hashComSalt));
		
		Usuario usuario =  (Usuario) critlogin.uniqueResult();
		
		if(usuario == null)
		{
			throw new ExceptionSistema("daouser.validaAutenticacaoExterna.dadosinvalidos");
		}else{
			return usuario;
		}
		
	}
	
	/**
	 * Salva um novo usuário no banco, sem permissão inicialmente
	 * @param usuario
	 */
	public void saveNovoAcesso(Usuario usuario, TipoUsuario tipo) {
		usuario.setTipo(tipo);
		usuario.setAtivo(true);
		this.save(usuario);
	}

	/**
	 * Atualiza dados de cadastro do usuário, exceto campos de acesso e segurança
	 * @param usuario
	 */
	public Usuario gravaDados(Usuario usuario) {
		//para ativar o usuário no INSERT, não vai funcionar no UPDATE propositalmente a propriedade está anotada como updatable false para evitar injection via form html
		usuario.setAtivo(true);
		this.saveOrUpdate(usuario);
		return this.get(usuario.getId());
	}

	/**
	 * Retorna o usuário pelo email digitado
	 * @param email
	 * @return usuário persistido no banco
	 */
	public Usuario getUsuarioPeloEmail(String email) {
		return (Usuario) this.getSession().createCriteria(Usuario.class).add(Restrictions.eq("email",email)).uniqueResult();
	}

	@Override
	public Usuario getUsuarioBancoPorId(int id) {
		return (Usuario) this.getSession().createCriteria(Usuario.class).add(Restrictions.eq("id", id)).uniqueResult();
	}
	@Override
	public long quantidadeDeUsuarios() {
		return super.totalRecords("id", null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> usuariosComPaginacao(String coluna,
			Map<String, Object> parametersDataTable, GenericDataTables<Usuario> usrDataTable) {

		String sSearch = (String) parametersDataTable.get("allSearch");
		int iDisplayStart = (Integer) parametersDataTable.get("iDisplayStart");
		int iDisplayLength = (Integer) parametersDataTable.get("iDisplayLength");
	
		String fdSortCol = usrDataTable.getColumnNameById(Integer.parseInt((String) ((ArrayList<Object>) parametersDataTable.get("fdSortCol")).get(0)));
		String fdSortDir = (String) ((ArrayList<Object>) parametersDataTable.get("fdSortDir")).get(0);
		
		List<Usuario> users = (sSearch != null && !sSearch.isEmpty()) ? (List<Usuario>) this.findIntervalByAttribute("nome", sSearch, iDisplayStart, iDisplayLength, fdSortDir, fdSortCol,null ) : 
					(List<Usuario>) this.findTheRange(iDisplayStart, iDisplayLength,fdSortDir,fdSortCol,null);
		
		return users;
	}

	@Override
	public boolean usuarioJaExiste(Usuario elaborador) {
		Criterion crit = Restrictions.or(Restrictions.eq("login", elaborador.getLogin()),Restrictions.eq("email", elaborador.getEmail()),Restrictions.eq("cpf", elaborador.getCpf()));
		Criteria criteria  = this.getSession().createCriteria(Usuario.class).add(crit);
		return (criteria.uniqueResult() != null);
	}
	
	@Override
	public Usuario getUsuario(String login, String email, String cpf) {
		return (Usuario) this.getSession().createCriteria(Usuario.class).add(Restrictions.or(Restrictions.eq("login", login),Restrictions.eq("email", email),Restrictions.eq("cpf", cpf))).uniqueResult();
	}
	
	@Override
	public void setCredencial(Usuario usuario,String hash, String salt) {
		Query query = this.getSession().createQuery("UPDATE User u SET u.senha = :pSenha, u.salt = :pSalt WHERE u = :pUsuario ");
		query.setParameter("pSenha", hash);
		query.setParameter("pSalt", salt);
		query.setParameter("pUsuario", usuario);
		
		query.executeUpdate();
	}




	
}
