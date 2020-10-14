package com.scholar.openapi.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.scholar.dto.ActivityDTO;
import com.scholar.exception.config.Problem;
import com.scholar.request.ActivityRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller das atividades/lições")
public interface ActivityControllerOpenApi {
		
	@ApiOperation("Encontrar todas as atividades")
	@ApiResponses({ 
		@ApiResponse(
			code = 200, 
			message = "Todas as atividades do sistema",
			response = ActivityDTO.class)
	})
	ResponseEntity<?> findAll();
	
	@ApiOperation("Encontrar atividade pelo ID")
	@ApiResponses({ 
		@ApiResponse(
			code = 200, 
			message = "Atividade encontrada", 
			response = ActivityDTO.class),
		@ApiResponse(
			code = 404,
			message = "Atividade não encontrada",
			response = Problem.class)
	})
	@ApiImplicitParam(
			name = "id", 
			value = "ID da Atividade", 
			required = true, 
			dataType = "int", 
			paramType = "path",
			example = "1")
	ResponseEntity<?> findById(Long Minid);
	
	@ApiOperation("Cadastrar uma atividade")
	@ApiResponses({ 
		@ApiResponse(code = 201, message = "Atividade cadastrada") 
	})
	ResponseEntity<?> save(
		@ApiParam(name = "body", value = "Objeto da atividade", required = true) 
		@Valid ActivityRequest request
	);
	
	@ApiOperation("Atualizar uma atividade pelo ID")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "Atividade atualizada") 
	})
	@ApiImplicitParam(
			name = "id", 
			value = "ID da Atividade", 
			required = true, 
			dataType = "int", 
			paramType = "path",
			example = "1")
	ResponseEntity<?> putById(
			Long id, 
			@ApiParam(name = "corpo", value = "Objeto da atividade", required = true) 
			@Valid ActivityRequest request);
	
	@ApiOperation("Deletar atividade pelo ID")
	@ApiResponses({ 
		@ApiResponse(
			code = 204, 
			message = "Atividade deletada"),
		@ApiResponse(
			code = 404,
			message = "Atividade não encontrada",
			response = Problem.class)
	})
	@ApiImplicitParam(
			name = "id", 
			value = "ID da Atividade", 
			required = true, 
			dataType = "int", 
			paramType = "path",
			example = "1")
	ResponseEntity<?> deleteById(Long id);

}
