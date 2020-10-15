package com.scholar.openapi.controller;

import org.springframework.http.ResponseEntity;

import com.scholar.dto.PersonDTO;
import com.scholar.exception.config.Problem;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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

}
