package com.nelioalves.cursomc.resources.exceptions;

import java.io.Serializable;

public class StandError implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer status;
	private String mensagem;
	private Long TimeStamp;
	
	
	public StandError(Integer status, String mensagem, Long timeStamp) {
		this.status = status;
		this.mensagem = mensagem;
		TimeStamp = timeStamp;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public String getMensagem() {
		return mensagem;
	}


	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}


	public Long getTimeStamp() {
		return TimeStamp;
	}


	public void setTimeStamp(Long timeStamp) {
		TimeStamp = timeStamp;
	}

}
