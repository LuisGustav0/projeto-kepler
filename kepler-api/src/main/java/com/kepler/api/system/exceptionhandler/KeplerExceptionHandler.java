package com.kepler.api.system.exceptionhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class KeplerExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
																																HttpHeaders headers,
																																HttpStatus status,
																																WebRequest request) {
		List<Error> errors = getListOfErros("message.invalid", ex);
		return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
																																HttpHeaders headers,
																																HttpStatus status,
																																WebRequest request) {
		List<Error> errors = createListOfErros(ex.getBindingResult());		
		return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
	}

	private String getMessage(String field) {
		try {
			return messageSource.getMessage(field, null, LocaleContextHolder.getLocale());
		} catch (Exception ex) {
			return field;
		}
	}

	private List<Error> createListOfErros(BindingResult bindingResult) {
		List<Error> errors = new ArrayList<>();

		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			String msgUser = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			String msgDev = fieldError.toString();
			errors.add(new Error(msgUser, msgDev));
		}

		return errors;
	}

	private List<Error> getListOfErros(String fieldError, Exception ex) {
		String msgUser = getMessage(fieldError);
		String msgDev = ex != null ? ExceptionUtils.getRootCauseMessage(ex) : "";
		return Arrays.asList(new Error(msgUser, msgDev));
	}

	public static class Error {
		private String	msgUser;
		private String	msgDev;

		private Error(String msgUser, String msgDev) {
			this.msgUser = msgUser;
			this.msgDev = msgDev;
		}

		public String getMsgUser() {
			return msgUser;
		}

		public String getMsgDev() {
			return msgDev;
		}
	}
}
