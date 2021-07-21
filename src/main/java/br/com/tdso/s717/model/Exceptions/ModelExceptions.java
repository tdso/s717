package br.com.tdso.s717.model.Exceptions;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import br.com.tdso.s717.model.Exceptions.Negociais.ValidacaoException;

@RestControllerAdvice
public class ModelExceptions {
	
	//@Autowired
	//private MessageSource messageSource;
	
	@ExceptionHandler(DateTimeParseException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public String getError (DateTimeParseException exception) {
		return "Data não informada ou formato inválido >> " +  
				exception.getParsedString() + "O formato deve ser dd/mm/aaaa !! " ;
	}
	@ExceptionHandler(ValidacaoException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)	
	public String getError (ValidacaoException exception) {
		return exception.getMessage() ;
	}
	
	@ExceptionHandler(NumberFormatException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)	
	public String getError (NumberFormatException exception) {
		return "Valor inválido para a Quantidade !!" ;
	}
	
/*
	//@ExceptionHandler(MethodArgumentNotValidException.class)
	@ExceptionHandler(Exception.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	//public List<String> getError (MethodArgumentNotValidException exception) {
	public List<String> getError (Exception exception) {
		List<String> msgs = new ArrayList<>();
		//List<FieldError> erros = exception.getBindingResult().getFieldErrors();
		//erros.forEach(e -> {
		//	msgs.add(messageSource.getMessage(e, LocaleContextHolder.getLocale()));
		//});
		//return msgs;
		msgs.add(exception.getMessage());
		return msgs;
	}
	*/

}
