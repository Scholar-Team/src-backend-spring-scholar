package com.scholar.openapi.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.scholar.dto.DisciplineDTO;
import com.scholar.exception.config.Problem;
import com.scholar.request.DisciplineRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller das disciplinas")
public interface DisciplineControllerOpenApi {

	@ApiOperation("Encontrar todas as disciplinas")
	@ApiResponses({ 
		@ApiResponse(
			code = 200, 
			message = "Todas as disciplinas do sistema",
			response = DisciplineDTO.class)
	})
	ResponseEntity<?> findAll();
	
	@ApiOperation("Encontrar disciplina pelo ID")
	@ApiResponses({ 
		@ApiResponse(
			code = 200, 
			message = "Disciplina encontrada", 
			response = DisciplineDTO.class),
		@ApiResponse(
			code = 404,
			message = "Disciplina não encontrada",
			response = Problem.class)
	})
	@ApiImplicitParam(
			name = "id", 
			value = "ID da disciplina", 
			required = true, 
			dataType = "int", 
			paramType = "path",
			example = "1")
	ResponseEntity<?> findById(Long id);
	
	@ApiOperation("Cadastrar uma disciplina")
	@ApiResponses({ 
		@ApiResponse(
			code = 201, 
			message = "Disciplina cadastrada") 
	})
	ResponseEntity<?> save(
		@ApiParam(name = "body", value = "Objeto da disciplina", required = true) 
		@Valid DisciplineRequest request
	);
	
	@ApiOperation("Atualizar uma disciplina pelo ID")
	@ApiResponses({ 
		@ApiResponse(
			code = 200,
			message = "Disciplina atualizada") 
	})
	@ApiImplicitParam(
			name = "id", 
			value = "ID da disciplina", 
			required = true, 
			dataType = "int", 
			paramType = "path",
			example = "1")
	ResponseEntity<?> putById(
			Long id, 
			@ApiParam(name = "corpo", value = "Objeto da disciplina", required = true) 
			@Valid DisciplineRequest request);
	
	@ApiOperation("Deletar disciplina pelo ID")
	@ApiResponses({ 
		@ApiResponse(
			code = 204, 
			message = "Disciplina deletada") ,
		@ApiResponse(
			code = 404,
			message = "Disciplina não encontrada",
			response = Problem.class)
	})
	@ApiImplicitParam(
			name = "id", 
			value = "ID da disciplina", 
			required = true, 
			dataType = "int", 
			paramType = "path",
			example = "1")
	ResponseEntity<?> deleteById(Long id);

}
