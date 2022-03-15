package com.nelioalves.cursomc.dto;

import java.io.Serializable;

public class ClienteInputDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String email;
	private String cpfOuCnpj;
	private Integer tipo;
	
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;
	
	private String telefonePrincipal;
	private String telefoneOpcinalUm;
	private String telefoneOpcinalDois;
	
	private Integer cidadeId;
	
	public ClienteInputDTO() {
		
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

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the cpfOuCnpj
	 */
	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	/**
	 * @param cpfOuCnpj the cpfOuCnpj to set
	 */
	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	/**
	 * @return the tipo
	 */
	public Integer getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the logradouro
	 */
	public String getLogradouro() {
		return logradouro;
	}

	/**
	 * @param logradouro the logradouro to set
	 */
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * @return the complemento
	 */
	public String getComplemento() {
		return complemento;
	}

	/**
	 * @param complemento the complemento to set
	 */
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	/**
	 * @return the bairro
	 */
	public String getBairro() {
		return bairro;
	}

	/**
	 * @param bairro the bairro to set
	 */
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	/**
	 * @return the cep
	 */
	public String getCep() {
		return cep;
	}

	/**
	 * @param cep the cep to set
	 */
	public void setCep(String cep) {
		this.cep = cep;
	}

	/**
	 * @return the telefonePrincipal
	 */
	public String getTelefonePrincipal() {
		return telefonePrincipal;
	}

	/**
	 * @param telefonePrincipal the telefonePrincipal to set
	 */
	public void setTelefonePrincipal(String telefonePrincipal) {
		this.telefonePrincipal = telefonePrincipal;
	}

	/**
	 * @return the telefoneOpcinalUm
	 */
	public String getTelefoneOpcinalUm() {
		return telefoneOpcinalUm;
	}

	/**
	 * @param telefoneOpcinalUm the telefoneOpcinalUm to set
	 */
	public void setTelefoneOpcinalUm(String telefoneOpcinalUm) {
		this.telefoneOpcinalUm = telefoneOpcinalUm;
	}

	/**
	 * @return the telefoneOpcinalDois
	 */
	public String getTelefoneOpcinalDois() {
		return telefoneOpcinalDois;
	}

	/**
	 * @param telefoneOpcinalDois the telefoneOpcinalDois to set
	 */
	public void setTelefoneOpcinalDois(String telefoneOpcinalDois) {
		this.telefoneOpcinalDois = telefoneOpcinalDois;
	}

	/**
	 * @return the cidadeId
	 */
	public Integer getCidadeId() {
		return cidadeId;
	}

	/**
	 * @param cidadeId the cidadeId to set
	 */
	public void setCidadeId(Integer cidadeId) {
		this.cidadeId = cidadeId;
	}
}