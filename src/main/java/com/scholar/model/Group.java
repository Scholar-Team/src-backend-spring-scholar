package com.scholar.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "group_table")
public class Group {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
		name = "group_permission", 
		joinColumns = @JoinColumn(name = "group_id"),
		inverseJoinColumns = @JoinColumn(name = "permission_id")
	)
	private List<Permission> permissions;
	
	@ManyToMany(mappedBy = "groups")
	private List<Person> persons;
	
}
