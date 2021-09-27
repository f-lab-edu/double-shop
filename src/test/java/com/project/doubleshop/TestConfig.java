package com.project.doubleshop;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@TestConfiguration
public class TestConfig {
    private final ResourceLoader resourceLoader;

    public TestConfig(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder(resourceLoader)
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:test/schema.sql")
                .addScript("classpath:test/data.sql")
                .build();
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setConfigLocation(resourceLoader.getResource("classpath:test/mybatis-test-config.xml"));
        return factoryBean.getObject();
    }
}
