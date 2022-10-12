package com.project.doubleshop.item.presentation;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.doubleshop.common.ApiResult;
import com.project.doubleshop.item.application.ItemFacade;
import com.project.doubleshop.item.domain.Item;
import com.project.doubleshop.web.item.dto.ItemApiResult;
import com.project.doubleshop.web.item.dto.ItemInfoResponse;
import com.project.doubleshop.web.item.dto.ItemForm;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class ItemAdminApi {
	private final ItemFacade itemFacade;

	@PostMapping(value = "v2/member/{memberId}/item", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	public ApiResult<ItemApiResult> createNewItem(@RequestBody ItemForm itemForm, @RequestPart(required = false)
	MultipartFile imageFile) {
		Item item = itemFacade.createItem(itemForm, imageFile);
		return ApiResult.OK(new ItemApiResult(item));
	}
}
