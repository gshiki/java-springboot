package br.shiki.java.spring.boot.template.secutiry;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import br.shiki.java.spring.boot.template.util.Messages;


/**
 * 
 * @author Shiki
 *
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8071387835636682448L;
	
	@Autowired
	protected Messages msg;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException 
	{
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, msg.get("msg.error.not.allowed"));
	}
	
}
