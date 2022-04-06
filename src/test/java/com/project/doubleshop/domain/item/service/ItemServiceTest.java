package com.project.doubleshop.domain.item.service;

import static com.project.doubleshop.domain.item.MockItem.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.List;
import java.util.Optional;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.project.doubleshop.domain.category.entity.Category;
import com.project.doubleshop.domain.category.repository.CategoryRepository;
import com.project.doubleshop.domain.category.service.CategoryService;
import com.project.doubleshop.domain.category.service.CategoryVaultManager;
import com.project.doubleshop.domain.item.MockItem;
import com.project.doubleshop.domain.item.entity.Item;
import com.project.doubleshop.domain.item.repository.ItemRepository;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

	@Mock
	ItemRepository itemRepository;

	@Mock
	CategoryService categoryService;

	@InjectMocks
	ItemService itemService;




	@Test
	void findById() {
		given(itemRepository.findById(Item1.ID)).willReturn(Optional.of(Item1.ITEM));
		given(categoryService.findById(Item1.CATEGORY.getId())).willReturn(Item1.CATEGORY);

		Item item = itemService.findById(Item1.ID);

		assertThat(item).isNotNull();
		assertThat(item.getId()).isEqualTo(Item1.ITEM.getId());
	}

	@Test
	void remove() {
		given(itemRepository.findById(Item1.ID)).willReturn(Optional.empty());

		itemService.remove(List.of(Item1.ITEM.getId()));

		assertThat(itemRepository.findById(Item1.ID).isEmpty()).isTrue();
	}
}