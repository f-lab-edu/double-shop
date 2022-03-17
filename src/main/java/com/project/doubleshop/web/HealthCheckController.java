package com.project.doubleshop.web;

import java.io.IOException;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.doubleshop.web.common.dto.FileRequest;
import com.project.doubleshop.web.common.file.ImageFile;
import com.project.doubleshop.web.common.file.client.FileClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class HealthCheckController {
	@GetMapping(value = "hcheck", produces = MediaType.APPLICATION_JSON_VALUE)
	public Long healthCheck() {
		return System.currentTimeMillis();
	}
}
