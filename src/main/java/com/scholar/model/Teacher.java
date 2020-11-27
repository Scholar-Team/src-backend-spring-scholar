package com.scholar.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "person_id")
@Table
public class Teacher extends Person {

	@ManyToMany(
		mappedBy = "teachers", 
		cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH }
	)
	@Builder.Default
	private Set<Discipline> disciplines = new HashSet<>();
	
	@OneToMany(
		mappedBy = "teacher",
		cascade = { CascadeType.REMOVE }, 
		orphanRemoval = true
	)
	@Builder.Default
	private Set<Feedback> feedbacks = new HashSet<>();
}
