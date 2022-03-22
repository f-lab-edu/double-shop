package com.project.doubleshop.web.common.file.client;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@Profile("local")
@Component
@RequiredArgsConstructor
public class DefaultFileClient implements FileClient {

	private final Path root = Path.of("C:\\Users\\choi\\Desktop\\test");

	@Override
	public Object get(String path) {
		File file = new File(path);
		return file.getAbsolutePath();
	}

	@Override
	public Boolean delete(String url){
		try {
			Path path = Path.of(this.root + File.separator + url);
			if (Files.exists(path)) {
				FileSystemUtils.deleteRecursively(path);
				return true;
			}
			return false;
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
	public String upload(InputStream inputStream, long length, String key, String contentType, Map<String, String> metadata) {
		try {
			Files.copy(inputStream, this.root.resolve(Objects.requireNonNull(key)));
			return this.root + File.separator + key;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<String> getList(String path) {
		return null;
	}
}
