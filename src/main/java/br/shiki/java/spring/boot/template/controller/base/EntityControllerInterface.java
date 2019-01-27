package br.shiki.java.spring.boot.template.controller.base;

import java.io.Serializable;

import org.springframework.http.ResponseEntity;

import br.shiki.java.spring.boot.template.dto.base.BaseDTO;


/**
 * 
 * @author Shiki
 *
 * @param <T>
 * @param <DT>
 */
public interface EntityControllerInterface<T extends Serializable, DT extends BaseDTO<T>> extends BaseControllerInterface {

	/**
	 * 
	 * @return
	 */
	ResponseEntity<?> list();
	
	/**
	 * 
	 * @param firstIndex
	 * @param amount
	 * @return
	 */
	ResponseEntity<?> paginate(int firstIndex, int amount);
	
	/**
	 * 
	 * @param dto
	 * @return
	 */
	ResponseEntity<?> register(DT dto);
	
	/**
	 * 
	 * @param entity
	 * @return
	 */
	ResponseEntity<?> register(T entity);
	
	/**
	 * 
	 * @param dto
	 * @return
	 */
	ResponseEntity<?> edit(DT dto);
	
	/**
	 * 
	 * @param entity
	 * @return
	 */
	ResponseEntity<?> edit(T entity);
	
	/**
	 * 
	 * @param dto
	 * @return
	 */
	ResponseEntity<?> delete(DT dto);
	
	/**
	 * 
	 * @param entity
	 * @return
	 */
	ResponseEntity<?> delete(T entity);
	
	/**
	 * 
	 * @return
	 */
	ResponseEntity<?> count();
	
}
