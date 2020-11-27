package com.scholar.openapi.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.scholar.dto.ClassDTO;
import com.scholar.exception.config.Problem;
import com.scholar.request.ClassRequest;
import com.scholar.request.FileRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller das aulas")
public interface ClassControllerOpenApi {

	@ApiOperation("Encontrar todas as aulas")
	@ApiResponses({ 
		@ApiResponse(
			code = 200, 
			message = "Todas as aulas do sistema",
			response = ClassDTO.class)
	})
	ResponseEntity<?> findAll();
	
	@ApiOperation("Encontrar aula pelo ID")
	@ApiResponses({ 
		@ApiResponse(
			code = 200, 
			message = "Aula encontrada", 
			response = ClassDTO.class),
		@ApiResponse(
			code = 404,
			message = "Aula não encontrada",
			response = Problem.class)
	})
	@ApiImplicitParam(
			name = "id", 
			value = "ID da aula", 
			required = true, 
			dataType = "int", 
			paramType = "path",
			example = "1")
	ResponseEntity<?> findById(Long id);
	
	@ApiOperation("Cadastrar uma aula")
	@ApiResponses({ 
		@ApiResponse(code = 201, message = "Aula cadastrada") 
	})
	ResponseEntity<?> save(
		@ApiParam(name = "body", value = "Objeto da aula", required = true) 
		@Valid ClassRequest request,
		FileRequest attachment
	);
	
	@ApiOperation("Atualizar uma aula pelo ID")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "Aula atualizada") 
	})
	@ApiImplicitParam(
			name = "id", 
			value = "ID da aula", 
			required = true, 
			dataType = "int", 
			paramType = "path",
			example = "1")
	ResponseEntity<?> putById(
			Long id, 
			@ApiParam(name = "corpo", value = "Objeto da aula", required = true) 
			@Valid ClassRequest request);
	
	@ApiOperation("Deletar aula pelo ID")
	@ApiResponses({ 
		@ApiResponse(
			code = 204, 
			message = "Aula deletada") ,
		@ApiResponse(
			code = 404,
			message = "Aula não encontrada",
			response = Problem.class)
	})
	@ApiImplicitParam(
			name = "id", 
			value = "ID da aula", 
			required = true, 
			dataType = "int", 
			paramType = "path",
			example = "1")
	ResponseEntity<?> deleteById(Long id);
	
}
