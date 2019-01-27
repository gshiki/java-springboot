package br.shiki.java.spring.boot.template.controller;

import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.shiki.java.spring.boot.template.controller.base.EntityController;
import br.shiki.java.spring.boot.template.dto.UserDTO;
import br.shiki.java.spring.boot.template.model.User;


/**
 * 
 * @author Shiki
 *
 */
@RestController
@RequestMapping("${routes.user}")
@PropertySource("classpath:/config/routes.properties")
public class UserController extends EntityController<User, UserDTO> {
	
	@ResponseBody
    @GetMapping(value="${routes.user.list}", produces="application/json;charset=UTF-8")
	public ResponseEntity<?> list() {
		return super.list();
	}
	
}
