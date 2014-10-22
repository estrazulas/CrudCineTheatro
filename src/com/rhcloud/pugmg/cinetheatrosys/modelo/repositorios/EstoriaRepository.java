package com.rhcloud.pugmg.cinetheatrosys.modelo.repositorios;

import java.util.List;
import java.util.Map;

import com.rhcloud.pugmg.cinetheatrosys.controle.uteis.paginacao.GenericDataTables;
import com.rhcloud.pugmg.cinetheatrosys.modelo.Estoria;
import com.rhcloud.pugmg.cinetheatrosys.modelo.auxiliares.UsuarioWeb;

public interface EstoriaRepository {
	
	Estoria gravaDados(Estoria estoria);

	List<Estoria> retornaEstoriasDoBanco(UsuarioWeb usuarioWeb);
	
	void removeEstoria(Integer id);
	
	Estoria buscaEstoria(Integer id);

	List<Estoria> estoriasComPaginacao(String string,
			Map<String, Object> parametersDataTable,
			GenericDataTables<Estoria> usrDataTable);

	long quantidadeDeEstorias();

}
