package com.project.doubleshop.web.common.file.client;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DefaultFileClient implements FileClient {

	private final Path root = Path.of("C:\\Users\\choi\\Desktop\\test");

	@Override
	public String getUrlPath(String path) {
		File file = new File(path);
		return file.getAbsolutePath();
	}

	@Override
	public String upload(MultipartFile file) {
		try {
			Files.copy(file.getInputStream(), this.root.resolve(Objects.requireNonNull(file.getOriginalFilename())));
			return file.getResource().getURL().toString();
		} catch (IOException e) {
			throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		}
	}

	@Override
	public String delete(String url){
		try {
			Path path = Path.of(this.root + File.separator + url);
			if (Files.exists(path)) {
				FileSystemUtils.deleteRecursively(path);
				return "success";
			}
			return "fail";
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String upload(InputStream inputStream, String key) {
		try {
			Files.copy(inputStream, this.root.resolve(Objects.requireNonNull(key)));
			return this.root + File.separator + key;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String upload(InputStream inputStream, String key, String base) {
		try {
			Path path = Path.of(this.root + File.separator + base);
			if (!Files.exists(path)) {
				Files.createDirectory(Path.of(String.valueOf(path)));
			}
			Files.copy(inputStream, Path.of(String.valueOf(path)).resolve(Objects.requireNonNull(key)));
			return this.root + File.separator + base + File.separator + key;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
