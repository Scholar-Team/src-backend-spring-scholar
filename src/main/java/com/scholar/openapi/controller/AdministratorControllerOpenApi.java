package com.scholar.openapi.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.scholar.dto.AdministratorDTO;
import com.scholar.exception.config.Problem;
import com.scholar.request.AdministratorRequest;
import com.scholar.request.FileRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller dos administradores")
public interface AdministratorControllerOpenApi {

	@ApiOperation("Encontrar todos os administradores")
	@ApiResponses({ 
		@ApiResponse(
			code = 200, 
			message = "Todos os administradores do sistema",
			response = AdministratorDTO.class)
	})
	ResponseEntity<?> findAll();
	
	@ApiOperation("Encontrar administrador pelo ID")
	@ApiResponses({ 
		@ApiResponse(
			code = 200, 
			message = "Administrador encontrado", 
			response = AdministratorDTO.class),
		@ApiResponse(
			code = 404,
			message = "Administrador não encontrado",
			response = Problem.class)
	})
	@ApiImplicitParam(
			name = "id", 
			value = "ID do administrador", 
			required = true, 
			dataType = "int", 
			paramType = "path",
			example = "1")
	ResponseEntity<?> findById(Long id);
	
	@ApiOperation("Cadastrar um administrador")
	@ApiResponses({ 
		@ApiResponse(code = 201, message = "Administrador cadastrado") 
	})
	ResponseEntity<?> save(
		@ApiParam(name = "body", value = "Objeto do administrador", required = true) 
		@Valid AdministratorRequest request,
		FileRequest fileRequest
	);
	
	@ApiOperation("Atualizar um administrador pelo ID")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "Administrador atualizado") 
	})
	@ApiImplicitParam(
			name = "id", 
			value = "ID do administrador", 
			required = true, 
			dataType = "int", 
			paramType = "path",
			example = "1")
	ResponseEntity<?> putById(
			Long id, 
			@ApiParam(name = "body", value = "Objeto do administrador", required = true) 
			@Valid AdministratorRequest request);
	
	@ApiOperation("Deletar administrador pelo ID")
	@ApiResponses({ 
		@ApiResponse(
			code = 204, 
			message = "Administrador deletado") ,
		@ApiResponse(
			code = 404,
			message = "Administrador não encontrado",
			response = Problem.class)
	})
	@ApiImplicitParam(
			name = "id", 
			value = "ID do administrador", 
			required = true, 
			dataType = "int", 
			paramType = "path",
			example = "1")
	ResponseEntity<?> deleteById(Long id);
	
}
