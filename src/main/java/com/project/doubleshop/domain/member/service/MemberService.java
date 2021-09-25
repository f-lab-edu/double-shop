package com.project.doubleshop.domain.member.service;

import com.project.doubleshop.web.member.dto.MemberSaveRequestDto;

public interface MemberService {

    boolean isIdDuplicate(String userId);

    boolean isEmailDuplicate(String email);

    void registerMember(MemberSaveRequestDto requestDto);

}
