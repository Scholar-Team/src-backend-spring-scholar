package com.scholar.exception;

public class PersonNotFoundException extends EntityNotFoundException{

	private static final long serialVersionUID = 1L;

	public PersonNotFoundException(String mensagem) {
		super(mensagem);		
	}

	public PersonNotFoundException(Long id) {
		this(String.format("Não existe um cadastro de cliente com código %d", id));
	}
}
