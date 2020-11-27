package com.scholar.model;

import java.time.ZonedDateTime;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
public class Feedback {

	@Id
	@EqualsAndHashCode.Include
	@ToString.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ToString.Include
	@Column(nullable = false)
	private ZonedDateTime dateTime;
	
	@ToString.Include
	@Column(nullable = false, columnDefinition = "TEXT")
	private String feedback;
	
	@ToString.Include
	@Column(nullable = false)
	private String grade;
	
	@OneToOne
	@JoinColumn(name = "file_id")
	private File attachment;
	
	@ManyToOne(
		cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }
	)
	@JoinColumn(name = "answer_id", nullable = false)
	@Setter(AccessLevel.NONE)
	private Answer answer;
	
	@ManyToOne(
		cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }
	)
	@JoinColumn(name = "teacher_id", nullable = false)
	@Setter(AccessLevel.NONE)
	private Teacher teacher;
	
	public void setAnswer(Answer answer) {
		if(Objects.nonNull(answer))
			answer.getFeedbacks().add(this);
		
		else if(Objects.nonNull(this.answer))
			this.answer.getFeedbacks().remove(this);
		
		this.answer = answer;
	}
	
	public void setTeacher(Teacher teacher) {
		if(Objects.nonNull(teacher))
			teacher.getFeedbacks().add(this);
		
		else if(Objects.nonNull(this.teacher))
			this.teacher.getFeedbacks().remove(this);
		
		this.teacher = teacher;
	}
}
