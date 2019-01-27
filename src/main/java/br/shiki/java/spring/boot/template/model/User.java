package br.shiki.java.spring.boot.template.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;


/**
 * 
 * @author Shiki
 *
 */
@Data
@Entity
@Table(name="users")
public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2060747559261495145L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

    @Column(name="login", nullable=false)
    private String login;
    
    @Column(name="password", nullable=false)
    private String password;

    @Column(name="name", nullable=true)
    private String name;

    @Column(name="email", nullable=true, unique=true)
    private String email;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy HH:mm:ss", locale="pt-BR", timezone="Brazil/East")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_date", nullable=false, updatable=false)
    private Date createDate = Calendar.getInstance().getTime();
    
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy HH:mm:ss", locale="pt-BR", timezone="Brazil/East")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="update_date", nullable=true)
    private Date updateDate = Calendar.getInstance().getTime();

    @ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.DETACH)
    @JoinTable( name="users_profiles", joinColumns= { @JoinColumn(name="user_id") }, inverseJoinColumns= { @JoinColumn(name="profile_id") } )
    private Set<Profile> profiles = new HashSet<>();
    
    @ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.DETACH)
    @JoinTable( name="users_departments", joinColumns= { @JoinColumn(name="user_id") }, inverseJoinColumns= { @JoinColumn(name="department_id") } )
    private Set<Department> departments = new HashSet<>();
    
    @Column(name="active", nullable=false)
    private boolean active = true;
    
    public void addProfile(Profile profile) {
    	this.profiles.add(profile);
    }
    
    public void addDepartment(Department department) {
    	this.departments.add(department);
    }
    
}
