package com.project.doubleshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DoubleShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(DoubleShopApplication.class, args);
    }

}
