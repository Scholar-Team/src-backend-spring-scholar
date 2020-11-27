package com.scholar.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AccessLevel;
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
public class Student extends Person {

	@ManyToOne(
		cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }
	)
	@JoinColumn(name = "classroom_id")
	@Setter(AccessLevel.NONE)
	private Classroom classroom;
	
	@OneToMany(
		mappedBy = "student",
		cascade = { CascadeType.ALL },
		orphanRemoval = true
	)
	@Builder.Default
	private Set<Answer> answers = new HashSet<>();
	
	public void setClassroom(Classroom classroom) {
		if(Objects.nonNull(classroom))
			classroom.getStudents().add(this);
		
		else if(Objects.nonNull(this.classroom))
			this.classroom.getStudents().remove(this);
		
		this.classroom = classroom;
	}
}
