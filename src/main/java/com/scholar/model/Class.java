package com.scholar.model;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
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
public class Class {

	@Id
	@EqualsAndHashCode.Include
	@ToString.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ToString.Include
	@Column(nullable = false)
	private String name;
	
	@ToString.Include
	@Column(nullable = false, columnDefinition = "TEXT")
	private String description;
	
	@ToString.Include
	@Column
	private String link;
	
	@Column(nullable = false)
	private ZonedDateTime date;
	
	@OneToOne
	@JoinColumn(name = "file_id")
	private File attachment;
	
	@ManyToMany(
		mappedBy = "classes",
		cascade = { CascadeType.REMOVE }
	)
	@Builder.Default
	private Set<Activity> activities = new HashSet<>();
	
	@ManyToOne(
		cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }
	)
	@JoinColumn(name = "discipline_id", nullable = false)
	@Setter(AccessLevel.NONE)
	private Discipline discipline;
	
	public void setDiscipline(Discipline discipline) {
		if(Objects.nonNull(discipline))
			discipline.getClasses().add(this);
		
		else if(Objects.nonNull(this.discipline))
			this.discipline.getClasses().remove(this);
		
		this.discipline = discipline;
	}
}
