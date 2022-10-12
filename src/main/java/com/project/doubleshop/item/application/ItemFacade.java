package com.project.doubleshop.item.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.project.doubleshop.item.domain.Item;
import com.project.doubleshop.item.infrastructure.ItemRegister;
import com.project.doubleshop.web.item.dto.ItemForm;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemFacade {
	private final ItemRegister itemRegister;

	@Transactional
	public Item createItem(ItemForm itemForm, MultipartFile imageFile) {
		return itemRegister.registerWith(itemForm, imageFile);
	}
}