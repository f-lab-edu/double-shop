package com.project.doubleshop;



import com.project.doubleshop.domain.mapper.ItemMapper;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DoubleShopApplicationTests {

    @Autowired
    ItemMapper testItemMapper;

    @Test
    void contextLoads() {

    }
}
