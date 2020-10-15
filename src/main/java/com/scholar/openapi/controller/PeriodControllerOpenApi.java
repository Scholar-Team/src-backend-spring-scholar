package com.scholar.openapi.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.scholar.dto.PeriodDTO;
import com.scholar.exception.config.Problem;
import com.scholar.request.PeriodRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller dos períodos")
public interface PeriodControllerOpenApi {

	@ApiOperation("Encontrar todos os períodos")
	@ApiResponses({ 
		@ApiResponse(
			code = 200, 
			message = "Todos os períodos do sistema",
			response = PeriodDTO.class)
	})
	ResponseEntity<?> findAll();
	
	@ApiOperation("Encontrar período pelo ID")
	@ApiResponses({ 
		@ApiResponse(
			code = 200, 
			message = "Período encontrado", 
			response = PeriodDTO.class),
		@ApiResponse(
			code = 404,
			message = "Período não encontrado",
			response = Problem.class)
	})
	@ApiImplicitParam(
			name = "id", 
			value = "ID do período", 
			required = true, 
			dataType = "int", 
			paramType = "path",
			example = "1")
	ResponseEntity<?> findById(Long id);
	
	@ApiOperation("Cadastrar um período")
	@ApiResponses({
		@ApiResponse(
			code = 201, 
			message = "Período cadastrado",
			response = PeriodDTO.class) 
	})
	ResponseEntity<?> save(
		@ApiParam(name = "body", value = "Objeto do período", required = true) 
		@Valid PeriodRequest request
	);
	
	@ApiOperation("Atualizar um período pelo ID")
	@ApiResponses({ 
		@ApiResponse(
			code = 200, 
			message = "Período atualizado") 
	})
	@ApiImplicitParam(
			name = "id", 
			value = "ID do período", 
			required = true, 
			dataType = "int", 
			paramType = "path",
			example = "1")
	ResponseEntity<?> putById(
			Long id, 
			@ApiParam(name = "body", value = "Objeto do período", required = true) 
			@Valid PeriodRequest request);
	
	@ApiOperation("Deletar período pelo ID")
	@ApiResponses({ 
		@ApiResponse(
			code = 204, 
			message = "Período deletado") ,
		@ApiResponse(
			code = 404,
			message = "Período não encontrado",
			response = Problem.class)
	})
	@ApiImplicitParam(
			name = "id", 
			value = "ID do período", 
			required = true, 
			dataType = "int", 
			paramType = "path",
			example = "1")
	ResponseEntity<?> deleteById(Long id);

}
