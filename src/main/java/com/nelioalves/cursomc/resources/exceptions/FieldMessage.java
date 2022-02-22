package com.nelioalves.cursomc.resources.exceptions;

import java.io.Serializable;

public class FieldMessage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String field;
	private String message;
	
	public FieldMessage() {}

	public FieldMessage(String field, String message) {
		super();
		this.field = field;
		this.message = message;
	}

	/**
	 * @return the field
	 */
	public String getField() {
		return field;
	}

	/**
	 * @param field the field to set
	 */
	public void setField(String field) {
		this.field = field;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	};
	
	

}
