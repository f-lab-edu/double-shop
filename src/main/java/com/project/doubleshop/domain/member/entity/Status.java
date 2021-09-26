package com.project.doubleshop.domain.member.entity;

/**
 * NORMAL: 일반 사용자 계정
 * DORMANT: 휴면 계정
 * BANNED: 사용 정지된 계정
 */
public enum Status {
    NORMAL,
    DORMANT,
    BANNED;
}
