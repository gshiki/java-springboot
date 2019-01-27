package br.shiki.java.spring.boot.template.dto;

import br.shiki.java.spring.boot.template.dto.base.BaseDTO;
import br.shiki.java.spring.boot.template.model.Profile;
import br.shiki.java.spring.boot.template.util.Util;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 
 * @author Shiki
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ProfileDTO extends BaseDTO<Profile> {
	
	private Long id;

	private String label;
	
    private String description;
    
	@Override
	public Profile toEntity() {
		Profile profile = new Profile();
		
		profile.setId(getId());
		profile.setLabel(getLabel());
		profile.setDescription(getDescription());
		
		return profile;
	}

	@Override
	public void fillDTO(Profile entity) {
		setId(entity.getId());
		setLabel(entity.getLabel());
		setDescription(entity.getDescription());
	}
	
	@Override
	public boolean validate() {
		boolean validateLabel = Util.isValid(getLabel());
		boolean validateDescription = Util.isValid(getDescription());
		
		return validateLabel && validateDescription;
	}
	
}
