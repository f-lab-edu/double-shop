package com.project.doubleshop.domain.item.service;

import static com.project.doubleshop.domain.item.service.MockItem.Item1.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.project.doubleshop.domain.item.entity.Item;
import com.project.doubleshop.domain.item.repository.ItemRepository;
import com.project.doubleshop.web.config.support.SimplePageRequest;
import com.project.doubleshop.web.item.dto.ItemForm;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

	@Mock
	ItemRepository itemRepository;

	@InjectMocks
	ItemService itemService;

	@Test
	@DisplayName("상품 생성")
	void createItem() {
		given(itemRepository.save(NEW_ITEM)).willReturn(true);

		itemService.saveItem(NEW_ITEM);

		then(itemRepository).should(times(1)).save(NEW_ITEM);
	}

	@Test
	@DisplayName("단건 상품 조회")
	void findItem() {
		given(itemRepository.findById(anyLong())).willReturn(ITEM);

		itemService.findItemById(ID).get();

		then(itemRepository).should(times(1)).findById(ID);
	}

	@Test
	@DisplayName("상품 수정")
	void updateItem() {
		given(itemRepository.save(ITEM)).willReturn(true);

		itemService.saveItem(ITEM);

		then(itemRepository).should(times(1)).save(ITEM);
	}

	@Test
	@DisplayName("상품 목록 조회")
	void findItems() {

		SimplePageRequest simplePageRequest = new SimplePageRequest();
		given(itemRepository.findAll(simplePageRequest)).willReturn(anyList());

		itemService.findItems(simplePageRequest);

		then(itemRepository).should(times(1)).findAll(simplePageRequest);
	}
}