package com.nelioalves.cursomc.services.exceptions;

public class DataIntegrityException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public DataIntegrityException(String erro) {
		super(erro);
	}
	
	public DataIntegrityException(String erro, Throwable cause) {
		super(erro, cause);
	}

}
