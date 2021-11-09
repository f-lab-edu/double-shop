package com.project.doubleshop;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DoubleShopApplicationTests {

    static {
        System.setProperty("spring.config.location",
                "classpath:/application.yml,classpath:/application-keystore.yml");
    }

    @Test
    void contextLoads() {
    }
  
}
