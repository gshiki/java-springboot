package br.shiki.java.spring.boot.template.secutiry.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;


/**
 * 
 * @author Shiki
 *
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@PropertySource("classpath:/config/config.properties")
public class CorsFilter extends OncePerRequestFilter {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("${config.security.cors.filter.allow.origin}")
	private String allowOrigin;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        logger.debug("CIO INFO - Processando requisicao '{}'", request.getRequestURL());

        response.setHeader("Access-Control-Allow-Origin", allowOrigin);
        response.setHeader("Access-Control-Allow-Methods", "HEAD, POST, PUT, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "X-PINGOTHER,Content-Type,X-Requested-With,x-xsrf-token,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        
        if ("OPTIONS".equals(request.getMethod())) {
        	response.setStatus(HttpServletResponse.SC_OK);
        } else {
        	chain.doFilter(request, response);
        }
    }
    
}
