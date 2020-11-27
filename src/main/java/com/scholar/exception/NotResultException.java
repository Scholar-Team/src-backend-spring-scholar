package com.scholar.exception;

public class NotResultException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public NotResultException(String mensagem) {
		super(mensagem);
	}
	
	public NotResultException() {
		super("Nenhum resultado encontrado.");
	}
	
}
