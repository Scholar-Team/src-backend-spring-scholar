package com.scholar.openapi.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.scholar.dto.TeacherDTO;
import com.scholar.exception.config.Problem;
import com.scholar.request.FileRequest;
import com.scholar.request.TeacherRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller dos professores")
public interface TeacherControllerOpenApi {

	@ApiOperation("Encontrar todos os professores")
	@ApiResponses({ 
		@ApiResponse(
			code = 200, 
			message = "Todos os professores do sistema",
			response = TeacherDTO.class)
	})
	ResponseEntity<?> findAll();
	
	@ApiOperation("Encontrar professor pelo ID")
	@ApiResponses({ 
		@ApiResponse(
			code = 200, 
			message = "Professor encontrado", 
			response = TeacherDTO.class),
		@ApiResponse(
			code = 404,
			message = "Professor não encontrado",
			response = Problem.class)
	})
	@ApiImplicitParam(
			name = "id", 
			value = "ID do professor", 
			required = true, 
			dataType = "int", 
			paramType = "path",
			example = "1")
	ResponseEntity<?> findById(Long id);
	
	@ApiOperation("Cadastrar um professor")
	@ApiResponses({ 
		@ApiResponse(code = 201, message = "Professor cadastrado") 
	})
	ResponseEntity<?> save(
		@ApiParam(name = "body", value = "Objeto do professor", required = true) 
		@Valid TeacherRequest request,
		FileRequest fileRequest
	);
	
	@ApiOperation("Atualizar um professor pelo ID")
	@ApiResponses({ 
		@ApiResponse(
				code = 200, 
				message = "Professor atualizado") 
	})
	@ApiImplicitParam(
			name = "id", 
			value = "ID do professor", 
			required = true, 
			dataType = "int", 
			paramType = "path",
			example = "1")
	ResponseEntity<?> putById(
			Long id, 
			@ApiParam(name = "body", value = "Objeto do professor", required = true) 
			@Valid TeacherRequest request);
	
	@ApiOperation("Deletar professor pelo ID")
	@ApiResponses({ 
		@ApiResponse(
			code = 204, 
			message = "Professor deletado") ,
		@ApiResponse(
			code = 404,
			message = "Professor não encontrado",
			response = Problem.class)
	})
	@ApiImplicitParam(
			name = "id", 
			value = "ID do professor", 
			required = true, 
			dataType = "int", 
			paramType = "path",
			example = "1")
	ResponseEntity<?> deleteById(Long id);

}
