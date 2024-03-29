package com.project.doubleshop.cart.service;

import static com.project.doubleshop.cart.service.MockCart.Cart1.*;
import static org.mockito.BDDMockito.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.project.doubleshop.cart.repository.CartRepository;
import com.project.doubleshop.member.service.MockMember;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {

	@Mock
	CartRepository cartRepository;

	@InjectMocks
	CartService cartService;

	@Test
	@DisplayName("장바구니 조회 테스트")
	void findCartsByMemberId() {
		given(cartRepository.findCartsByMemberId(anyLong())).willReturn(anyList());

		cartService.findCartsByMemberId(MockMember.Member1.ID);

		then(cartRepository).should(times(1)).findCartsByMemberId(MockMember.Member1.ID);
	}

	@Test
	@DisplayName("장바구니 추가 테스트")
	void saveNewCart() {
		given(cartRepository.save(NEW_CART)).willReturn(CART);

		cartService.save(NEW_CART);

		then(cartRepository).should(times(1)).save(NEW_CART);
	}

	@Test
	@DisplayName("장바구니 삭제 테스트")
	void deleteCarts() {
		List<Long> cartIds = List.of(CART.getId());
		Long memberId = CART.getMember().getId();
		given(cartRepository.findCartsByIdsAndMemberId(cartIds, memberId)).willReturn(List.of(CART));

		cartService.deleteCarts(cartIds, memberId);

		then(cartRepository).should(times(1)).deleteAllById(cartIds);
	}
}