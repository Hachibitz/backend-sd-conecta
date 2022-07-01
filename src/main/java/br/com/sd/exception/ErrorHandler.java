package br.com.sd.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ErrorHandler {
	
	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<MessageError> handlerUnauthorizedException(final UnauthorizedException exc, final WebRequest request){
		return new ResponseEntity<>(getMessage(exc, String.valueOf(HttpStatus.UNAUTHORIZED.value())), HttpStatus.UNAUTHORIZED);
	}
	
	private static MessageError getMessage(Exception e, String codigo) {
        MessageError error = new MessageError();
        error.setCodigo(codigo);
        error.setMensagem(e.getMessage());
        return error;
    }
	
}
