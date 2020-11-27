package com.scholar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Address {

	@Id
	@EqualsAndHashCode.Include
	@ToString.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ToString.Include
	@Column(nullable = false, unique = true)
	private String cep;
	
	@ToString.Include
	@Column(nullable = false)
	private String state;
	
	@ToString.Include
	@Column(nullable = false)
	private String city;
	
	@ToString.Include
	@Column(nullable = false)
	private String street;
	
	@ToString.Include
	@Column(nullable = false)
	private String number;
	
	@ToString.Include
	@Column
	private String complement;
}
