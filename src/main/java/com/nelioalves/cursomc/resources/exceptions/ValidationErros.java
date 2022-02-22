package com.nelioalves.cursomc.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationErros extends StandError {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> lista = new ArrayList<>();
	
	public ValidationErros(Integer status, String mensagem, Long timeStamp) {
		super(status, mensagem, timeStamp);
		 
	}

	/**
	 * @return the lista
	 */
	public List<FieldMessage> getErros() {
		return lista;
	}

	/**
	 * 
	 * @param fieldName
	 * @param fieldMessage
	 */
	public void setErros(String fieldName, String fieldMessage) {
		this.lista.add(new FieldMessage(fieldName, fieldMessage));
	}

	

}
