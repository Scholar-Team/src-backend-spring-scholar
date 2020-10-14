package com.scholar.openapi.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.scholar.dto.SchoolDTO;
import com.scholar.exception.config.Problem;
import com.scholar.request.SchoolRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller das escolas")
public interface SchoolControllerOpenApi {

	@ApiOperation("Encontrar todas as escolas")
	@ApiResponses({ 
		@ApiResponse(
			code = 200, 
			message = "Todas as escolas do sistema",
			response = SchoolDTO.class)
	})
	ResponseEntity<?> findAll();
	
	@ApiOperation("Encontrar escola pelo ID")
	@ApiResponses({ 
		@ApiResponse(
			code = 200, 
			message = "Escola encontrada", 
			response = SchoolDTO.class),
		@ApiResponse(
			code = 404,
			message = "Escola não encontrada",
			response = Problem.class)
	})
	@ApiImplicitParam(
			name = "id", 
			value = "ID da escola", 
			required = true, 
			dataType = "int", 
			paramType = "path",
			example = "1")
	ResponseEntity<?> findById(Long id);
	
	@ApiOperation("Cadastrar uma escola")
	@ApiResponses({ 
		@ApiResponse(code = 201, message = "Escola cadastrada") 
	})
	ResponseEntity<?> save(
		@ApiParam(name = "body", value = "Objeto da escola", required = true) 
		@Valid SchoolRequest request
	);
	
	@ApiOperation("Atualizar uma escola pelo ID")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "Escola atualizada") 
	})
	@ApiImplicitParam(
			name = "id", 
			value = "ID da escola", 
			required = true, 
			dataType = "int", 
			paramType = "path",
			example = "1")
	ResponseEntity<?> putById(
			Long id, 
			@ApiParam(name = "corpo", value = "Objeto da escola", required = true) 
			@Valid SchoolRequest request);
	
	@ApiOperation("Deletar escola pelo ID")
	@ApiResponses({ 
		@ApiResponse(
			code = 204, 
			message = "Escola deletada"),
		@ApiResponse(
			code = 404,
			message = "Escola não encontrada",
			response = Problem.class)
	})
	@ApiImplicitParam(
			name = "id", 
			value = "ID da escola", 
			required = true, 
			dataType = "int", 
			paramType = "path",
			example = "1")
	ResponseEntity<?> deleteById(Long id);

}
