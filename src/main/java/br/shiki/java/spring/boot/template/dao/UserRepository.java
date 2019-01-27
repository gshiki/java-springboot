package br.shiki.java.spring.boot.template.dao;

import org.springframework.stereotype.Repository;

import br.shiki.java.spring.boot.template.dao.base.BaseDAO;
import br.shiki.java.spring.boot.template.model.User;


/**
 * 
 * @author Shiki
 *
 */
@Repository
public interface UserRepository extends BaseDAO<User> {
	
	/**
	 * 
	 * @param login
	 * @return
	 */
	User findByLogin(String login);
	
	/**
	 * 
	 * @param email
	 * @return
	 */
	User findByEmail(String email);
	
}
