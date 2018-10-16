package com.kepler.api.system.base;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.kepler.api.system.model.KeplerEntity;

public abstract class KeplerService<T extends KeplerEntity> {

	protected abstract KeplerRepository<T, Long> getRepository();

	public KeplerService() {
	}

	protected String getEntityName() {
		return getRepository().getEntityName();
	}

	public Optional<T> findById(Long id) {
		return getRepository().findById(id);
	}

	@Transactional
	public void delete(Long id) {
		delete(id, false, false);
	}

	@Transactional
	public void delete(Long id, boolean isIgnoreBeforeDelete, boolean isIgnoreAfterDelete) {
		Optional<T> entityOptional = findById(id);

		T entity = entityOptional
				.orElseThrow(() -> new EmptyResultDataAccessException("delete " + getEntityName() + " -> " + id, 1));

		getRepository().delete(entity);
	}

	@Transactional
	public T create(T entity) {
		entity = getRepository().save(entity);
		return entity;
	}

	public List<T> findAll() {
		return (List<T>) getRepository().findAll();
	}
}
