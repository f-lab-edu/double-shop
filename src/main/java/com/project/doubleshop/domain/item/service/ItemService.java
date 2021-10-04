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
import com.project.doubleshop.web.item.dto.ItemStatusRequest;
import com.project.doubleshop.web.item.exception.InvalidItemArgumentException;

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
		if(!violations.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			for (ConstraintViolation<Item> violation : violations) {
				sb.append(violation.getMessage()).append(" ");
			}
			throw new InvalidItemArgumentException(sb.toString());
		}
		return itemRepository.save(item);
	}

	public Optional<Item> findItem(Long itemId) {
		return Optional.ofNullable(itemRepository.findById(itemId));
	}

	public List<Item> findItems(Pageable pageable) {
		return itemRepository.findAll(pageable);
	}

	@Transactional
	public void AssignItemStatus(ItemStatusRequest requestDTO) {
		itemRepository.assignStatus(requestDTO);
	}

	@Transactional
	public void DeleteAssignedItems(Status status) {
		itemRepository.deleteAssignedData(status);
	}
}