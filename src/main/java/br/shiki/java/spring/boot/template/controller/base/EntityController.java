package br.shiki.java.spring.boot.template.controller.base;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import br.shiki.java.spring.boot.template.dto.base.BaseDTO;
import br.shiki.java.spring.boot.template.service.base.BaseService;


/**
 * 
 * @author Shiki
 *
 * @param <T>
 * @param <DT>
 */
public abstract class EntityController<T extends Serializable, DT extends BaseDTO<T>> extends BaseController implements EntityControllerInterface<T, DT> {
	
	@Autowired
	private BaseService<T> baseService;

	@Override
	public ResponseEntity<?> list() {
		List<T> entityList = baseService.findAll();
		
		return super.respondWithSuccessAndList("list", entityList);
	}

	@Override
	public ResponseEntity<?> paginate(int firstIndex, int amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> register(DT dto) {
		if (dto != null && dto.validate()) {
			baseService.save(dto.toEntity());
			
			return super.respondWithSuccess(super.t("msg.registration.success"));
		}
		return super.respondWithError(super.t("msg.registration.error"));
	}
	
	@Override
	public ResponseEntity<?> register(T entity) {
		if (entity != null) {
			baseService.save(entity);
			
			return super.respondWithSuccess(super.t("msg.registration.success"));
		}
		return super.respondWithError(super.t("msg.registration.error"));
	}

	@Override
	public ResponseEntity<?> edit(DT dto) {
		if (dto != null && dto.validate()) {
			baseService.save(dto.toEntity());
			
			return super.respondWithSuccess(super.t("msg.modification.success"));
		}
		return super.respondWithError(super.t("msg.modification.error"));
	}
	
	@Override
	public ResponseEntity<?> edit(T entity) {
		if (entity != null) {
			baseService.save(entity);
			
			return super.respondWithSuccess(super.t("msg.modification.success"));
		}
		return super.respondWithError(super.t("msg.modification.error"));
	}

	@Override
	public ResponseEntity<?> delete(DT dto) {
		if (dto != null && dto.validate()) {
			baseService.delete(dto.toEntity());
			
			return super.respondWithSuccess(super.t("msg.deletion.success"));
		}
		return super.respondWithError(super.t("msg.deletion.error"));
	}
	
	@Override
	public ResponseEntity<?> delete(T entity) {
		if (entity != null) {
			baseService.delete(entity);
			
			return super.respondWithSuccess(super.t("msg.deletion.success"));
		}
		return super.respondWithError(super.t("msg.deletion.error"));
	}

	@Override
	public ResponseEntity<?> count() {
		return super.respondWithSuccessAndObject("count", baseService.count());
	}
	
}
