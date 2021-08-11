package br.com.gral.anuncio.exception;

import org.springframework.http.HttpStatus; 
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class RecursoJaExisteException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private static final String message = "Recurso com id %s ja existe.";

	public RecursoJaExisteException () {
		super();
	}

	public RecursoJaExisteException (String str) {
		super(String.format(message , str));
	}	
}
