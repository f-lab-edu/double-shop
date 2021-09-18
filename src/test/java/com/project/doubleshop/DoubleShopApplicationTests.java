package com.project.doubleshop;

import com.project.doubleshop.test.domain.entity.Article;
import com.project.doubleshop.test.domain.entity.Item;
import com.project.doubleshop.test.domain.mapper.ArticleMapper;
import com.project.doubleshop.test.domain.mapper.ItemMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration(classes = TestConfig.class)
class DoubleShopApplicationTests {

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    ItemMapper itemMapper;

    @Test
    void contextLoads() {
        // given
        Article article = articleMapper.selectById(1L);
        int affectedColumnNum = itemMapper.insertItem(Item.builder()
                .name("마법천자문. 1: 불어라! 바람 풍")
                .description("스토리에 몰입하다보니 정작 한자는 한자도 생각도 나지않는 책")
                .price(10800)
                .unit("권")
                .volume("1")
                .length("1")
                .packageType("종이박스")
                .certificateOfAnalysis("1")
                .expiration("1")
                .pricePer100g(0)
                .allergicIssue("1")
                .modelSerialNo("175014350 - 500109051")
                .ratePoint(0)
                .searchKeyword("교육")
                .stock(132)
                .discountPrice(0)
                .onedayEligible(true)
                .freshEligible(false)
                .build());

        itemMapper.insertItem(Item.builder()
                .name("마법천자문. 2: 불어라! 바람 풍")
                .description("스토리에 몰입하다보니 정작 한자는 한자도 생각도 나지않는 책")
                .price(10800)
                .unit("권")
                .volume("1")
                .length("1")
                .packageType("종이박스")
                .certificateOfAnalysis("1")
                .expiration("1")
                .pricePer100g(0)
                .allergicIssue("1")
                .modelSerialNo("175014350 - 500109051")
                .ratePoint(0)
                .searchKeyword("교육")
                .stock(132)
                .discountPrice(0)
                .onedayEligible(true)
                .freshEligible(false)
                .build());

        // when
        Item item = itemMapper.selectById(1L);
        Item item2 = itemMapper.selectById(2L);
        String changeItemName = "바른생활";
        item.setName(changeItemName);
        itemMapper.updateItem(item);
        itemMapper.deleteItem(item2.getId());

        // then
        assertThat(affectedColumnNum).isSameAs(1);
        assertThat(item).isNotNull();
        assertThat(itemMapper.selectById(2L)).isNull();
        assertThat(item.getId()).isEqualTo(1L);
        assertThat(item.getName()).isEqualTo(changeItemName);
        assertThat(article).isNotNull();
        assertThat(article.getId()).isEqualTo(1L);
    }
}
