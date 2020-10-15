package com.scholar.request;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonRequest {

	private Long id;
	
	@NotBlank(message = "O nome não pode estar vazio.")
	private String name;
	
	@NotBlank(message = "A senha não pode estar vazia.")
	@Size(min = 8, max = 64, message = "A senha deve ter entre 8 a 64 caracteres.")
	private String password;
	
	@Past(message = "Data de nascimento inválida")
	private LocalDate birthDate;
	
	@CPF(message = "O CPF deve ser válido.")
	private String cpf;
	
	@Valid
	private List<TelephoneRequest> telephones;
	
	@Valid
	private AddressRequest address;
}
