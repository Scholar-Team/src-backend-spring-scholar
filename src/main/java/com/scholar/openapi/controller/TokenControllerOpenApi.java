package com.scholar.openapi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller dos tokens")
public interface TokenControllerOpenApi {

	@ApiOperation("Revogar token")
	@ApiResponses({ 
		@ApiResponse(
			code = 200, 
			message = "Token revogado")
	})
	ResponseEntity<?> revoke(HttpServletRequest req, HttpServletResponse resp);
}
