package br.shiki.java.spring.boot.template.secutiry;

import java.io.Serializable;

import lombok.Data;


/**
 * [IT01 -UC01]
 * Classe que representa a resposta de uma requisicao de 
 * autenticacao JWT com um token de acesso.
 * 
 * @author guilhermequeiroz
 *
 */
@Data
public class JwtAuthenticationResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6290645810334707986L;
	
	private String token;
	
}
