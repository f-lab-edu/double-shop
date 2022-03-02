package com.project.doubleshop.web;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class HealthCheckController {
	@GetMapping(value = "hcheck", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Long healthCheck() {
		return System.currentTimeMillis();
	}
}
