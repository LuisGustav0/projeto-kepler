package com.kepler.api.system.base;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kepler.api.system.model.KeplerEntity;

public abstract class KeplerController<T extends KeplerEntity> {

	protected abstract KeplerService<T> getService();

	protected void addHeaderLocationForCreation(HttpServletResponse response, Long id) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(id).toUri();
		response.setHeader("Location", uri.toASCIIString());
	}

	@PostMapping
	public ResponseEntity<T> create(@RequestBody @Valid T entity, HttpServletResponse response) {
		entity = getService().create(entity);

		addHeaderLocationForCreation(response, entity.getCodigo());

		return ResponseEntity.status(HttpStatus.CREATED).body(entity);
	}
	
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public ResponseEntity<Void> delete(@PathVariable Long id) {
      getService().delete(id);

      return ResponseEntity.status(HttpStatus.OK).build();
  }

	@GetMapping("/{id}")
	public ResponseEntity<T> findById(@PathVariable Long id) {
		Optional<T> entity = getService().findById(id);
		return entity != null ? ResponseEntity.ok(entity.get()) : ResponseEntity.notFound().build();
	}

	@GetMapping
	public List<T> findAll() {
		return getService().findAll();
	}
}
