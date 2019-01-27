package br.shiki.java.spring.boot.template.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


/**
 * 
 * @author Shiki
 *
 */
@Data
@Entity
@Table(name="department")
public class Department implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1187884853755482629L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

	@Column(name="label", nullable=false)
	private String label;
	
	@Column(name="symbol", nullable=false)
	private String symbol;
	
    @Column(name="description", nullable=false)
    private String description;
    
}
