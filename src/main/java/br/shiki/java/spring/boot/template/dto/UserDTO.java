package br.shiki.java.spring.boot.template.dto;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;

import br.shiki.java.spring.boot.template.dto.base.BaseDTO;
import br.shiki.java.spring.boot.template.model.Department;
import br.shiki.java.spring.boot.template.model.Profile;
import br.shiki.java.spring.boot.template.model.User;
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
public class UserDTO extends BaseDTO<User> {
	
	private Long id;

    private String login;

    private String name;

    @Email
    private String email;

    @Valid
    private List<ProfileDTO> profilesDTO = new ArrayList<>();
    
    @Valid
    private List<DepartmentDTO> departmentsDTO = new ArrayList<>();
    
    private boolean active;
    
	@Override
	public User toEntity() {
		User user = new User();
		
		Calendar calendar = Calendar.getInstance();
		
		user.setId(getId());
		user.setLogin(getLogin());
		user.setName(getName());
		user.setEmail(getEmail());
		user.setCreateDate(calendar.getTime());
		user.setUpdateDate(calendar.getTime());
		user.setActive(isActive());
		
		for (ProfileDTO profileDTO : profilesDTO) {
			user.addProfile(profileDTO.toEntity());
		}
		
		for (DepartmentDTO departmentDTO : departmentsDTO) {
			user.addDepartment(departmentDTO.toEntity());
		}
		return user;
	}
	
	@Override
	public void fillDTO(User entity) {
		setId(entity.getId());
		setLogin(entity.getLogin());
		setName(entity.getName());
		setEmail(entity.getEmail());
		setActive(entity.isActive());
		
		for (Profile profile : entity.getProfiles()) {
			ProfileDTO profileDTO = new ProfileDTO();
			
			profileDTO.fillDTO(profile);
			
			this.profilesDTO.add(profileDTO);
		}
		
		for (Department department : entity.getDepartments()) {
			DepartmentDTO departmentDTO = new DepartmentDTO();
			
			departmentDTO.fillDTO(department);
			
			this.departmentsDTO.add(departmentDTO);
		}
	}
	
	@Override
	public boolean validate() {
		boolean validateLogin = Util.isValid(getLogin());
		
		return validateLogin;
	}
	
}
