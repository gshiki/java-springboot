package br.shiki.java.spring.boot.template.secutiry.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import br.shiki.java.spring.boot.template.secutiry.JwtTokenUtil;


/**
 * 
 * @author Shiki
 *
 */
public class JwtAuthorizationTokenFilter extends OncePerRequestFilter {
	
	private String tokenPrefix;
	private String requestPrefix;
	private JwtTokenUtil jwtTokenUtil;
	private UserDetailsService userDetailsService;
	
	public JwtAuthorizationTokenFilter(String tokenPrefix, String requestPrefix, JwtTokenUtil jwtTokenUtil, UserDetailsService userDetailsService) {
		this.tokenPrefix = tokenPrefix;
		this.requestPrefix = requestPrefix;
		this.jwtTokenUtil = jwtTokenUtil;
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException 
	{
		String tokenHeader = request.getHeader(this.requestPrefix);

        String username = null;
        String authToken = null;
        
        if (tokenHeader != null && tokenHeader.startsWith(this.tokenPrefix)) {
            authToken = tokenHeader.substring(this.tokenPrefix.length());
            
            try {
                username = jwtTokenUtil.getUsernameFromToken(authToken);
            } catch (Exception e) {
                /*logger.warn("Not a valid token.", e);*/
            }
        } else {
            /*logger.warn("Problem with header.");*/
        }
        /*logger.debug("Checking user '{}'", username);*/
        
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        	
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                
                /*logger.info("User authorized '{}'", username);*/
                
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        chain.doFilter(request, response);
	}
	
}
