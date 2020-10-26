package com.scholar.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scholar.openapi.controller.TokenControllerOpenApi;

@RestController
@CrossOrigin
@RequestMapping("/token")
public class TokenController implements TokenControllerOpenApi {

	@Override
	@DeleteMapping("/revoke")
	public ResponseEntity<?> revoke(HttpServletRequest req, HttpServletResponse resp) {
		Cookie cookie = new Cookie("refreshToken", null);
		cookie.setHttpOnly(true);
		cookie.setSecure(false);
		cookie.setPath(req.getContextPath() + "/oauth/token");
		cookie.setMaxAge(0);

		resp.addCookie(cookie);
		resp.setStatus(HttpStatus.NO_CONTENT.value());
		
		return ResponseEntity.ok().build();
	}

}
