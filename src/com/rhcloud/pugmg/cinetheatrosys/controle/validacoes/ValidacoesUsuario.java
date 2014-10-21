package com.rhcloud.pugmg.cinetheatrosys.controle.validacoes;

import com.rhcloud.pugmg.cinetheatrosys.controle.exceptions.ExceptionSistema;
import com.rhcloud.pugmg.cinetheatrosys.modelo.Usuario;

public class ValidacoesUsuario {
	
	public static void verificaUsuarioDadosParaRecuperacao(Usuario usuarioBd, String cpf) throws ExceptionSistema {
		
		if(usuarioBd== null || !usuarioBd.getCpf().trim().equals(cpf))
		{
			throw new ExceptionSistema("validacoesusuario.recuperar.cpfloginnaoconfere");
		}
	}	
}
