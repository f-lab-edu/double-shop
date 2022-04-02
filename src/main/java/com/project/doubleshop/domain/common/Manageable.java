package com.project.doubleshop.domain.common;

import com.project.doubleshop.domain.common.Status;

public interface Manageable<T> {
	/**
	 * 데이터의 관리상태를 지정하는 메소드, 크게 '활성화', '중지', '삭제'로 분류
	 * 자세한 내용은 com.project.doubleshop.domain.common.Status 참고
	 * @param requestDTO 지정하고자 하는 상태의 내용을 담은 오브젝트 파라미터
	 * @return 영향 받은 row의 갯수
	 */
	int updateStatus(T requestDTO);

	/**
	 * 데이터의 관리상태 중에서 '삭제'로 분류받은 데이터들을 모아 일괄적으로 삭제하는 메소드
	 * @param status 관리상태가 '삭제'인지 아닌지 확인할 수 있는 오브젝트 파라미터
	 * @return 영향 받은 row의 갯수
	 */
	int deleteData(Status status);
}
