package com.scholar.openapi.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.scholar.dto.DirectorDTO;
import com.scholar.exception.config.Problem;
import com.scholar.request.DirectorRequest;
import com.scholar.request.FileRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller dos diretores")
public interface DirectorControllerOpenApi {

	@ApiOperation("Encontrar todos os diretores")
	@ApiResponses({ 
		@ApiResponse(
			code = 200, 
			message = "Todos os diretores do sistema",
			response = DirectorDTO.class)
	})
	ResponseEntity<?> findAll();
	
	@ApiOperation("Encontrar diretor pelo ID")
	@ApiResponses({ 
		@ApiResponse(
			code = 200, 
			message = "Diretor encontrado", 
			response = DirectorDTO.class),
		@ApiResponse(
			code = 404,
			message = "Diretor não encontrado",
			response = Problem.class)
	})
	@ApiImplicitParam(
			name = "id", 
			value = "ID do diretor", 
			required = true, 
			dataType = "int", 
			paramType = "path",
			example = "1")
	ResponseEntity<?> findById(Long id);
	
	@ApiOperation("Cadastrar um diretor")
	@ApiResponses({ 
		@ApiResponse(
			code = 201,
			message = "Diretor cadastrado") 
	})
	ResponseEntity<?> save(
		@ApiParam(name = "body", value = "Objeto do diretor", required = true) 
		@Valid DirectorRequest request,
		FileRequest fileRequest
	);
	
	@ApiOperation("Atualizar um diretor pelo ID")
	@ApiResponses({ 
		@ApiResponse(
			code = 200,
			message = "Diretor atualizado") 
	})
	@ApiImplicitParam(
			name = "id", 
			value = "ID do diretor", 
			required = true, 
			dataType = "int", 
			paramType = "path",
			example = "1")
	ResponseEntity<?> putById(
			Long id, 
			@ApiParam(name = "body", value = "Objeto do diretor", required = true) 
			@Valid DirectorRequest request);
	
	@ApiOperation("Deletar diretor pelo ID")
	@ApiResponses({ 
		@ApiResponse(
			code = 204, 
			message = "Diretor deletado") ,
		@ApiResponse(
			code = 404,
			message = "Diretor não encontrado",
			response = Problem.class)
	})
	@ApiImplicitParam(
			name = "id", 
			value = "ID do diretor", 
			required = true, 
			dataType = "int", 
			paramType = "path",
			example = "1")
	ResponseEntity<?> deleteById(Long id);
	
}
