package com.scholar.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class Classroom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@ManyToMany
	@JoinTable(
		name = "classroom_discipline",
		joinColumns = @JoinColumn(name = "classroom_id"),
		inverseJoinColumns = @JoinColumn(name = "discipline_id")
	)
	private List<Discipline> disciplines;
	
	@OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL)
	private List<Student> students;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "period_id", nullable = false)
	private Period period;
}
