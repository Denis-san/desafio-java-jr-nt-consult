package br.com.san.desafiojavajr.exceptions;

import org.springframework.http.HttpStatus;

public abstract class HttpApplicationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public HttpApplicationException(String msg) {
		super(msg);
	}

	public HttpApplicationException(String message, Throwable cause) {
		super(message, cause);
	}

	public abstract HttpStatus getStatus();

}
