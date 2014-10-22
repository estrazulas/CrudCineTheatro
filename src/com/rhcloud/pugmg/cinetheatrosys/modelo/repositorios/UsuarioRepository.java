package com.rhcloud.pugmg.cinetheatrosys.modelo.repositorios;

import java.util.List;
import java.util.Map;

import com.rhcloud.pugmg.cinetheatrosys.controle.exceptions.ExceptionSistema;
import com.rhcloud.pugmg.cinetheatrosys.controle.uteis.paginacao.GenericDataTables;
import com.rhcloud.pugmg.cinetheatrosys.modelo.TipoUsuario;
import com.rhcloud.pugmg.cinetheatrosys.modelo.Usuario;

public interface UsuarioRepository {
	Usuario getUsuarioBancoPorId(int id);
	Usuario getUsuarioBanco(String email);
	Usuario autenticacao( String senhadigitada, String logindigitado, Usuario usuarioBanco) throws ExceptionSistema;
	Usuario saveNovoAcesso(Usuario usuario, TipoUsuario tipo);
	Usuario gravaDados(Usuario usuario);
	long quantidadeDeUsuarios();
	List<Usuario> usuariosComPaginacao(String coluna,
			Map<String, Object> parametersDataTable, GenericDataTables<Usuario> usrDataTable);
	boolean usuarioJaExiste(Usuario elaborador);
	public Usuario getUsuario(String email, String cpf) ;
	void setCredencial(Usuario usuario, String hash, String salt);
}
