package com.project.doubleshop.item.application;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.project.doubleshop.item.domain.Item;
import com.project.doubleshop.item.infrastructure.ItemFinder;
import com.project.doubleshop.item.infrastructure.ItemCreator;
import com.project.doubleshop.web.item.dto.ItemForm;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemFacade {
	private final ItemCreator itemCreator;
	private final ItemFinder itemFinder;

	@Transactional
	public Item createItem(ItemForm itemForm, MultipartFile imageFile) {
		return itemCreator.createWith(itemForm, imageFile);
	}

	public Item find(Long id) {
		return itemFinder.find(id);
	}

	public List<Item> findPerPage(Pageable pageable) {
		return itemFinder.findPerPage(pageable);
	}
}