package com.project.doubleshop.web.common.file.client;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface FileClient {
	String getUrlPath(String key) throws IOException;
	String upload(MultipartFile file);
	String delete(String url) throws IOException;

	String upload(InputStream inputStream, String key);

	String upload(InputStream inputStream, String key, String base);
}
