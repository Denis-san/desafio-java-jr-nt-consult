package br.com.san.desafiojavajr.exceptions.handler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import java.util.Date;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.san.desafiojavajr.exceptions.HttpApplicationException;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class HandlerAppExceptions {
	 private static final Logger log = LoggerFactory.getLogger(HandlerAppExceptions.class);


	@ExceptionHandler(HttpApplicationException.class)
	public ResponseEntity<ErrorReponse> handleHttpApplicationException(HttpApplicationException ex,
			HttpServletRequest request) {

		ErrorReponse errorResponse = new ErrorReponse(
				new Date(),
				ex.getStatus().value(),
				ex.getMessage(),
				request.getServletPath()
			);
		
		log.warn(ex.getMessage());

		return new ResponseEntity<ErrorReponse>(errorResponse, ex.getStatus());
	}
	
	
	
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorReponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
			HttpServletRequest request) {
		
		String validationErrors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

		ErrorReponse errorResponse = new ErrorReponse(
				new Date(),
				BAD_REQUEST.value(),
				validationErrors,
				request.getServletPath()
			);

		return new ResponseEntity<ErrorReponse>(errorResponse, BAD_REQUEST);
	}
	

	
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorReponse> handleGenericExceptions(Exception ex, HttpServletRequest request) {

		ErrorReponse errorResponse = new ErrorReponse(
				new Date(),
				INTERNAL_SERVER_ERROR.value(),
				"Houve um erro interno ao realizar a operação",
				request.getServletPath()
			);
		
		log.error("Exceção não tratada: ", ex);

		return new ResponseEntity<ErrorReponse>(errorResponse, INTERNAL_SERVER_ERROR);
	}
	
}
