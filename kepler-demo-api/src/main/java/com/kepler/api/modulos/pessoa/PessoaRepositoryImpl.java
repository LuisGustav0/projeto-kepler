package com.kepler.api.modulos.pessoa;

import javax.persistence.EntityManager;

import com.kepler.api.system.base.KeplerRepositoryImpl;

public class PessoaRepositoryImpl extends KeplerRepositoryImpl<Pessoa> implements PessoaRepositoryQuery {

	public PessoaRepositoryImpl(EntityManager entityManager) {
		super(entityManager);
	}
}