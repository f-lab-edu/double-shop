package com.project.doubleshop.domain.cart.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.project.doubleshop.domain.item.entity.Item;
import com.project.doubleshop.domain.member.domain.Member;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Cart {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	private Member member;

	@ManyToOne(fetch = FetchType.LAZY)
	private Item item;

	private Integer quantity;

	public Cart(Member member, Item item, Integer quantity) {
		this.member = member;
		this.item = item;
		this.quantity = quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity += quantity;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Item getItemWithCategory() {
		this.item.getCategory();
		return item;
	}
}
