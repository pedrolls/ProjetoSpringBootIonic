package com.nelioalves.cursomc.services.exceptions;

public class ObjectNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundException(String erro) {
		super(erro);
	}
	
	public ObjectNotFoundException(String erro, Throwable cause) {
		super(erro, cause);
	}

}
