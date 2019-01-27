package br.shiki.java.spring.boot.template.controller;

import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.shiki.java.spring.boot.template.controller.base.EntityController;
import br.shiki.java.spring.boot.template.dto.DepartmentDTO;
import br.shiki.java.spring.boot.template.model.Department;


/**
 * 
 * @author Shiki
 *
 */
@RestController
@RequestMapping("${routes.department}")
@PropertySource("classpath:/config/routes.properties")
public class DepartmentController extends EntityController<Department, DepartmentDTO> {
	
	@ResponseBody
    @GetMapping(value="${routes.department.list}", produces="application/json;charset=UTF-8")
	public ResponseEntity<?> list() {
		return super.list();
	}
	
}
