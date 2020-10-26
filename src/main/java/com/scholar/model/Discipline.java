package com.scholar.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Discipline {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@ManyToMany(mappedBy = "disciplines")
	private List<Classroom> classrooms;
	
	@ManyToMany
	@JoinTable(
		name = "discipline_teacher",
		joinColumns = @JoinColumn(name = "discipline_id"),
		inverseJoinColumns = @JoinColumn(name = "teacher_id")
	)
	private List<Teacher> teachers;

}
