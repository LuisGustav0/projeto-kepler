package com.kepler.api.modulos.categoria;

import javax.persistence.EntityManager;

import com.kepler.api.system.base.KeplerRepositoryImpl;

public class CategoriaRepositoryImpl extends KeplerRepositoryImpl<Categoria> implements CategoriaRepositoryQuery {

	public CategoriaRepositoryImpl(EntityManager entityManager) {
		super(entityManager);
	}
}