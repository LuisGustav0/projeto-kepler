package com.kepler.api.modulos.categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kepler.api.system.base.KeplerController;

@RestController
@RequestMapping(value = "categoria")
public class CategoriaController extends KeplerController<Categoria> {

	private CategoriaService service;

	@Autowired
	public CategoriaController(CategoriaService service) {
		this.service = service;
	}

	@Override
	public CategoriaService getService() {
		return service;
	}
}
