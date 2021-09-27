package com.project.doubleshop.domain.repository.item;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.project.doubleshop.domain.entity.item.Item;
import com.project.doubleshop.web.item.dto.ItemFormDTO;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ItemRepositoryTest {
	@Autowired
	ItemRepository itemRepository;

	@Test
	@Order(1)
	@DisplayName("상품 pk 1번에는 상품 데이터가 존재한다.")
	void findById() {
		Item item = itemRepository.findById(1L);

		assertThat(item).isNotNull();
		assertThat(item.getId()).isEqualTo(1L);
	}

	@Test
	@Order(2)
	@DisplayName("현재 상품 릴레이션에는 14개의 상품이 저장되어있다.")
	void findAll() {
		List<Item> items = itemRepository.findAll();

		assertThat(items).isNotNull();
		assertThat(items.size()).isEqualTo(14);
	}

	@Test
	@Order(3)
	@DisplayName("1번 상품의 이름을 바꿀 경우, 해당 릴레이션의 1번 상품의 이름은 바꾼 이름과 동일하다.")
	void updateOne() {
		Item beforeUpdateItem = itemRepository.findById(1L);
		Long id = beforeUpdateItem.getId();
		String changedName = "changed item name";

		Item updatedItemForm = Item.createItem(ItemFormDTO.builder()
			.id(id)
			.name(changedName)
			.description("update one item")
			.price(1)
			.build());

		boolean result = itemRepository.save(updatedItemForm);
		Item afterUpdateItem = itemRepository.findById(1L);

		assertThat(result).isTrue();
		assertThat(afterUpdateItem).isNotNull();
		assertThat(afterUpdateItem.getName()).isEqualTo(changedName);
	}

	@Test
	@Order(4)
	@DisplayName("하나의 상품을 추가하면, 그 상품의 pk는 15번이고, 전체 상품의 갯수는 15개이다.")
	void insertOneItem() {
		Item inputItem = Item.createItem(ItemFormDTO.builder()
			.name("newItem")
			.brandName("newBrand")
			.description("newDescription")
			.price(1)
			.build());

		boolean save = itemRepository.save(inputItem);

		List<Item> items = itemRepository.findAll();
		Item newItem = itemRepository.findById((long)items.size());

		assertThat(save).isTrue();
		assertThat(items).isNotNull();
		assertThat(newItem).isNotNull();
		assertThat(items.size()).isSameAs(15);
		assertThat(newItem.getId()).isEqualTo(15);
	}
}