package br.com.scholar.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(exclude = { 
		"id", "name", "password", "birthDate" 
		})
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "tbperson")
public abstract class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String name;
	
	@Column
	private String password;
	
	@Column(name = "birth_date")
	LocalDateTime birthDate;
	
	@Column
	private String cpf;
}
