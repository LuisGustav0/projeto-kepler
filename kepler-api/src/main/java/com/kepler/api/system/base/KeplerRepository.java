package com.kepler.api.system.base;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.kepler.api.system.model.KeplerEntity;

@NoRepositoryBean
public interface KeplerRepository<T extends KeplerEntity, PK extends Serializable> extends CrudRepository<T, PK>, 
		KeplerRepositorySearch {
	
  EntityManager getEntityManager();

  Class<T> getEntityClass();

  String getEntityName();
}
