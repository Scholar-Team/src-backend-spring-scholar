package com.scholar.openapi.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.scholar.dto.FileDTO;
import com.scholar.exception.config.Problem;
import com.scholar.request.FileRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller dos arquivos")
public interface FileControllerOpenApi {

	@ApiOperation("Encontrar todos os arquivos")
	@ApiResponses({ 
		@ApiResponse(
			code = 200, 
			message = "Todos os arquivos do sistema",
			response = FileDTO.class)
	})
	ResponseEntity<?> findAll();
	
	@ApiOperation("Encontrar arquivo pelo ID")
	@ApiResponses({ 
		@ApiResponse(
			code = 200, 
			message = "Arquivo encontrado", 
			response = FileDTO.class),
		@ApiResponse(
			code = 404,
			message = "Arquivo não encontrado",
			response = Problem.class)
	})
	@ApiImplicitParam(
			name = "id", 
			value = "ID do arquivo", 
			required = true, 
			dataType = "int", 
			paramType = "path",
			example = "1")
	ResponseEntity<?> findById(Long id);
	
	@ApiOperation("Cadastrar um arquivo")
	@ApiResponses({ 
		@ApiResponse(code = 201, message = "arquivo cadastrado") 
	})
	ResponseEntity<?> save(
		@ApiParam(name = "body", value = "Objeto do arquivo", required = true) 
		@Valid FileRequest request
	);
	
	@ApiOperation("Atualizar um arquivo pelo ID")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "arquivo atualizado") 
	})
	@ApiImplicitParam(
			name = "id", 
			value = "ID do arquivo", 
			required = true, 
			dataType = "int", 
			paramType = "path",
			example = "1")
	ResponseEntity<?> putById(
			Long id, 
			@ApiParam(name = "body", value = "Objeto do arquivo", required = true) 
			@Valid FileRequest request);
	
	@ApiOperation("Deletar arquivo pelo ID")
	@ApiResponses({ 
		@ApiResponse(
			code = 204, 
			message = "Arquivo deletado") ,
		@ApiResponse(
			code = 404,
			message = "Arquivo não encontrado",
			response = Problem.class)
	})
	@ApiImplicitParam(
			name = "id", 
			value = "ID do arquivo", 
			required = true, 
			dataType = "int", 
			paramType = "path",
			example = "1")
	ResponseEntity<?> deleteById(Long id);

}
