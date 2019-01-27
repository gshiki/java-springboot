package br.shiki.java.spring.boot.template.service.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.shiki.java.spring.boot.template.dao.base.BaseDAO;


/**
 * 
 * @author Shiki
 *
 * @param <T>
 */
@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class BaseService<T> {

	@Autowired
	BaseDAO<T> cioDAO;

	/* ************************************************************* */
    /* 						   LISTAGEM								 */
	/**
	 * 
	 * @return
	 */
	@Transactional(readOnly=true)
    public List<T> findAll() {
        return (List<T>) cioDAO.findAll();
    }
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws EmptyResultDataAccessException
	 */
	@Transactional(readOnly=true)
	public T findById(Long id) throws EmptyResultDataAccessException {
		return cioDAO.findById(id).orElseThrow( () -> new EmptyResultDataAccessException(1) );
	}
	
	/**
	 * 
	 */
//	@Transactional(readOnly=true)
//	public List<T> paginate(int page, int size) {
//		PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC);
//		
//		Page<T> entities = cioDAO.findAllPaginated(pageRequest);
//		
//		return entities.getContent();
//	}
	
	/**
	 * 
	 * @param entity
	 * @return
	 */
//	@Transactional(readOnly=true)
//	public List<T> paginate(int page, int size, String sortDir, String sort) {
//		PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.fromString(sortDir), sort);
//		
//		Page<T> entities = cioDAO.findAllPaginated(pageRequest);
//		
//		return entities.getContent();
//	}
	
	/* ************************************************************* */
    /* 						    SALVAR								 */
	/**
	 * 
	 * @param entity
	 * @return
	 */
	@Transactional
	public T save(T entity) {
		return cioDAO.save(entity);
	}
	
	/* ************************************************************* */
    /* 						    DELETAR								 */
	/**
	 * 
	 * @param entity
	 */
	@Transactional
	public void delete(T entity) {
		cioDAO.delete(entity);
	}
	
	/**
	 * 
	 * @param id
	 */
	@Transactional
	public void deleteById(Long id) {
		cioDAO.deleteById(id);
	}
	
	/* ************************************************************* */
    /* 						   CONTAGEM								 */
	/**
	 * 
	 * @return
	 */
    @Transactional(readOnly=true)
    public Long count() {
        return cioDAO.count();
    }
    
}
