package com.rhcloud.pugmg.cinetheatrosys.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;

import com.rhcloud.pugmg.cinetheatrosys.controle.uteis.paginacao.GenericDataTables;
import com.rhcloud.pugmg.cinetheatrosys.dao.daobase.GenericDao;
import com.rhcloud.pugmg.cinetheatrosys.modelo.Estoria;
import com.rhcloud.pugmg.cinetheatrosys.modelo.auxiliares.UsuarioWeb;
import com.rhcloud.pugmg.cinetheatrosys.modelo.repositorios.EstoriaRepository;

/**
 * Dao para manipular os usuários do banco
 * @author Daniel Severo Estrázulas
 */
@Component
class DaoEstoria extends GenericDao<Estoria> implements EstoriaRepository {

	/**
	 * Atualiza dados de cadastro 
	 * @param estoria
	 */
	public Estoria gravaDados(Estoria estoria) {
		this.saveOrUpdate(estoria);
		return this.get(estoria.getId());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Estoria> retornaEstoriasDoBanco(UsuarioWeb usuarioWeb) {
		String hql ="";
		
		if(usuarioWeb.isAdmin()){
			hql = "SELECT e FROM Estoria e JOIN fetch e.usuario u ";
		}else{
			hql = "SELECT e FROM Estoria e JOIN fetch e.usuario u WHERE u.id = :pUsuario";
		}
		
		Query query = super.criaQuery(hql);
		query.setParameter("pUsuario", usuarioWeb.getUserLogado().getId());
		return (List<Estoria>) Collections.unmodifiableList(query.list());
	}

	public Estoria buscaEstoria(Integer id) {
		Criteria crit = super.criaCriteria();
		crit.add(Restrictions.eq("id", id));
		return (Estoria) crit.uniqueResult();
	}
	
	public void removeEstoria(Integer id){
		Query query = this.criaQuery("DELETE FROM Estoria e WHERE e.id = :pEstoria ");
		query.setParameter("pEstoria", id);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Estoria> estoriasComPaginacao(String string,
			Map<String, Object> parametersDataTable,
			GenericDataTables<Estoria> usrDataTable) {

		String sSearch = (String) parametersDataTable.get("allSearch");
		int iDisplayStart = (Integer) parametersDataTable.get("iDisplayStart");
		int iDisplayLength = (Integer) parametersDataTable.get("iDisplayLength");
	
		String fdSortCol = usrDataTable.getColumnNameById(Integer.parseInt((String) ((ArrayList<Object>) parametersDataTable.get("fdSortCol")).get(0)));
		String fdSortDir = (String) ((ArrayList<Object>) parametersDataTable.get("fdSortDir")).get(0);
		
		List<Estoria> estorias = (sSearch != null && !sSearch.isEmpty()) ? (List<Estoria>) this.findIntervalByAttribute("nome", sSearch, iDisplayStart, iDisplayLength, fdSortDir, fdSortCol,null ) : 
					(List<Estoria>) this.findTheRange(iDisplayStart, iDisplayLength,fdSortDir,fdSortCol,null);
		
		return estorias;
	}

	@Override
	public long quantidadeDeEstorias() {
		return super.totalRecords("id",null);
	}
	


}
