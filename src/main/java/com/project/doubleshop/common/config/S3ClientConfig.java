package com.project.doubleshop.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Profile("prod")
public class S3ClientConfig {
	@Value("${s3-access-key}")
	private String accessKey;

	@Value("${s3-secrete-key}")
	private String secreteKey;

	@Value("${s3-region}")
	private String region;

	@Bean
	public AmazonS3 amazonS3Client() {
		return AmazonS3ClientBuilder.standard()
			.withRegion(Regions.fromName(region))
			.withCredentials(
				new AWSStaticCredentialsProvider(
					new BasicAWSCredentials(
						accessKey,
						secreteKey
					)
				)
			).build();
	}
}
