package br.shiki.java.spring.boot.template.dto.base;


/**
 * 
 * @author Shiki
 *
 * @param <T>
 */
public interface BaseDTOInterface<T> {

	/**
	 * 
	 * @return
	 */
	T toEntity();
	
	/**
	 * 
	 * @param entity
	 */
	void fillDTO(T entity);
	
	/**
	 * 
	 * @return
	 */
	boolean validate();
	
}
