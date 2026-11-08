package br.com.san.desafiojavajr.exceptions.handler;

import java.util.Date;

public record ErrorReponse(Date timestamp, int status, String message, String path) {
	
}
