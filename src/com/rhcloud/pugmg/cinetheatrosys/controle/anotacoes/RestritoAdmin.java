package com.rhcloud.pugmg.cinetheatrosys.controle.anotacoes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotação para restringir o acesso de determinado método HTTP aos
 * usuários admin
 * @author Daniel Severo Estrázulas
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RestritoAdmin {

}
