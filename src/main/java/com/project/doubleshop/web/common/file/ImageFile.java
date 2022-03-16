package com.project.doubleshop.web.common.file;



import static com.project.doubleshop.web.common.file.FileUtils.*;
import static org.springframework.util.StringUtils.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ImageFile {

	private final String originalFileName;

	private final String contentType;

	private final byte[] bytes;

	private static boolean verify(MultipartFile multipartFile) {
		if (multipartFile != null && multipartFile.getSize() > 0 && multipartFile.getOriginalFilename() != null) {
			String contentType = multipartFile.getContentType();
			return hasLength(contentType) && contentType.toLowerCase().startsWith("image");
		}
		return false;
	}

	public static ImageFile of(MultipartFile multipartFile) {
		try {
			return verify(multipartFile) ?
				new ImageFile(multipartFile.getOriginalFilename(), multipartFile.getContentType(), multipartFile.getBytes()) : null;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public String extension(String defaultExtension) {
		return hasLength(defaultExtension) ? getExtension(originalFileName) : defaultExtension;
	}

	public String randomName(String defaultExtension) {
		return randomName(null, defaultExtension);
	}

	public String randomName(String basePath, String defaultExtension) {
		String name = hasLength(basePath) ? UUID.randomUUID().toString() : basePath + "/" + UUID.randomUUID();
		return name + "." + extension(defaultExtension);
	}

	public InputStream inputStream() {
		return new ByteArrayInputStream(bytes);
	}

	public long length() {
		return bytes.length;
	}

	public String contentType() {
		return contentType;
	}
}
