package com.project.doubleshop.web.common.file.client;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Profile("prod")
@Slf4j
@Component
@RequiredArgsConstructor
public class AwsS3Client extends DefaultFileClient {

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
	public List<String> getList(String path) {
		ListObjectsRequest request = new ListObjectsRequest()
			.withBucketName(bucketName)
			.withPrefix(path);

		List<String> keys = new ArrayList<>();

		ObjectListing objects;
		do {
			objects = amazonS3.listObjects(request);
			for (S3ObjectSummary objectSummary : objects.getObjectSummaries()) {
				String summaryKey = objectSummary.getKey();
				keys.add(summaryKey);
			}
		} while (objects.isTruncated());
		return keys;
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

	@Async
	@Override
	public Future<String> uploadAsync(InputStream inputStream, long length, String key, String contentType,
		Map<String, String> metadata) {
		return new AsyncResult<>(upload(inputStream, length, key, contentType, metadata));
	}

	@Override
	public Boolean delete(String key) {
		DeleteObjectRequest request = new DeleteObjectRequest(bucketName, key);
		executeDelete(request);
		return true;
	}



	private String executePut(PutObjectRequest request) {
		amazonS3.putObject(request.withCannedAcl(CannedAccessControlList.PublicRead));
		String result = request.getKey();
		log.debug("s3 object key {} created.", result);
		return result;
	}

	private void executeDelete(DeleteObjectRequest request) {
		amazonS3.deleteObject(request);
	}
}
