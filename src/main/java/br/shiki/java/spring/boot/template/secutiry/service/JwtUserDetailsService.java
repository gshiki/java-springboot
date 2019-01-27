package br.shiki.java.spring.boot.template.secutiry.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.shiki.java.spring.boot.template.dao.UserRepository;
import br.shiki.java.spring.boot.template.model.User;
import br.shiki.java.spring.boot.template.secutiry.factory.JwtUserFactory;
import br.shiki.java.spring.boot.template.util.Messages;


/**
 * 
 * @author Shiki
 *
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	protected Messages msg;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByLogin(username);
		
		if (user == null) {
			throw new UsernameNotFoundException(msg.get("msg.error.login.user.not.found"));
		} else {
			return JwtUserFactory.convert(user);
		}
	}
	
}
