package com.project.doubleshop.domain.cart.service;

import static com.project.doubleshop.domain.cart.service.MockCart.Cart1.*;
import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.mockito.BDDMockito.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.project.doubleshop.domain.cart.entity.Cart;
import com.project.doubleshop.domain.cart.repository.legacy.CartRepository;
import com.project.doubleshop.domain.cart.service.legacy.CartService;
import com.project.doubleshop.domain.member.service.MockMember;

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
		given(cartRepository.saveCart(CART)).willReturn(true);

		cartService.saveNewCart(CART);

		then(cartRepository).should(times(1)).saveCart(CART);
	}

	@Test
	@DisplayName("장바구니 삭제 테스트")
	void deleteCarts() {
		List<Long> cardIds = List.of(ID);
		given(cartRepository.deleteCarts(anyLong(), anyList())).willReturn(cardIds.size());
		given(cartRepository.findCartInIds(cardIds, MockMember.Member1.ID)).willReturn(List.of(new Cart(1L, 1L, 1L, 1)));

		Integer resultSize = cartService.deleteCarts(1L, cardIds);

		assertThat(resultSize).isEqualTo(cardIds.size());
	}
}