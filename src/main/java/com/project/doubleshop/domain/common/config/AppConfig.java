package com.project.doubleshop.domain.common.config;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.project.doubleshop.web.common.file.ImageFile;
import com.project.doubleshop.web.common.file.client.DefaultFileClient;
import com.project.doubleshop.web.common.file.client.FileClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class AppConfig {
	@Value("${mysql-datasource}")
	private String dataSourceUrl;

	@Value("${mysql-username}")
	private String dataSourceUsername;

	@Value("${mysql-password}")
	private String dataSourcePassword;

	@Bean
	public DataSource dataSource() {
		return DataSourceBuilder.create()
			.url(dataSourceUrl)
			.username(dataSourceUsername)
			.password(dataSourcePassword)
			.build();
	}

	@Bean
	public FileClient configFileClient() {
		return new DefaultFileClient();
	}

	@Bean
	public Jackson2ObjectMapperBuilder configureObjectMapper() {
		// Java time module
		JavaTimeModule jtm = new JavaTimeModule();
		jtm.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME));
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
			@Override
			public void configure(ObjectMapper objectMapper) {
				super.configure(objectMapper);
				objectMapper.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
				objectMapper.setVisibility(PropertyAccessor.IS_GETTER, JsonAutoDetect.Visibility.NONE);
				objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
			}
		};
		builder.serializationInclusion(JsonInclude.Include.NON_NULL);
		builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		builder.modulesToInstall(jtm);
		return builder;
	}

	@Bean
	public PageableHandlerMethodArgumentResolverCustomizer customize() {
		return resolver -> {
			resolver.setOneIndexedParameters(true);
			resolver.setFallbackPageable(PageRequest.of(0, 9, Sort.Direction.DESC, "id"));
		};
	}
}