package com.rhcloud.pugmg.cinetheatrosys.controle.uteis;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;

/**
 * Componente para o framework poder criar/matar a sessão
 * @author Daniel Severo Estrázulas
 *
 */
@Component
public class CriadorSession implements ComponentFactory<Session> {
	private SessionFactory sessionFactory;
	private Session session;

	public CriadorSession(SessionFactory factory) {
		this.sessionFactory = factory;
	}

	@PostConstruct
	public void abre() {
		this.session = sessionFactory.openSession();
	}
		
	@PreDestroy
	public void fecha(){
		session.close();
	}

	public Session getInstance() {
		return session;
	}
}
