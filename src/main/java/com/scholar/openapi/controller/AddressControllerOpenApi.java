package com.scholar.openapi.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.scholar.dto.AddressDTO;
import com.scholar.exception.config.Problem;
import com.scholar.request.AddressRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller dos endereços")
public interface AddressControllerOpenApi {

	@ApiOperation("Encontrar todos os endereços")
	@ApiResponses({ 
		@ApiResponse(
			code = 200, 
			message = "Todos os endereços do sistema",
			response = AddressDTO.class)
	})
	ResponseEntity<?> findAll();
	
	@ApiOperation("Encontrar endereço pelo ID")
	@ApiResponses({ 
		@ApiResponse(
			code = 200, 
			message = "Endereço encontrado", 
			response = AddressDTO.class),
		@ApiResponse(
			code = 404,
			message = "Endereço não encontrado",
			response = Problem.class)
	})
	@ApiImplicitParam(
			name = "id", 
			value = "ID do Endereço", 
			required = true, 
			dataType = "int", 
			paramType = "path",
			example = "1")
	ResponseEntity<?> findById(Long id);
	
	@ApiOperation("Cadastrar um Endereço")
	@ApiResponses({ 
		@ApiResponse(code = 201, message = "Endereço cadastrado") 
	})
	ResponseEntity<?> save(
		@ApiParam(name = "body", value = "Objeto do Endereço", required = true) 
		@Valid AddressRequest request
	);
	
	@ApiOperation("Atualizar um Endereço pelo ID")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "Endereço atualizado") 
	})
	@ApiImplicitParam(
			name = "id", 
			value = "ID do Endereço", 
			required = true, 
			dataType = "int", 
			paramType = "path",
			example = "1")
	ResponseEntity<?> putById(
			Long id, 
			@ApiParam(name = "body", value = "Objeto do Endereço", required = true) 
			@Valid AddressRequest request);
	
	@ApiOperation("Deletar Endereço pelo ID")
	@ApiResponses({ 
		@ApiResponse(
			code = 204, 
			message = "Endereço deletado") ,
		@ApiResponse(
			code = 404,
			message = "Endereço não encontrado",
			response = Problem.class)
	})
	@ApiImplicitParam(
			name = "id", 
			value = "ID do Endereço", 
			required = true, 
			dataType = "int", 
			paramType = "path",
			example = "1")
	ResponseEntity<?> deleteById(Long id);

}
