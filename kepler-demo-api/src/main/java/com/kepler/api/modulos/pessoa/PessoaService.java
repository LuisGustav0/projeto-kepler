package com.kepler.api.modulos.pessoa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kepler.api.system.base.KeplerService;

@Service
public class PessoaService extends KeplerService<Pessoa> {

	private PessoaRepository repository;

	@Autowired
	public PessoaService(PessoaRepository repository) {
		this.repository = repository;
	}

	@Override
	protected PessoaRepository getRepository() {
		return repository;
	}
}
