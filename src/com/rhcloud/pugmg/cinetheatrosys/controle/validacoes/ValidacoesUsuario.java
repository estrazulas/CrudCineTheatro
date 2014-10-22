package com.rhcloud.pugmg.cinetheatrosys.controle.validacoes;

import com.rhcloud.pugmg.cinetheatrosys.controle.exceptions.ExceptionSistema;
import com.rhcloud.pugmg.cinetheatrosys.modelo.Estoria;
import com.rhcloud.pugmg.cinetheatrosys.modelo.Usuario;
import com.rhcloud.pugmg.cinetheatrosys.modelo.auxiliares.UsuarioWeb;

public class ValidacoesUsuario {
	
	public static void verificaUsuarioDadosParaRecuperacao(Usuario usuarioBd, String cpf) throws ExceptionSistema {
		
		if(usuarioBd== null || !usuarioBd.getCpf().trim().equals(cpf))
		{
			throw new ExceptionSistema("validacoesusuario.recuperar.cpfloginnaoconfere");
		}
	}
	
	public static void validarDonoEstoriaParaAlteracao(UsuarioWeb usuarioWeb, Estoria estoria)
			throws ExceptionSistema 
			{
		if(estoria.getUsuario().getId() != usuarioWeb.getUserLogado().getId()){
			throw new ExceptionSistema("controle.estoria.pgCadastroEstoria.naopermitido");
		}
	}

}
