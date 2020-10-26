package com.scholar.openapi.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.scholar.dto.StudentDTO;
import com.scholar.exception.config.Problem;
import com.scholar.request.StudentRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller dos estudantes")
public interface StudentControllerOpenApi {

	@ApiOperation("Encontrar todos os estudantes")
	@ApiResponses({ 
		@ApiResponse(
			code = 200, 
			message = "Todos os estudantes do sistema",
			response = StudentDTO.class)
	})
	ResponseEntity<?> findAll();
	
	@ApiOperation("Encontrar estudante pelo ID")
	@ApiResponses({ 
		@ApiResponse(
			code = 200, 
			message = "Estudante encontrado", 
			response = StudentDTO.class),
		@ApiResponse(
			code = 404,
			message = "Estudante não encontrado",
			response = Problem.class)
	})
	@ApiImplicitParam(
			name = "id", 
			value = "ID do estudante", 
			required = true, 
			dataType = "int", 
			paramType = "path",
			example = "1")
	ResponseEntity<?> findById(Long id);
	
	@ApiOperation("Cadastrar um estudante")
	@ApiResponses({ 
		@ApiResponse(code = 201, message = "Estudante cadastrado") 
	})
	ResponseEntity<?> save(
		@ApiParam(name = "body", value = "Objeto do estudante", required = true) 
		@Valid StudentRequest request
	);
	
	@ApiOperation("Atualizar um estudante pelo ID")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "Estudante atualizado") 
	})
	@ApiImplicitParam(
			name = "id", 
			value = "ID do estudante", 
			required = true, 
			dataType = "int", 
			paramType = "path",
			example = "1")
	ResponseEntity<?> putById(
			Long id, 
			@ApiParam(name = "body", value = "Objeto do estudante", required = true) 
			@Valid StudentRequest request);
	
	@ApiOperation("Deletar estudante pelo ID")
	@ApiResponses({ 
		@ApiResponse(
			code = 204, 
			message = "Estudante deletado") ,
		@ApiResponse(
			code = 404,
			message = "Estudante não encontrado",
			response = Problem.class)
	})
	@ApiImplicitParam(
			name = "id", 
			value = "ID do estudante", 
			required = true, 
			dataType = "int", 
			paramType = "path",
			example = "1")
	ResponseEntity<?> deleteById(Long id);

}
