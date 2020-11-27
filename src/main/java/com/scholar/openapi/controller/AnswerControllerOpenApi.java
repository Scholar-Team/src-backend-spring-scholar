package com.scholar.openapi.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.scholar.dto.AnswerDTO;
import com.scholar.exception.config.Problem;
import com.scholar.request.AnswerRequest;
import com.scholar.request.FileRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller das respostas")
public interface AnswerControllerOpenApi {
		
	@ApiOperation("Encontrar todas as respostas")
	@ApiResponses({ 
		@ApiResponse(
			code = 200, 
			message = "Todas as respostas do sistema",
			response = AnswerDTO.class)
	})
	ResponseEntity<?> findAll();
	
	@ApiOperation("Encontrar resposta pelo ID")
	@ApiResponses({ 
		@ApiResponse(
			code = 200, 
			message = "Resposta encontrada", 
			response = AnswerDTO.class),
		@ApiResponse(
			code = 404,
			message = "Resposta não encontrada",
			response = Problem.class)
	})
	@ApiImplicitParam(
			name = "id", 
			value = "ID da resposta", 
			required = true, 
			dataType = "int", 
			paramType = "path",
			example = "1")
	ResponseEntity<?> findById(Long Minid);
	
	@ApiOperation("Cadastrar uma resposta")
	@ApiResponses({ 
		@ApiResponse(code = 201, message = "Resposta cadastrada") 
	})
	ResponseEntity<?> save(
		@ApiParam(name = "body", value = "Objeto da resposta", required = true) 
		@Valid AnswerRequest request,
		FileRequest fileRequest
	);
	
	@ApiOperation("Atualizar uma resposta pelo ID")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "Resposta atualizada") 
	})
	@ApiImplicitParam(
			name = "id", 
			value = "ID da resposta", 
			required = true, 
			dataType = "int", 
			paramType = "path",
			example = "1")
	ResponseEntity<?> putById(
			Long id, 
			@ApiParam(name = "corpo", value = "Objeto da resposta", required = true) 
			@Valid AnswerRequest request);
	
	@ApiOperation("Deletar resposta pelo ID")
	@ApiResponses({ 
		@ApiResponse(
			code = 204, 
			message = "Resposta deletada"),
		@ApiResponse(
			code = 404,
			message = "Resposta não encontrada",
			response = Problem.class)
	})
	@ApiImplicitParam(
			name = "id", 
			value = "ID da resposta", 
			required = true, 
			dataType = "int", 
			paramType = "path",
			example = "1")
	ResponseEntity<?> deleteById(Long id);
}

