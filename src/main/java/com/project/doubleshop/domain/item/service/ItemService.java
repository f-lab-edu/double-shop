package com.project.doubleshop.domain.item.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.domain.item.entity.Item;
import com.project.doubleshop.domain.item.repository.ItemRepository;
import com.project.doubleshop.web.config.support.Pageable;
import com.project.doubleshop.web.common.StatusRequest;
import com.project.doubleshop.web.item.exception.InvalidItemArgumentException;
import com.project.doubleshop.web.item.exception.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
	private final ItemRepository itemRepository;

	private final Validator validator;

	@Transactional
	public boolean saveItem(Item item) {
		Set<ConstraintViolation<Item>> violations = validator.validate(item);
		// 테스트용 service layered validation - 실질적으로 사용하지는 않는 validation 추후 개선할 예정.
		if (!violations.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			for (ConstraintViolation<Item> violation : violations) {
				sb.append(violation.getMessage()).append(" ");
			}
			throw new InvalidItemArgumentException(sb.toString());
		}
		return itemRepository.save(item);
	}

	public Optional<Item> findItemById(Long itemId) {
		return Optional.ofNullable(itemRepository.findById(itemId));
	}

	public List<Item> findItems(Pageable pageable) {
		return itemRepository.findAll(pageable);
	}

	@Transactional
	public void updateItemStatus(StatusRequest requestDTO) {
		Item item = itemRepository.findById(requestDTO.getId());
		if (item == null) {
			throw new DataNotFoundException(String.format("item ID '%s' not found", requestDTO.getId()));
		}
		if (Status.of(requestDTO.getStatus().name()) == null) {
			throw new IllegalArgumentException(String.format("request status value '%s' not found", requestDTO.getStatus().name()));
		}
	}

	@Transactional
	public void DeleteItems(Status status) {
		itemRepository.deleteData(status);
	}
}