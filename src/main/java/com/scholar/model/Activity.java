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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
public class Activity {

	@Id
	@EqualsAndHashCode.Include
	@ToString.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ToString.Include
	@Column(nullable = false)
	private String name;
	
	@ToString.Include
	@Column(columnDefinition = "TEXT")
	private String description;
	
	@ToString.Include
	@Column(nullable = false)
	private ZonedDateTime initialDate;
	
	@ToString.Include
	@Column(nullable = false)
	private ZonedDateTime finalDate;
	
	@OneToOne
	@JoinColumn(name = "file_id")
	private File attachment;
	
	@ManyToOne(
		cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }
	)
	@JoinColumn(name = "classroom_id", nullable = false)
	@Setter(AccessLevel.NONE)
	private Classroom classroom;
	
	@ManyToMany(
		cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }
	)
	@JoinTable(
		name = "activity_class",
		joinColumns = @JoinColumn(name = "activity_id"),
		inverseJoinColumns = @JoinColumn(name = "class_id")
	)
	@Builder.Default
	private Set<Class> classes = new HashSet<>();
	
	@OneToMany(
		mappedBy = "activity",
		cascade = CascadeType.REMOVE, 
		orphanRemoval = true
	)
	@Builder.Default
	private Set<Answer> answers = new HashSet<>();
	
	public void setClassroom(Classroom classroom) {
		if(Objects.nonNull(classroom))
			classroom.getActivities().add(this);
		
		else if(Objects.nonNull(this.classroom))
			this.classroom.getActivities().remove(this);
		
		this.classroom = classroom;
	}
	
	public void addClass(Class $class) {
		classes.add($class);
		$class.getActivities().add(this);
	}
	
	public void removeClass(Class $class) {
		classes.remove($class);
		$class.getActivities().remove(this);
	}
}
