package com.project.doubleshop.domain.item.repository.item;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.domain.item.entity.Item;
import com.project.doubleshop.domain.item.mapper.ItemMapper;
import com.project.doubleshop.domain.item.repository.ItemRepository;
import com.project.doubleshop.domain.item.service.ItemService;
import com.project.doubleshop.web.config.support.Pageable;
import com.project.doubleshop.web.config.support.SimplePageRequest;
import com.project.doubleshop.web.item.dto.ItemStatusRequest;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ItemRepositoryTest {
	@Autowired
	ItemMapper itemMapper;

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	ItemService itemService;

	Pageable pageable = new SimplePageRequest(1, 9);

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
	@DisplayName("전체 상품 조회 테스트")
	void findAll() {
		int length = itemMapper.selectAllItems(pageable).size();
		List<Item> items = itemRepository.findAll(pageable);
		items.forEach(item -> System.out.println(item.getId()));
		assertThat(items).isNotNull();
		assertThat(items.size()).isEqualTo(length);
	}

	@Test
	@Order(3)
	@DisplayName("1번 상품의 이름을 바꿀 경우, 해당 릴레이션의 1번 상품의 이름은 바꾼 이름과 동일하다.")
	void updateOne() {
		Item beforeUpdateItem = itemRepository.findById(1L);
		Long id = beforeUpdateItem.getId();
		String changedName = "changed item name";

		Item updatedItem = Item.builder()
			.id(id)
			.name(changedName)
			.description("")
			.brandName("")
			.price(1)
			.build();

		boolean result = itemRepository.save(updatedItem);
		Item afterUpdateItem = itemRepository.findById(1L);

		assertThat(result).isTrue();
		assertThat(afterUpdateItem).isNotNull();
		assertThat(afterUpdateItem.getName()).isEqualTo(changedName);
	}

	@Test
	@Order(4)
	@DisplayName("상품 등록이 이상없이 완료되면, 결과는 true 여야 한다.")
	void insertOneItem() {
		int length = itemMapper.selectAllItems(pageable).size();
		Item inputItem = Item.builder()
			.name("itemName")
			.brandName("newBrand")
			.description("newDescription")
			.price(1)
			.build();

		boolean save = itemRepository.save(inputItem);

		assertThat(save).isTrue();
	}

	@Test
	@Order(5)
	@DisplayName("13번 아이템에 삭제 요청을 할 경우, 13번 아이템의 status는 `DELETE`가 된다.")
	void assignDeleteOneItem() {
		ItemStatusRequest itemStatusRequest = new ItemStatusRequest(13L, Status.TO_BE_DELETED);
		itemRepository.updateStatus(itemStatusRequest);

		Item item = itemRepository.findById(13L);

		assertThat(item.getStatus()).isEqualTo(Status.TO_BE_DELETED);
	}
}