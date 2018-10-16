package com.kepler.api.modulos.pessoa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kepler.api.system.base.KeplerController;

@RestController
@RequestMapping(value = "pessoa")
public class PessoaController extends KeplerController<Pessoa> {

	private PessoaService service;

	@Autowired
	public PessoaController(PessoaService service) {
		this.service = service;
	}

	@Override
	public PessoaService getService() {
		return service;
	}
}
