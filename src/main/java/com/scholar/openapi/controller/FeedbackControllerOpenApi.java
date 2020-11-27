package com.scholar.openapi.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.scholar.dto.FeedbackDTO;
import com.scholar.exception.config.Problem;
import com.scholar.request.FeedbackRequest;
import com.scholar.request.FileRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller dos feedbacks")
public interface FeedbackControllerOpenApi {

	@ApiOperation("Encontrar todos os feedbacks")
	@ApiResponses({ 
		@ApiResponse(
			code = 200, 
			message = "Todos os feedbacks do sistema",
			response = FeedbackDTO.class)
	})
	ResponseEntity<?> findAll();
	
	@ApiOperation("Encontrar feedback pelo ID")
	@ApiResponses({ 
		@ApiResponse(
			code = 200, 
			message = "Feedback encontrado", 
			response = FeedbackDTO.class),
		@ApiResponse(
			code = 404,
			message = "Feedback não encontrado",
			response = Problem.class)
	})
	@ApiImplicitParam(
			name = "id", 
			value = "ID do feedback", 
			required = true, 
			dataType = "int", 
			paramType = "path",
			example = "1")
	ResponseEntity<?> findById(Long id);
	
	@ApiOperation("Cadastrar um feedback")
	@ApiResponses({ 
		@ApiResponse(code = 201, message = "Feedback cadastrado") 
	})
	ResponseEntity<?> save(
		@ApiParam(name = "body", value = "Objeto do feedback", required = true) 
		@Valid FeedbackRequest request,
		FileRequest fileRequest
	);
	
	@ApiOperation("Atualizar um feedback pelo ID")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "Feedback atualizado") 
	})
	@ApiImplicitParam(
			name = "id", 
			value = "ID do feedback", 
			required = true, 
			dataType = "int", 
			paramType = "path",
			example = "1")
	ResponseEntity<?> putById(
			Long id, 
			@ApiParam(name = "body", value = "Objeto do feedback", required = true) 
			@Valid FeedbackRequest request);
	
	@ApiOperation("Deletar feedback pelo ID")
	@ApiResponses({ 
		@ApiResponse(
			code = 204, 
			message = "Feedback deletado") ,
		@ApiResponse(
			code = 404,
			message = "Feedback não encontrado",
			response = Problem.class)
	})
	@ApiImplicitParam(
			name = "id", 
			value = "ID do feedback", 
			required = true, 
			dataType = "int", 
			paramType = "path",
			example = "1")
	ResponseEntity<?> deleteById(Long id);

}
