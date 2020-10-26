package com.scholar.openapi.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.scholar.dto.PermissionDTO;
import com.scholar.exception.config.Problem;
import com.scholar.request.PermissionRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller das permissões")
public interface PermissionControllerOpenApi {
		
	@ApiOperation("Encontrar todas as permissões")
	@ApiResponses({ 
		@ApiResponse(
			code = 200, 
			message = "Todas as permissões do sistema",
			response = PermissionDTO.class)
	})
	ResponseEntity<?> findAll();
	
	@ApiOperation("Encontrar permissão pelo ID")
	@ApiResponses({ 
		@ApiResponse(
			code = 200, 
			message = "Permissão encontrada", 
			response = PermissionDTO.class),
		@ApiResponse(
			code = 404,
			message = "Permissão não encontrada",
			response = Problem.class)
	})
	@ApiImplicitParam(
			name = "id", 
			value = "ID da permissão", 
			required = true, 
			dataType = "int", 
			paramType = "path",
			example = "1")
	ResponseEntity<?> findById(Long Minid);
	
	@ApiOperation("Cadastrar uma permissão")
	@ApiResponses({ 
		@ApiResponse(code = 201, message = "Permissão cadastrada") 
	})
	ResponseEntity<?> save(
		@ApiParam(name = "body", value = "Objeto da permissão", required = true) 
		@Valid PermissionRequest request
	);
	
	@ApiOperation("Atualizar uma permissão pelo ID")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "Permissão atualizada") 
	})
	@ApiImplicitParam(
			name = "id", 
			value = "ID da permissão", 
			required = true, 
			dataType = "int", 
			paramType = "path",
			example = "1")
	ResponseEntity<?> putById(
			Long id, 
			@ApiParam(name = "corpo", value = "Objeto da permissão", required = true) 
			@Valid PermissionRequest request);
	
	@ApiOperation("Deletar permissão pelo ID")
	@ApiResponses({ 
		@ApiResponse(
			code = 204, 
			message = "Permissão deletada"),
		@ApiResponse(
			code = 404,
			message = "Permissão não encontrada",
			response = Problem.class)
	})
	@ApiImplicitParam(
			name = "id", 
			value = "ID da permissão", 
			required = true, 
			dataType = "int", 
			paramType = "path",
			example = "1")
	ResponseEntity<?> deleteById(Long id);
}
