package com.scholar.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table
public class Person {
	
	@Id
	@EqualsAndHashCode.Include
	@ToString.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ToString.Include
	@Column(nullable = false)
	private String name;
	
	@ToString.Include
	@Column(nullable = false, unique = true)
	private String email;
	
	@ToString.Include
	@Column(nullable = false)
	private String password;
	
	@ToString.Include
	@Column(nullable = false)
	private LocalDate birthDate;
	
	@ToString.Include
	@Column(nullable = false, unique = true)
	private String cpf;
	
	@OneToMany(
		mappedBy = "person",
		cascade = { CascadeType.ALL }, 
		orphanRemoval = true
	)
	@Builder.Default
	private Set<Telephone> telephones = new HashSet<>();
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
	private Address address;
	
	@OneToOne
	@JoinColumn(name = "file_id")
	private File file;
	
	@ManyToMany(
		cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }
	)
	@JoinTable(
		name = "person_role",
		joinColumns = @JoinColumn(name = "person_id"),
		inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	@Builder.Default
	private Set<Role> roles = new HashSet<>();
	
	public void addRole(Role role) {
		roles.add(role);
		role.getPersons().add(this);
	}
	
	public void removeRole(Role role) {
		roles.remove(role);
		role.getPersons().remove(this);
	}
	
	public void addTelephone(Telephone telephone) {
		telephones.add(telephone);
		telephone.setPerson(this);
	}
	
	public void removeTelephone(Telephone telephone) {
		telephones.remove(telephone);
		telephone.setPerson(null);
	}
	
}
