package br.shiki.java.spring.boot.template.controller.base;

import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;


/**
 * 
 * @author Shiki
 *
 */
public interface BaseControllerInterface {
	
	/**
	 * 
	 * @return
	 */
	ResponseEntity<String> respondWithSuccess();
	
	/**
	 * 
	 * @param message
	 * @return
	 */
	ResponseEntity<Map<String, String>> respondWithSuccess(String message);
	
	/**
	 * 
	 * @param key
	 * @param object
	 * @return
	 */
	ResponseEntity<Map<String, Object>> respondWithSuccessAndObject(String key, Object object);
	
	/**
	 * 
	 * @param key
	 * @param objects
	 * @return
	 */
	ResponseEntity<Map<String, List<?>>> respondWithSuccessAndList(String key, List<? extends Object> objects);
	
	/**
	 * 
	 * @return
	 */
	ResponseEntity<String> respondWithError();
	
	/**
	 * 
	 * @param message
	 * @return
	 */
	ResponseEntity<Map<String, List<Map<String, Object>>>> respondWithError(String message);
	
	/**
	 * 
	 * @param bindingResult
	 * @return
	 */
	ResponseEntity<Map<String, List<Map<String, Object>>>> respondWithBindingErrors(BindingResult bindingResult);

	/**
	 * 
	 * @param e
	 * @return
	 */
	ResponseEntity<Map<String, List<Map<String, Object>>>> respondWhenException(Exception e);
	
	/**
	 * 
	 * @param e
	 * @return
	 */
	ResponseEntity<Map<String, List<Map<String, Object>>>> respondWhenAccessDeniedException(Exception e);

	/**
	 * 
	 * @param e
	 * @return
	 */
	ResponseEntity<Map<String, List<Map<String, Object>>>> respondWhenEmptyResultDataAccessException(EmptyResultDataAccessException e);

	/**
	 * 
	 * @param e
	 * @return
	 */
	ResponseEntity<Map<String, List<Map<String, Object>>>> respondWhenInvalidDataAccessApiUsageException(InvalidDataAccessApiUsageException e);
	
}
