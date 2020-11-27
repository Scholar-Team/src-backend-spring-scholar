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
public class Answer {

	@Id
	@EqualsAndHashCode.Include
	@ToString.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ToString.Include
	@Column(nullable = false)
	private ZonedDateTime dateTime;
	
	@ToString.Include
	@Column(columnDefinition = "TEXT")
	private String description;
		
	@ManyToOne(
		cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }
	)
	@JoinColumn(name = "activity_id", nullable = false)
	@Setter(AccessLevel.NONE)
	private Activity activity;
	
	@ManyToOne(
		cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }
	)
	@JoinColumn(
		name = "student_id",
		nullable = false
	)
	@Setter(AccessLevel.NONE)
	private Student student;
	
	@OneToMany(
		mappedBy = "answer",
		cascade = { CascadeType.REMOVE }
	)
	@Builder.Default
	private Set<Feedback> feedbacks = new HashSet<>();
	
	@OneToOne
	@JoinColumn(name = "file_id")
	private File attachment;
	
	public void setActivity(Activity activity) {
		if(Objects.nonNull(activity))
			activity.getAnswers().add(this);
		
		else if(Objects.nonNull(this.activity))
			this.activity.getAnswers().remove(this);
		
		this.activity = activity;
	}
	
	public void setStudent(Student student) {
		if(Objects.nonNull(student))
			student.getAnswers().add(this);
		
		else if(Objects.nonNull(this.student))
			this.student.getAnswers().remove(this);
		
		this.student = student;
	}
}
