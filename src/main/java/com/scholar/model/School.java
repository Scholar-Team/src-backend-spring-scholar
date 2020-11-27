package com.scholar.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.scholar.model.enumeration.SchoolType;

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
public class School {

	@Id
	@EqualsAndHashCode.Include
	@ToString.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ToString.Include
	@Column(nullable = false)
	private String name;
	
	@ToString.Include
	@Column(unique = true)
	private String site;
	
	@ToString.Include
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private SchoolType type;
	
	@OneToMany(
		mappedBy = "school", 
		cascade = CascadeType.REMOVE, 
		orphanRemoval = true
	)
	@Builder.Default
	private Set<Period> periods = new HashSet<>();
	
	@OneToOne(
		mappedBy = "school",
		cascade = { CascadeType.REMOVE }
	)
	private Director director;
	
	@OneToOne(
		cascade = CascadeType.ALL
	)
    @JoinColumn(name = "address_id")
	private Address address;
}
