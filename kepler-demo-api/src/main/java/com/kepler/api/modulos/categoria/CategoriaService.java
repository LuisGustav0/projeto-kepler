package com.kepler.api.modulos.categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kepler.api.system.base.KeplerService;

@Service
public class CategoriaService extends KeplerService<Categoria> {

	private CategoriaRepository repository;

	@Autowired
	public CategoriaService(CategoriaRepository repository) {
		this.repository = repository;
	}

	@Override
	protected CategoriaRepository getRepository() {
		return repository;
	}
}
