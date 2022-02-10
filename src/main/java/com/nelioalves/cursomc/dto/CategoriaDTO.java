package com.nelioalves.cursomc.dto;

import com.nelioalves.cursomc.domain.Categoria;

public class CategoriaDTO {

	private Integer id;
	private String nome;
	
	public CategoriaDTO() {	}
	
	/**
	 * Construtor que converte Categoria em CategoriaDTO
	 * @param objCategoria
	 */
	public CategoriaDTO(Categoria objCategoria) {
		this.id = objCategoria.getId();
		this.nome = objCategoria.getNome();
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
