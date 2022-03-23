package com.project.doubleshop.web.common.file;



import static com.project.doubleshop.web.common.file.FileUtils.*;
import static org.springframework.util.StringUtils.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.project.doubleshop.web.common.file.client.FileClient;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
		String name = hasLength(basePath) ?  basePath + "/" + UUID.randomUUID() : UUID.randomUUID().toString();
		return name + "." + extension(defaultExtension);
	}

	public static String uploadImageFile(FileClient fileClient, ImageFile file, String path) {
		log.info("upload image file");

		if (file != null) {
			String key = file.randomName(path, "jpeg");
			try {
				return fileClient.upload(file.inputStream(), file.length(), key, file.getContentType(), null);
			} catch (AmazonS3Exception e) {
				log.warn("Amazon S3 error (key: {}): {}", key, e.getMessage(), e);
			}
		}
		return null;
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
