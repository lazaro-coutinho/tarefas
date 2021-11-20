package br.com.lazaro.tarefas.handler;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.lazaro.tarefas.exceptions.TarefaNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ConstraintErrorView> handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest request) {
		
		List<String> constraints = new ArrayList<>();
		for (FieldError f : exception.getFieldErrors()) {
			constraints.add(f.getDefaultMessage());
		}
		
		ConstraintErrorView constraintErrorView = ConstraintErrorView.builder()
				.titulo("Requisição com campos inválidos")
				.mensagem("http://erros.tarefas.com/400")
				.status(400)
				.timestamp(System.currentTimeMillis())
				.constraints(constraints)
				.build();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(constraintErrorView);
	}
	
	@ExceptionHandler(TarefaNotFoundException.class)
	public ResponseEntity<ErrorView> handlerTarefaNotFoundException(TarefaNotFoundException exception, HttpServletRequest request) {
		ErrorView errorView = ErrorView.builder()
				.titulo(exception.getMessage())
				.mensagem("http://erros.tarefas.com/404")
				.status(404)
				.timestamp(System.currentTimeMillis())
				.build();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorView);
	}

}
