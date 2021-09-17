package com.project.doubleshop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration(classes = TestConfig.class)
class DoubleShopApplicationTests {

    @Autowired
    ArticleMapper articleMapper;

    @Test
    void contextLoads() {
        Article article = articleMapper.getArticle(1L);

        assertThat(article).isNotNull();
        assertThat(article.getId()).isEqualTo(1L);
    }

}
