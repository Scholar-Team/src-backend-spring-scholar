package com.scholar.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
public class Discipline {

	@Id
	@EqualsAndHashCode.Include
	@ToString.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ToString.Include
	@Column(nullable = false)
	private String name;
	
	@OneToOne
	@JoinColumn(name = "file_id")
	private File file;
	
	@ManyToMany(
		cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }
	)
	@JoinTable(
		name = "discipline_classroom",
		joinColumns = @JoinColumn(name = "discipline_id"),
		inverseJoinColumns = @JoinColumn(name = "classroom_id")
	)
	@Builder.Default
	private Set<Classroom> classrooms = new HashSet<>();
	
	@ManyToMany(
		cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }
	)
	@JoinTable(
		name = "discipline_teacher",
		joinColumns = @JoinColumn(name = "discipline_id"),
		inverseJoinColumns = @JoinColumn(name = "teacher_id")
	)
	@Builder.Default
	private Set<Teacher> teachers = new HashSet<>();
	
	@OneToMany(
		mappedBy = "discipline", 
		cascade = CascadeType.REMOVE, 
		orphanRemoval = true
	)
	@Builder.Default
	private Set<Class> classes = new HashSet<>();
	
	public void addClassroom(Classroom classroom) {
		classrooms.add(classroom);
		classroom.getDisciplines().add(this);
	}
	
	public void removeClassroom(Classroom classroom) {
		classrooms.remove(classroom);
		classroom.getDisciplines().remove(this);
	}
	
	public void addTeacher(Teacher teacher) {
		teachers.add(teacher);
		teacher.getDisciplines().add(this);
	}
	
	public void removeTeacher(Teacher teacher) {
		teachers.remove(teacher);
		teacher.getDisciplines().remove(this);
	}
}
