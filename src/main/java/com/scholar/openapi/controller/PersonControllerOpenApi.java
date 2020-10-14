package com.scholar.openapi.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.scholar.dto.PersonDTO;
import com.scholar.exception.config.Problem;
import com.scholar.request.PersonRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller das pessoas")
public interface PersonControllerOpenApi {

	@ApiOperation("Encontrar todas as pessoas")
	@ApiResponses({ 
		@ApiResponse(
			code = 200, 
			message = "Todas as pessoas do sistema",
			response = PersonDTO.class)
	})
	ResponseEntity<?> findAll();
	
	@ApiOperation("Encontrar pessoa pelo ID")
	@ApiResponses({ 
		@ApiResponse(
			code = 200, 
			message = "Pessoa encontrada", 
			response = PersonDTO.class),
		@ApiResponse(
			code = 404,
			message = "Pessoa não encontrada",
			response = Problem.class)
	})
	@ApiImplicitParam(
			name = "id", 
			value = "ID da pessoa", 
			required = true, 
			dataType = "int", 
			paramType = "path",
			example = "1")
	ResponseEntity<?> findById(Long id);
	
	@ApiOperation("Cadastrar uma pessoa")
	@ApiResponses({ 
		@ApiResponse(code = 201, message = "Pessoa cadastrada") 
	})
	ResponseEntity<?> save(
		@ApiParam(name = "body", value = "Objeto da pessoa", required = true) 
		@Valid PersonRequest request
	);
	
	@ApiOperation("Atualizar uma pessoa pelo ID")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "Pessoa atualizada") 
	})
	@ApiImplicitParam(
			name = "id", 
			value = "ID da pessoa", 
			required = true, 
			dataType = "int", 
			paramType = "path",
			example = "1")
	ResponseEntity<?> putById(
			Long id, 
			@ApiParam(name = "corpo", value = "Objeto da pessoa", required = true) 
			@Valid PersonRequest request);
	
	@ApiOperation("Deletar pessoa pelo ID")
	@ApiResponses({ 
		@ApiResponse(
			code = 204, 
			message = "Pessoa deletada") ,
		@ApiResponse(
			code = 404,
			message = "Pessoa não encontrada",
			response = Problem.class)
	})
	@ApiImplicitParam(
			name = "id", 
			value = "ID da pessoa", 
			required = true, 
			dataType = "int", 
			paramType = "path",
			example = "1")
	ResponseEntity<?> deleteById(Long id);

}
