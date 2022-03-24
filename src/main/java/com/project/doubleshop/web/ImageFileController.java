package com.project.doubleshop.web;

import static com.project.doubleshop.web.common.file.ImageFile.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

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

import com.project.doubleshop.web.common.dto.FileRequest;
import com.project.doubleshop.web.common.file.client.FileClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class ImageFileController {

	private final FileClient fileClient;

	@GetMapping(value = "file", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> getImageUrls(@ModelAttribute FileRequest fileRequest) {
		return ResponseEntity.ok(fileClient.getList(fileRequest.getPath()));
	}

	@PostMapping(value = "file/sync", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> resultSync(@ModelAttribute FileRequest fileRequest, @RequestPart(required = false) MultipartFile file) {
		return ResponseEntity.ok(uploadImageFile(fileClient, of(file), fileRequest.getPath()));
	}

	@PostMapping(value = "file/async", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> resultAsync(@ModelAttribute FileRequest fileRequest, @RequestPart(required = false) MultipartFile file) {
		return ResponseEntity.ok(uploadAsyncImageFile(fileClient, of(file), fileRequest.getPath()));
	}

	@DeleteMapping("file")
	public ResponseEntity<Boolean> deleteFile(@RequestBody Map<String, String> map) {
		return ResponseEntity.ok(fileClient.delete(map.get("path")));
	}
}
