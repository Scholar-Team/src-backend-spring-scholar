package com.scholar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.scholar.controller.generic.BaseController;
import com.scholar.dto.FeedbackDTO;
import com.scholar.model.Feedback;
import com.scholar.openapi.controller.FeedbackControllerOpenApi;
import com.scholar.request.FeedbackRequest;
import com.scholar.request.FileRequest;
import com.scholar.security.permissions.CheckAdministrator;
import com.scholar.service.FeedbackService;

@RestController
@CrossOrigin
@RequestMapping("/feedback")
public class FeedbackController implements FeedbackControllerOpenApi {
	
	private FeedbackService service;
	private BaseController<Feedback, 
		FeedbackDTO, FeedbackRequest> base;
	
	@Autowired
	public FeedbackController(FeedbackService service) {		
		this.service = service;
		this.base = new BaseController<>(service);
	}
	
	@Override
	@CheckAdministrator.ViewAllData
	@GetMapping
	public ResponseEntity<?> findAll() {
		return base.findAll();
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		return base.findById(id);
	}

	@Override
	@PostMapping
	public ResponseEntity<?> save(
			@RequestPart(name = "feedback", required = true) FeedbackRequest request,
			FileRequest fileRequest) {
		
		request.setAttachment(fileRequest);
		return base.save(request);
	}

	@Override
	@PutMapping("/{id}")
	public ResponseEntity<?> putById(
			@PathVariable Long id,
			@RequestBody FeedbackRequest request) {
		
		return base.putById(id, request);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<?> patchById(
			@PathVariable Long id, 
			@RequestBody FeedbackRequest request) {
		
		return base.patchById(id, request);
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		return base.deleteById(id);
	}

}
