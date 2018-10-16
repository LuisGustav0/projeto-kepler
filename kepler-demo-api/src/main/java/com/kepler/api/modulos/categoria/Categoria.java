package com.kepler.api.modulos.categoria;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.kepler.api.system.model.KeplerEntity;

@Entity
@Table(name = "categoria")
public class Categoria extends KeplerEntity {
	private static final long serialVersionUID = 4734741452815549290L;

	@NotNull
	@Size(min = 1, max = 50)
	@Column(length = 50, unique = true, nullable = false)
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
