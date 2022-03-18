package com.project.doubleshop.web.common.file.client;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GetObjectAclRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

import lombok.RequiredArgsConstructor;

@Profile("local")
@Component
@RequiredArgsConstructor
public class AwsS3Client implements FileClient {

	@Value("${s3-url}")
	private String url;

	@Value("${s3-bucketName}")
	private String bucketName;

	private final AmazonS3 amazonS3;

	@Override
	public S3Object get(String key) {
		GetObjectRequest request = new GetObjectRequest(bucketName, key);
		return amazonS3.getObject(request);
	}

	@Override
	public String upload(InputStream inputStream, String key) {
		return null;
	}

	@Override
	public String upload(InputStream inputStream, long length, String key, String contentType,
		Map<String, String> metadata) {
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentLength(length);
		objectMetadata.setContentType(contentType);
		if (metadata != null && !metadata.isEmpty()) {
			objectMetadata.setUserMetadata(metadata);
		}
		PutObjectRequest request = new PutObjectRequest(bucketName, key, inputStream, objectMetadata);
		return executePut(request);
	}

	@Override
	public String delete(String url) {
		String key = "getName(url)";
		DeleteObjectRequest request = new DeleteObjectRequest(bucketName, key);
		executeDelete(request);
		return null;
	}



	private String executePut(PutObjectRequest request) {
		amazonS3.putObject(request.withCannedAcl(CannedAccessControlList.PublicRead));
		if (!url.endsWith("/")) {
			url = "/" + url + "/" + request.getKey();
		}
		return url;
	}

	private void executeDelete(DeleteObjectRequest request) {
		amazonS3.deleteObject(request);
	}
}
