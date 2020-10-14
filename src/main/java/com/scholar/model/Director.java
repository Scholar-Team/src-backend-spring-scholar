package com.scholar.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString
@Entity
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "person_id")
@Table(name = "director")
public class Director extends Person {

}
