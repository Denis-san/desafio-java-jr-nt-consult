package br.com.san.desafiojavajr.exceptions;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends HttpApplicationException {
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(String msg) {
		super(msg);
	}
	
	public ResourceNotFoundException() {
		super("Recurso não encontrado!");
	}

	@Override
	public HttpStatus getStatus() {
		return HttpStatus.NOT_FOUND;
	}
	
}
