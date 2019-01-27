package br.shiki.java.spring.boot.template.dao;

import org.springframework.stereotype.Repository;

import br.shiki.java.spring.boot.template.dao.base.BaseDAO;
import br.shiki.java.spring.boot.template.model.Profile;


/**
 * 
 * @author Shiki
 *
 */
@Repository
public interface ProfileRepository extends BaseDAO<Profile> {

}
