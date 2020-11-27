package com.scholar.model;

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
import javax.persistence.OneToMany;
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
public class Classroom {

	@Id
	@EqualsAndHashCode.Include
	@ToString.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ToString.Include
	@Column(nullable = false)
	private String name;
	
	@ManyToMany(
		cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH },
		mappedBy = "classrooms"
	)
	@Builder.Default
	private Set<Discipline> disciplines = new HashSet<>();
	
	@OneToMany(
		mappedBy = "classroom", 
		cascade = CascadeType.REMOVE
	)
	@Builder.Default
	private Set<Activity> activities = new HashSet<>();
	
	@OneToMany(
		mappedBy = "classroom",
		cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }
	)
	@Builder.Default
	private Set<Student> students = new HashSet<>();
	
	@ManyToOne(
		cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }
	)
	@JoinColumn(name = "period_id", nullable = false)
	@Setter(AccessLevel.NONE)
	private Period period;
	
	public void setPeriod(Period period) {
		if(Objects.nonNull(period))
			period.getClassrooms().add(this);
		
		else if(Objects.nonNull(this.period))
			this.period.getClassrooms().remove(this);
		
		this.period = period;
	}
}
