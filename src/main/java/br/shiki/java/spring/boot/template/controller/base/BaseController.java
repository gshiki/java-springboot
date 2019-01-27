package br.shiki.java.spring.boot.template.controller.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.shiki.java.spring.boot.template.util.Messages;
import br.shiki.java.spring.boot.template.util.Util;


/**
 * 
 * @author Shiki
 *
 */
public abstract class BaseController implements BaseControllerInterface {
	
	@Autowired
	protected Messages msg;
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	protected String t(String key) {
		return msg.get(key);
	}
	
	/**
	 * 
	 * @param key
	 * @param arguments
	 * @return
	 */
	protected String t(String key, Object... arguments) {
		return msg.get(key, arguments);
	}

	/* ************************************************************* */
    /* 							SUCESSOS							 */
	@Override
	public ResponseEntity<String> respondWithSuccess() {
		return ResponseEntity.ok(t("msg.success"));
	}

	@Override
	public ResponseEntity<Map<String, String>> respondWithSuccess(String message) {
		return ResponseEntity.ok().body(Collections.singletonMap("msg", message));
	}

	@Override
	public ResponseEntity<Map<String, Object>> respondWithSuccessAndObject(String key, Object object) {
		return ResponseEntity.ok().body(Collections.singletonMap(key, object));
	}

	@Override
	public ResponseEntity<Map<String, List<?>>> respondWithSuccessAndList(String key, List<? extends Object> objects) {
		return ResponseEntity.ok().body(Collections.singletonMap(key, objects));
	}

	/* ************************************************************* */
    /* 							  ERROS								 */
	@Override
	public ResponseEntity<String> respondWithError() {
		return ResponseEntity.badRequest().body(t("msg.error"));
	}

	@Override
	public ResponseEntity<Map<String, List<Map<String, Object>>>> respondWithError(String message) {
		Map<String, Object> error;
		List<Map<String, Object>> errors = new ArrayList<Map<String, Object>>();
		
		error = new HashMap<String, Object>();
		error.put("type", "error");
		error.put("msg", message);
		errors.add(error);
		
		return ResponseEntity.badRequest().body(Collections.singletonMap("errors", errors));
	}

	@Override
	public ResponseEntity<Map<String, List<Map<String, Object>>>> respondWithBindingErrors(BindingResult bindingResult) {
		Map<String, Object> error;
		List<Map<String, Object>> errors = new ArrayList<Map<String, Object>>();
		
		error = new HashMap<String, Object>();
		error.put("type", "fields");
		error.put("msgs", Util.convertErrorsToJSON(bindingResult.getAllErrors()));
		errors.add(error);
		
		return ResponseEntity.badRequest().body(Collections.singletonMap("errors", errors));
	}

	/* ************************************************************* */
    /* 							 EXCECOES							 */
	@Override
	@ExceptionHandler( { Exception.class } )
	public ResponseEntity<Map<String, List<Map<String, Object>>>> respondWhenException(Exception e) {
		Map<String, Object> error;
		List<Map<String, Object>> errors = new ArrayList<Map<String, Object>>();
		
		error = new HashMap<String, Object>();
		error.put("type", "error");
		error.put("msg", t("exception.erro.operacao"));
		errors.add(error);
		
		error = new HashMap<String, Object>();
		error.put("type", "exception");
		error.put("e-msg", e.getMessage());
		errors.add(error);
		
		return ResponseEntity.badRequest().body(Collections.singletonMap("errors", errors));
	}

	@Override
	@ExceptionHandler( { AccessDeniedException.class } )
	public ResponseEntity<Map<String, List<Map<String, Object>>>> respondWhenAccessDeniedException(Exception e) {
		Map<String, Object> error;
		List<Map<String, Object>> errors = new ArrayList<Map<String, Object>>();
		
		error = new HashMap<String, Object>();
		error.put("type", "error");
		error.put("msg", t("exception.erro.operacao"));
		errors.add(error);
		
		error = new HashMap<String, Object>();
		error.put("type", "exception");
		error.put("e-msg", e.getMessage());
		errors.add(error);
		
		return ResponseEntity.badRequest().body(Collections.singletonMap("errors", errors));
	}

	@Override
	@ExceptionHandler( { EmptyResultDataAccessException.class } )
	public ResponseEntity<Map<String, List<Map<String, Object>>>> respondWhenEmptyResultDataAccessException(EmptyResultDataAccessException e) {
		Map<String, Object> error;
		List<Map<String, Object>> errors = new ArrayList<Map<String, Object>>();
		
		error = new HashMap<String, Object>();
		error.put("type", "error");
		error.put("msg", t("exception.erro.operacao"));
		errors.add(error);
		
		error = new HashMap<String, Object>();
		error.put("type", "exception");
		error.put("e-msg", e.getMessage());
		errors.add(error);
		
		return ResponseEntity.badRequest().body(Collections.singletonMap("errors", errors));
	}

	@Override
	@ExceptionHandler( { InvalidDataAccessApiUsageException.class } )
	public ResponseEntity<Map<String, List<Map<String, Object>>>> respondWhenInvalidDataAccessApiUsageException(InvalidDataAccessApiUsageException e) {
		Map<String, Object> error;
		List<Map<String, Object>> errors = new ArrayList<Map<String, Object>>();
		
		error = new HashMap<String, Object>();
		error.put("type", "error");
		error.put("msg", t("exception.erro.operacao"));
		errors.add(error);
		
		error = new HashMap<String, Object>();
		error.put("type", "exception");
		error.put("e-msg", e.getMessage());
		errors.add(error);
		
		return ResponseEntity.badRequest().body(Collections.singletonMap("errors", errors));
	}

}
