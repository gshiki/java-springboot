package br.shiki.java.spring.boot.template.secutiry.controller;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.shiki.java.spring.boot.template.secutiry.JwtAuthenticationRequest;
import br.shiki.java.spring.boot.template.secutiry.JwtAuthenticationResponse;
import br.shiki.java.spring.boot.template.secutiry.JwtTokenUtil;
import br.shiki.java.spring.boot.template.util.Messages;


/**
 * 
 * @author Shiki
 *
 */
@RestController
@PropertySource("classpath:/config/routes.properties")
public class AuthenticationController {
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
    private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	@Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;
	
	@Autowired
	protected Messages msg;
	
	/**
	 * Metodo que trata a requisicao de autenticacao do usuario.
	 * @param authenticationRequest
	 * @return JSON - Token de acesso.
	 */
	@PostMapping(value="${routes.login}")
	public ResponseEntity<?> requestAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) 
	{
		try {				
			authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(msg.get("msg.error.login.user.wrong.password"));
		}

        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        
        JwtAuthenticationResponse response = new JwtAuthenticationResponse();
        
        response.setToken(jwtTokenUtil.generateToken(userDetails));
        
        return new ResponseEntity<JwtAuthenticationResponse>(response, HttpStatus.OK);
	}
	
	/**
     * Metodo que invalida a sessao do usuario.
     * @param authenticationRequest
     * @return JSON - Resposta positiva caso consiga realiza a invalidade da sessao.
     */
    @PostMapping(value="${routes.logout}")
    public ResponseEntity<?> removeAuthenticationToken(HttpServletRequest request, HttpServletResponse response) {

    	HttpSession session = request.getSession(false);
    	
        SecurityContextHolder.clearContext();
        
        session = request.getSession(false);
        
        if(session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok(msg.get("msg.success.logout"));
    }

	/* ************************************************************* */
    /* 						METODOS PRIVADOS						 */
	/**
     * Realiza a tentativa de autenticar o usuario, caso nao consiga,
     * dispara uma Exception.
     * @param username
     * @param password
     */
    private void authenticate(String username, String password) throws NullPointerException {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }
	
}
