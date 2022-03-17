package com.project.doubleshop.web;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.doubleshop.web.common.dto.FileRequest;
import com.project.doubleshop.web.common.file.ImageFile;
import com.project.doubleshop.web.common.file.client.FileClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class FileTestController {

	private final FileClient fileClient;

	@GetMapping(value = "file", produces = MediaType.APPLICATION_JSON_VALUE)
	public String checkFile() throws IOException {
		return fileClient.getUrlPath("classpath:test/hello.jpeg");
	}

	@PostMapping(value = "file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String result(@ModelAttribute FileRequest fileRequest, @RequestPart(required = false) MultipartFile[] files) {
		Arrays.asList(files).forEach(
			file -> uploadImageFile(ImageFile.of(file), fileRequest.getName()).orElseThrow());
		return "hi";
	}

	private Optional<String> uploadImageFile(ImageFile file, String name) {
		String fileUrl = null;
		if (file != null) {
			String key = file.randomName("imageFile", "jpeg");
			try {
				fileUrl = fileClient.upload(file.inputStream(), key, name);
			} catch (RuntimeException e) {
				log.warn(e.getMessage());
			}
		}
		return Optional.ofNullable(fileUrl);
	}

	@DeleteMapping("file")
	public ResponseEntity<String> deleteFile(@RequestBody Map<String, String> map) {
		return ResponseEntity.ok(fileClient.delete(map.get("name")));
	}
}
