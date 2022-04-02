package com.project.doubleshop.domain.item.repository.legacy;

import java.util.List;

import com.project.doubleshop.domain.common.Manageable;
import com.project.doubleshop.domain.common.mapper.param.RequestItemsWithCategory;
import com.project.doubleshop.domain.item.entity.Item;
import com.project.doubleshop.web.config.support.Pageable;
import com.project.doubleshop.web.common.StatusRequest;
import com.project.doubleshop.web.item.dto.ItemStockQuery;

public interface ItemRepository extends Manageable<StatusRequest> {
	/**
	 * 데이터를 '저장' 하는 메소드. 새로운 데이터를 저장하는 것 뿐만 아니라, 기존 데이터를 저장하는 것도 포함한다.
	 * 따라서, 'insert' 뿐만 아니라, 'update' 역시 이 메소드를 통해 구현해야한다.
	 * @param entity 저장하고자 하는 새로운 데이터를 담은 오브젝트, 혹은 수정하고자 하는 데이터를 담은 오브젝트
	 * @return 작업의 성공여부
	 */
	boolean save(Item entity);

	/**
	 * 데이터 단건조회
	 * @param id 조회하고자 하는 스키마의 pk 값
	 * @return 조회가 완료된 스키마 데이터와 매핑된 오브젝트
	 */
	Item findById(Long id);

	/**
	 * 데이터 리스트 조회
	 * @return 조회가 완료된 스키마 데이터들과 매핑된 Collection 오브젝트
	 */
	List<Item> findAll(Pageable pageable);

	/**
	 * 카테고리별 페이징이 적용된 상품 리스트 조회
	 * @param request 카테고리 fk 번호와 페이징 관련 파라미터
	 * @return 특정 카테고리로 정리된 상품 Collection
	 */
	List<Item> findAllWithCategory(RequestItemsWithCategory request);

	List<Item> findItemsInIds(List<Long> itemIds);

	int updateItems(List<ItemStockQuery> queryList);
}
