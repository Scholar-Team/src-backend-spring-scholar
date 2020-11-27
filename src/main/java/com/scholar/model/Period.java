package com.scholar.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.scholar.model.enumeration.SchoolPeriod;

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
public class Period {

	@Id
	@EqualsAndHashCode.Include
	@ToString.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ToString.Include
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private SchoolPeriod period;
	
	@ManyToOne(
		cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }
	)
	@JoinColumn(name = "school_id", nullable =  false)
	@Setter(AccessLevel.NONE)
	private School school;
	
	@OneToMany(
		mappedBy = "period", 
		cascade = CascadeType.REMOVE
	)
	@Builder.Default
	private Set<Classroom> classrooms = new HashSet<>();
	
	public void setSchool(School school) {
		if(Objects.nonNull(school))
			school.getPeriods().add(this);
		
		else if(Objects.nonNull(this.school))
			this.school.getPeriods().remove(this);
		
		this.school = school;
	}
}
