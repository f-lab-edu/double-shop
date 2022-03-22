package com.project.doubleshop.web.common.file.client;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface FileClient {
	Object get(String key);
	Boolean delete(String url);

	String upload(InputStream inputStream, String key);

	String upload(InputStream inputStream, long length, String key, String contentType, Map<String, String> metadata);

	List<String> getList(String path);
}
