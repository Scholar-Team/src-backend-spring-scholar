package com.scholar.openapi.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.scholar.dto.ClassroomDTO;
import com.scholar.exception.config.Problem;
import com.scholar.request.ClassroomRequest;
import com.scholar.request.FileRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller das turmas")
public interface ClassroomControllerOpenApi {

	@ApiOperation("Encontrar todas as turmas")
	@ApiResponses({ 
		@ApiResponse(
			code = 200, 
			message = "Todas as turmas do sistema",
			response = ClassroomDTO.class)
	})
	ResponseEntity<?> findAll();
	
	@ApiOperation("Encontrar turma pelo ID")
	@ApiResponses({ 
		@ApiResponse(
			code = 200, 
			message = "turma encontrada", 
			response = ClassroomDTO.class),
		@ApiResponse(
			code = 404,
			message = "turma não encontrada",
			response = Problem.class)
	})
	@ApiImplicitParam(
			name = "id", 
			value = "ID da turma", 
			required = true, 
			dataType = "int", 
			paramType = "path",
			example = "1")
	ResponseEntity<?> findById(Long id);
	
	@ApiOperation("Cadastrar uma turma")
	@ApiResponses({ 
		@ApiResponse(code = 201, message = "turma cadastrada") 
	})
	ResponseEntity<?> save(
		@ApiParam(name = "body", value = "Objeto da turma", required = true) 
		@Valid ClassroomRequest request,
		FileRequest fileRequest
	);
	
	@ApiOperation("Atualizar uma turma pelo ID")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "turma atualizada") 
	})
	@ApiImplicitParam(
			name = "id", 
			value = "ID da turma", 
			required = true, 
			dataType = "int", 
			paramType = "path",
			example = "1")
	ResponseEntity<?> putById(
			Long id, 
			@ApiParam(name = "corpo", value = "Objeto da turma", required = true) 
			@Valid ClassroomRequest request);
	
	@ApiOperation("Deletar turma pelo ID")
	@ApiResponses({ 
		@ApiResponse(
			code = 204, 
			message = "turma deletada") ,
		@ApiResponse(
			code = 404,
			message = "turma não encontrada",
			response = Problem.class)
	})
	@ApiImplicitParam(
			name = "id", 
			value = "ID da turma", 
			required = true, 
			dataType = "int", 
			paramType = "path",
			example = "1")
	ResponseEntity<?> deleteById(Long id);
	
}
