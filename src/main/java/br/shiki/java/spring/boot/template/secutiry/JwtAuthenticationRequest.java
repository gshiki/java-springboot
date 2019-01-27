package br.shiki.java.spring.boot.template.secutiry;

import java.io.Serializable;

import lombok.Data;


/**
 * [IT01 - UC01]
 * Classe que representa a requisicao de uma 
 * autenticacao para disponibilizacao de token 
 * JWT.
 * 
 * @author guilhermequeiroz
 *
 */
@Data
public class JwtAuthenticationRequest implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7681767671406387851L;
	
	private String username;
	private String password;
	
}
