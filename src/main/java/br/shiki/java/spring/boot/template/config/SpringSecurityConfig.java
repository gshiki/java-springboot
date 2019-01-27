package br.shiki.java.spring.boot.template.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;

import br.shiki.java.spring.boot.template.secutiry.JwtAuthenticationEntryPoint;
import br.shiki.java.spring.boot.template.secutiry.JwtTokenUtil;
import br.shiki.java.spring.boot.template.secutiry.filter.CorsFilter;
import br.shiki.java.spring.boot.template.secutiry.filter.JwtAuthorizationTokenFilter;
import br.shiki.java.spring.boot.template.secutiry.service.JwtUserDetailsService;


/**
 * 
 * @author Shiki
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
@PropertySource({
	"classpath:/config/jwt.properties", 
	"classpath:/config/routes.properties"
})
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;
	
	@Autowired
    private JwtAuthenticationEntryPoint authoriztionEntryPoint;
	
    @Value("${jwt.header}")
    private String requestHeader;
    
    @Value("${jwt.token.header}")
    private String tokenHeader;
    
    @Value("${routes.login}")
    private String authenticationPath;
    
    @Value("${routes.public}")
    private String publicPath;
    
    @Value("$routes.email}")
    private String mailPath;
    
    @Value("${routes.bcrypt}")
    private String bcryptPath;
    
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }
	
	@Override
	protected void configure(HttpSecurity httpSercurity) throws Exception {
		httpSercurity
			// Retira a seguranca CROSS RESOURCE
			.csrf().disable()
			// Tratamento de excecao para acessos nao autorizados
			.exceptionHandling().authenticationEntryPoint(authoriztionEntryPoint)
			.and()
			// Retira a criacao de sessao
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
			
            .authorizeRequests()
			// Recursos publicos
			.antMatchers(publicPath, mailPath, bcryptPath).permitAll()
			// Autenticacao em todos os outros recursos
			.anyRequest().authenticated();
		
		httpSercurity.addFilterBefore(
				new JwtAuthorizationTokenFilter(tokenHeader, requestHeader, jwtTokenUtil, jwtUserDetailsService), 
				UsernamePasswordAuthenticationFilter.class);
		httpSercurity.addFilterBefore(
				new CorsFilter(), 
				SecurityContextPersistenceFilter.class);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.POST, authenticationPath);
	}
	
}
