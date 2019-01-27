package br.shiki.java.spring.boot.template.secutiry.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


/**
 * 
 * @author Shiki
 *
 */
@Data
public class JwtUser implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6121757803177695857L;
	
	private Long id;
	private String login;
	private String email;
	private String username;
	private String password;
	private final Collection<? extends GrantedAuthority> authorities;
    private final boolean enabled;
    
    public JwtUser(Long id, 
    		String login, 
    		String email, 
    		String username, 
    		String password, 
    		Collection<? extends GrantedAuthority> authorities, boolean enabled) 
    {
    	this.id = id;
    	this.login = login;
    	this.email = email;
    	this.username = username;
    	this.password = password;
    	this.authorities = authorities;
    	this.enabled = enabled;
	}
	
    @JsonIgnore
    @Override
	public String getPassword() {
		return password;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
    
}
