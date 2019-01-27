package br.shiki.java.spring.boot.template.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.shiki.java.spring.boot.template.dao.UserRepository;
import br.shiki.java.spring.boot.template.model.User;
import br.shiki.java.spring.boot.template.service.base.BaseService;


/**
 * 
 * @author Shiki
 *
 */
@Service
public class UserService extends BaseService<User> {
	
	@Autowired
	private UserRepository userRepository;
	
	/* ************************************************************* */
    /* 							BUSCAS								 */
	@Transactional(readOnly=true)
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
	
}
