package com.scholar.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

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
public class Role {

	@Id
	@EqualsAndHashCode.Include
	@ToString.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ToString.Include
	@Column(nullable = false, unique = true)
	private String name;
	
	@ManyToMany(
		mappedBy = "roles",
		cascade = { CascadeType.REMOVE }
	)
	@Builder.Default
	private Set<Permission> permissions = new HashSet<>();
	
	@ManyToMany(
		mappedBy = "roles",
		cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }
	)
	@Builder.Default
	private Set<Person> persons = new HashSet<>();
	
	public void addPermission(Permission permission) {
		permissions.add(permission);
		permission.getRoles().add(this);
	}
	
	public void removePermission(Permission permission) {
		permissions.remove(permission);
		permission.getRoles().remove(this);
	}
	
}
