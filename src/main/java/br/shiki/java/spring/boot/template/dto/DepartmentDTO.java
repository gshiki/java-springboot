package br.shiki.java.spring.boot.template.dto;

import br.shiki.java.spring.boot.template.dto.base.BaseDTO;
import br.shiki.java.spring.boot.template.model.Department;
import br.shiki.java.spring.boot.template.util.Util;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * [IT01 - UC01]
 * 
 * @author guilhermequeiroz
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class DepartmentDTO extends BaseDTO<Department> {
	
	private Long id;

	private String label;
	
	private String symbol;
	
    private String description;
    
	@Override
	public Department toEntity() {
		Department department = new Department();
		
		department.setId(getId());
		department.setLabel(getLabel());
		department.setSymbol(getSymbol());
		department.setDescription(getDescription());
		
		return department;
	}

	@Override
	public void fillDTO(Department entity) {
		setId(entity.getId());
		setLabel(entity.getLabel());
		setSymbol(entity.getSymbol());
		setDescription(entity.getDescription());
	}
	
	@Override
	public boolean validate() {
		boolean validateLabel = Util.isValid(getLabel());
		boolean validateSymbol = Util.isValid(getSymbol());
		boolean validateDescription = Util.isValid(getDescription());
		
		return validateLabel && validateSymbol && validateDescription;
	}
	
}
