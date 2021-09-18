package com.project.doubleshop.service;

import com.project.doubleshop.dto.MemberSaveRequestDto;

public interface MemberService {

    boolean checkIdDuplicate(String userId);

    boolean checkEmailDuplicate(String email);

    void signUp(MemberSaveRequestDto requestDto);

}
