package br.shiki.java.spring.boot.template.dao.base;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * [IT01 - UC01]
 * 
 * @author guilhermequeiroz
 *
 * @param <T> Classe do tipo T que representa uma entidade.
 */
@Repository
public interface BaseDAO<T> extends CrudRepository<T, Long> {
	
	/**
	 * Realiza a busca de todos os registros da entidade 
	 * ordenada por ID ascendente.
	 * @return Retorna uma lista da entidade ordenada.
	 */
	List<T> findAllByOrderByIdAsc();
	
	/**
	 * 
	 * @param pageable
	 * @return
	 */
//	Page<T> findAllPaginated(Pageable pageable);

}
