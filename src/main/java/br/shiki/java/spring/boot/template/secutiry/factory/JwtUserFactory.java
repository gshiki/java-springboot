package br.shiki.java.spring.boot.template.secutiry.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import br.shiki.java.spring.boot.template.model.Profile;
import br.shiki.java.spring.boot.template.model.User;
import br.shiki.java.spring.boot.template.secutiry.model.JwtUser;


/**
 * 
 * @author Shiki
 *
 */
public class JwtUserFactory {
	
	public static JwtUser convert(User user) {
		JwtUser jwtUser = new JwtUser(
				user.getId(), 
				user.getLogin(), 
				user.getEmail(), 
				user.getLogin(), 
				user.getPassword(), 
				mapGrantedAuthorities(user.getProfiles()), 
				user.isActive());
        return jwtUser;
    }

    private static List<GrantedAuthority> mapGrantedAuthorities(Set<Profile> profiles) {
    	List<GrantedAuthority> authorities = new ArrayList<>();
    	
    	for (Profile profile : profiles) {
			authorities.add( new SimpleGrantedAuthority( "ROLE_" + profile.getLabel().toUpperCase()) );
		}
        return authorities;
    }

}
