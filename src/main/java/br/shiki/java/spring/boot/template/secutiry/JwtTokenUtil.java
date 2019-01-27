package br.shiki.java.spring.boot.template.secutiry;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import br.shiki.java.spring.boot.template.secutiry.model.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClock;


/**
 * 
 * @author Shiki
 *
 */
@Component
@PropertySource("classpath:/config/jwt.properties")
public class JwtTokenUtil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2659239754017983488L;
	
	static final String CLAIM_KEY_USERNAME = "sub";
    static final String CLAIM_KEY_CREATED = "iat";
    
    private Clock clock = DefaultClock.INSTANCE;
    
    @Value("${jwt.secret}")
    private String secret;
    
    @Value("${jwt.expiration.time}")
    private Long expiration;
    
    public String getUsernameFromToken(String token) {
    	Claims claims = getAllClaimsFromToken(token);
        return claims.getSubject();
    }

    public Date getIssuedAtDateFromToken(String token) {
    	Claims claims = getAllClaimsFromToken(token);
        return claims.getIssuedAt();
    }

    public Date getExpirationDateFromToken(String token) {
    	Claims claims = getAllClaimsFromToken(token);
        return claims.getExpiration();
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
        		.setSigningKey(secret)
        		.parseClaimsJws(token)
        		.getBody();
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        Date createdDate = clock.now();
        Date expirationDate = new Date( createdDate.getTime() + expiration );

        return Jwts.builder()
        		.setClaims(claims)
        		.setSubject(subject)
        		.setIssuedAt(createdDate)
        		.setExpiration(expirationDate)
        		.signWith(SignatureAlgorithm.HS512, secret)
        		.compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        JwtUser user = (JwtUser) userDetails;
        
        String username = getUsernameFromToken(token);
//      Date expirationDate = getExpirationDateFromToken(token);
        
        return ( username.equals(user.getUsername()) );
    }

}
