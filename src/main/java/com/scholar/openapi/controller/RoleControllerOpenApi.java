package com.scholar.openapi.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.scholar.dto.RoleDTO;
import com.scholar.exception.config.Problem;
import com.scholar.request.RoleRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller dos papeis")
public interface RoleControllerOpenApi {

	@ApiOperation("Encontrar todos os papeis")
	@ApiResponses({ 
		@ApiResponse(
			code = 200, 
			message = "Todos os papeis do sistema",
			response = RoleDTO.class)
	})
	ResponseEntity<?> findAll();
	
	@ApiOperation("Encontrar papel pelo ID")
	@ApiResponses({ 
		@ApiResponse(
			code = 200, 
			message = "Papel encontrado", 
			response = RoleDTO.class),
		@ApiResponse(
			code = 404,
			message = "Papel não encontrado",
			response = Problem.class)
	})
	@ApiImplicitParam(
			name = "id", 
			value = "ID do papel", 
			required = true, 
			dataType = "int", 
			paramType = "path",
			example = "1")
	ResponseEntity<?> findById(Long id);
	
	@ApiOperation("Cadastrar um papel")
	@ApiResponses({
		@ApiResponse(
			code = 201, 
			message = "Papel cadastrado",
			response = RoleDTO.class) 
	})
	ResponseEntity<?> save(
		@ApiParam(name = "body", value = "Objeto do papel", required = true) 
		@Valid RoleRequest request
	);
	
	@ApiOperation("Atualizar um papel pelo ID")
	@ApiResponses({ 
		@ApiResponse(
			code = 200, 
			message = "Papel atualizado") 
	})
	@ApiImplicitParam(
			name = "id", 
			value = "ID do papel", 
			required = true, 
			dataType = "int", 
			paramType = "path",
			example = "1")
	ResponseEntity<?> putById(
			Long id, 
			@ApiParam(name = "body", value = "Objeto do papel", required = true) 
			@Valid RoleRequest request);
	
	@ApiOperation("Deletar papel pelo ID")
	@ApiResponses({ 
		@ApiResponse(
			code = 204, 
			message = "Papel deletado") ,
		@ApiResponse(
			code = 404,
			message = "Papel não encontrado",
			response = Problem.class)
	})
	@ApiImplicitParam(
			name = "id", 
			value = "ID do papel", 
			required = true, 
			dataType = "int", 
			paramType = "path",
			example = "1")
	ResponseEntity<?> deleteById(Long id);

}