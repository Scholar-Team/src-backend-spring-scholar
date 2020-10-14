package com.scholar.openapi.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.scholar.dto.TelephoneDTO;
import com.scholar.exception.config.Problem;
import com.scholar.request.TelephoneRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller dos telefones")
public interface TelephoneControllerOpenApi {

	@ApiOperation("Encontrar todos os telefones")
	@ApiResponses({ 
		@ApiResponse(
			code = 200, 
			message = "Todos os telefones do sistema",
			response = TelephoneDTO.class)
	})
	ResponseEntity<?> findAll();
	
	@ApiOperation("Encontrar telefone pelo ID")
	@ApiResponses({ 
		@ApiResponse(
			code = 200, 
			message = "Telefone encontrado", 
			response = TelephoneDTO.class),
		@ApiResponse(
			code = 404,
			message = "Telefone não encontrado",
			response = Problem.class)
	})
	@ApiImplicitParam(
			name = "id", 
			value = "ID do telefone", 
			required = true, 
			dataType = "int", 
			paramType = "path",
			example = "1")
	ResponseEntity<?> findById(Long id);
	
	@ApiOperation("Cadastrar um telefone")
	@ApiResponses({ 
		@ApiResponse(code = 201, message = "Telefone cadastrado") 
	})
	ResponseEntity<?> save(
		@ApiParam(name = "body", value = "Objeto do telefone", required = true) 
		@Valid TelephoneRequest request
	);
	
	@ApiOperation("Atualizar um telefone pelo ID")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "Telefone atualizado") 
	})
	@ApiImplicitParam(
			name = "id", 
			value = "ID do telefone", 
			required = true, 
			dataType = "int", 
			paramType = "path",
			example = "1")
	ResponseEntity<?> putById(
			Long id, 
			@ApiParam(name = "body", value = "Objeto do telefone", required = true) 
			@Valid TelephoneRequest request);
	
	@ApiOperation("Deletar telefone pelo ID")
	@ApiResponses({ 
		@ApiResponse(
			code = 204, 
			message = "Telefone deletado") ,
		@ApiResponse(
			code = 404,
			message = "Telefone não encontrado",
			response = Problem.class)
	})
	@ApiImplicitParam(
			name = "id", 
			value = "ID do telefone", 
			required = true, 
			dataType = "int", 
			paramType = "path",
			example = "1")
	ResponseEntity<?> deleteById(Long id);

}
