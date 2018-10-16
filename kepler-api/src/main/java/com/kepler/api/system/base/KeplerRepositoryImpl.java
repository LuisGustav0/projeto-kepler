package com.kepler.api.system.base;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;

import org.springframework.data.repository.NoRepositoryBean;

import com.kepler.api.system.model.KeplerEntity;

@NoRepositoryBean
public class KeplerRepositoryImpl<T extends KeplerEntity> {

	private Class<T> entityClass;

	private EntityManager entityManager;

	public KeplerRepositoryImpl(EntityManager entityManager) {
      this.entityManager = entityManager;
  }

	public EntityManager getEntityManager() {
		return this.entityManager;
	}
	
	@SuppressWarnings({ "unchecked" })
	public Class<T> getEntityClass() {
		if (entityClass != null)
			return entityClass;

		ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();

		this.entityClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];

		return entityClass;
	}

	public String getEntityName() {
		return getEntityClass().getSimpleName();
	}
}
