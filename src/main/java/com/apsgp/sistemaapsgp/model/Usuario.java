package com.apsgp.sistemaapsgp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.apsgp.sistemaapsgp.domain.ValidationGroups;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;



@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
@Table(name="usuario")
public class Usuario {

	@NotNull(groups=ValidationGroups.UsuarioId.class)
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max=60)
	@Column(name="nome")
	private String nome;


	@NotBlank
	@Email
	@Size(max=200)
	@Column(name="email")
	private String email;

	@NotBlank
	@Size(max=60)
	@Column(name="senha")
	private String senha;
	
}
