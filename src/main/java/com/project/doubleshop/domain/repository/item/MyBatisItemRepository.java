package com.project.doubleshop.domain.repository.item;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.project.doubleshop.domain.entity.item.Item;
import com.project.doubleshop.domain.mapper.ItemMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MyBatisItemRepository implements ItemRepository {
	private final ItemMapper mapper;

	/**
	 *
	 * @param item 클라이언트 로부터, 받은 요청 파라미터 값.
	 * @return 만약 0이면 쿼리로 영향을 받은 로우가 없다는 뜻으로 실패(false)를 리턴.
	 */
	@Override
	public boolean save(Item item) {
		int affectedRowNum;
		if(item.getId() != null) {
			affectedRowNum = mapper.updateItem(item);
		} else {
			affectedRowNum = mapper.insertItem(item);
		}
		return affectedRowNum != 0;
	}

	/**
	 *
	 * @param id 조회하고자 하는 상품의 pk 번호
	 * @return pk 번호로 조회한 상품 오브젝트
	 */
	@Override
	public Item findById(Long id) {
		return mapper.selectById(id);
	}

	@Override
	public List<Item> findAll() {
		return mapper.selectAllItems();
	}
}
