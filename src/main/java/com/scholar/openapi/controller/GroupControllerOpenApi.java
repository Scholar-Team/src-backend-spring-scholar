package com.scholar.openapi.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.scholar.dto.GroupDTO;
import com.scholar.exception.config.Problem;
import com.scholar.request.GroupRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller dos grupos")
public interface GroupControllerOpenApi {

	@ApiOperation("Encontrar todos os grupos")
	@ApiResponses({ 
		@ApiResponse(
			code = 200, 
			message = "Todos os grupos do sistema",
			response = GroupDTO.class)
	})
	ResponseEntity<?> findAll();
	
	@ApiOperation("Encontrar grupo pelo ID")
	@ApiResponses({ 
		@ApiResponse(
			code = 200, 
			message = "Grupo encontrado", 
			response = GroupDTO.class),
		@ApiResponse(
			code = 404,
			message = "Grupo não encontrado",
			response = Problem.class)
	})
	@ApiImplicitParam(
			name = "id", 
			value = "ID do grupo", 
			required = true, 
			dataType = "int", 
			paramType = "path",
			example = "1")
	ResponseEntity<?> findById(Long id);
	
	@ApiOperation("Cadastrar um grupo")
	@ApiResponses({
		@ApiResponse(
			code = 201, 
			message = "Grupo cadastrado",
			response = GroupDTO.class) 
	})
	ResponseEntity<?> save(
		@ApiParam(name = "body", value = "Objeto do grupo", required = true) 
		@Valid GroupRequest request
	);
	
	@ApiOperation("Atualizar um grupo pelo ID")
	@ApiResponses({ 
		@ApiResponse(
			code = 200, 
			message = "Grupo atualizado") 
	})
	@ApiImplicitParam(
			name = "id", 
			value = "ID do grupo", 
			required = true, 
			dataType = "int", 
			paramType = "path",
			example = "1")
	ResponseEntity<?> putById(
			Long id, 
			@ApiParam(name = "body", value = "Objeto do grupo", required = true) 
			@Valid GroupRequest request);
	
	@ApiOperation("Deletar grupo pelo ID")
	@ApiResponses({ 
		@ApiResponse(
			code = 204, 
			message = "Grupo deletado") ,
		@ApiResponse(
			code = 404,
			message = "Grupo não encontrado",
			response = Problem.class)
	})
	@ApiImplicitParam(
			name = "id", 
			value = "ID do grupo", 
			required = true, 
			dataType = "int", 
			paramType = "path",
			example = "1")
	ResponseEntity<?> deleteById(Long id);

}