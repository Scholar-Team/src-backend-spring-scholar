package com.scholar.exception;

public class ImageNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public ImageNotFoundException(String mensagem) {
		super(mensagem);		
	}

	public ImageNotFoundException(Long id) {
		this(String.format("Não existe uma imagem com código %d", id));
	}
}
