package com.scholar.model;

import java.util.List;

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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "period")
public class Period {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	@Enumerated(EnumType.STRING)
	private SchoolPeriod period;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "school_id", nullable =  false)
	private School school;
	
	@OneToMany(mappedBy = "period", cascade = CascadeType.ALL)
	private List<Classroom> classrooms;
}
