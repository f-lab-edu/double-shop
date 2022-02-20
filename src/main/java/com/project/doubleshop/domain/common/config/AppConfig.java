package com.project.doubleshop.domain.common.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@Profile("prod")
public class AppConfig {
	@Value("${mysql-datasource}")
	private String dataSourceUrl;

	@Value("${mysql-username}")
	private String dataSourceUsername;

	@Value("${mysql-password}")
	private String dataSourcePassword;

	@Bean
	public DataSource dataSource() {
		log.info("Get dataSource from vm");
		return DataSourceBuilder.create()
			.url(dataSourceUrl)
			.username(dataSourceUsername)
			.password(dataSourcePassword)
			.build();
	}
}
