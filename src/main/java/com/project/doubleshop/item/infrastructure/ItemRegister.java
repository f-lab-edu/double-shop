package com.project.doubleshop.item.infrastructure;

import static com.project.doubleshop.web.common.file.ImageFile.*;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.project.doubleshop.item.domain.Item;
import com.project.doubleshop.item.domain.ItemRepository;
import com.project.doubleshop.web.common.file.client.FileClient;
import com.project.doubleshop.web.item.dto.ItemForm;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ItemRegister {
	private final FileClient fileClient;
	private final ItemRepository itemRepository;

	public Item registerWith(ItemForm itemForm, MultipartFile imageFile) {
		String imageUrl = uploadAsyncImageFile(fileClient, of(imageFile), null);
		Item item = itemRepository.save(Item.convertToItem(itemForm));
		item.setImageUri(imageUrl);
		return item;
	}
}
