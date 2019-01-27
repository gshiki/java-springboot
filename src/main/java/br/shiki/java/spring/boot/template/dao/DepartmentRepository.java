package br.shiki.java.spring.boot.template.dao;

import org.springframework.stereotype.Repository;

import br.shiki.java.spring.boot.template.dao.base.BaseDAO;
import br.shiki.java.spring.boot.template.model.Department;


/**
 * 
 * @author Shiki
 *
 */
@Repository
public interface DepartmentRepository extends BaseDAO<Department> {

}
