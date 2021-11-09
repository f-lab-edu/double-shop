package com.project.doubleshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DoubleShopApplication {

    static {
        System.setProperty("spring.config.location",
                "classpath:/application.yml,classpath:/application-keystore.yml");
    }

    public static void main(String[] args) {
        SpringApplication.run(DoubleShopApplication.class, args);
    }

}
