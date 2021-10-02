package com.project.doubleshop.domain.member.service;

import com.project.doubleshop.web.member.dto.MemberFindRequestDto;
import com.project.doubleshop.web.member.dto.MemberFindResponseDto;
import com.project.doubleshop.web.member.dto.MemberSaveRequestDto;
import com.project.doubleshop.web.member.dto.PasswordChangeRequestDto;

public interface MemberService {

    boolean isIdDuplicate(String userId);

    boolean isEmailDuplicate(String email);

    void registerMember(MemberSaveRequestDto requestDto);

    public MemberFindResponseDto getMemberResource(String userId);

    public void updatePassword(PasswordChangeRequestDto requestDto);

}
