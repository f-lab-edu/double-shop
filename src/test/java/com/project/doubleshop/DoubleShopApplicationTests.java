package com.project.doubleshop;



import com.project.doubleshop.domain.entity.item.Item;
import com.project.doubleshop.domain.repository.mapper.ItemMapper;

import com.project.doubleshop.web.item.dto.ItemFormDTO;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ContextConfiguration(classes = TestConfig.class)
class DoubleShopApplicationTests {

    @Autowired
    ItemMapper testItemMapper;

    @Test
    @Order(1)
    @DisplayName("select 단건 테스트: item pk 1번에는 item 데이터가 존재한다.")
    void findOneItemById() {
        Item findItem = testItemMapper.selectById(1L);

        assertThat(findItem).isNotNull();
        assertThat(findItem.getId()).isEqualTo(1L);
    }

    @Test
    @Order(2)
    @DisplayName("select 목록 테스트: 모든 상품 목록을 검색할 경우, 상품 갯수는 총 14개여야 한다.")
    void findAllItems() {
        List<Item> allItems = testItemMapper.selectAllItems();

        assertThat(allItems).isNotNull();
        assertThat(allItems.size()).isEqualTo(14);
    }

    @Test
    @Order(3)
    @DisplayName("update 테스트: 1번 상품의 이름을 바꿀 경우, 기존 1번 상품의 이름과 달라야 한다.")
    void updateOneItem() {
        Item item = testItemMapper.selectById(1L);
        String beforeChangeName = item.getName();
        Item newItem = Item.createItem(ItemFormDTO.builder()
            .name("updated Item")
            .build());
        testItemMapper.updateItem(newItem);
        String afterChangeNAme = newItem.getName();

        assertThat(item).isNotNull();
        assertThat(beforeChangeName).isNotEqualTo(afterChangeNAme);
    }

    @Test
    @Order(4)
    @DisplayName("delete 테스트: 1번 상품을 삭제할 경우, 기존의 상품 목록의 갯수보다 1개 적어야 한다.")
    void deleteOneItem() {
        int beforeDeleteItemSize = testItemMapper.selectAllItems().size();
        testItemMapper.deleteItem(1L);
        int afterDeleteItemSize = testItemMapper.selectAllItems().size();
        assertThat(beforeDeleteItemSize - 1).isSameAs(afterDeleteItemSize);
    }

    @Test
    @Order(5)
    @DisplayName("insert 테스트: 상품을 하나 등록할 경우, 추가된 로우는 하나여야만 한다.")
    void insertOneItem() {
        int affectedRowNum = testItemMapper.insertItem(Item.builder()
                .name("newItem")
                .brandName("newBrand")
                .description("newDescription")
                .price(1)
                .build());

        assertThat(affectedRowNum).isSameAs(1);

    }
}
