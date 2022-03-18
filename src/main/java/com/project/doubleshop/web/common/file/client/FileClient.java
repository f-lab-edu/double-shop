package com.project.doubleshop.web.common.file.client;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface FileClient {
	Object get(String key);
	String delete(String url);

	String upload(InputStream inputStream, String key);

	String upload(InputStream inputStream, long length, String key, String contentType, Map<String, String> metadata);

	List<String> getList(String path);
}
