package com.daniele.pbarra.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "template")
public class Template implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "id_template", 
	sequenceName = "id_template", 
	allocationSize=1)
	@GeneratedValue( strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(name ="nombre")
	private String nombre;
	
	@JsonIgnore
	@OneToMany(mappedBy = "idTemplate")
	private List<Caja> caja;
}
