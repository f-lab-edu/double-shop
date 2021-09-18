package com.project.doubleshop.service.impl;

import com.project.doubleshop.dto.MemberSaveRequestDto;
import com.project.doubleshop.exception.DuplicateEmailException;
import com.project.doubleshop.exception.DuplicateIdException;
import com.project.doubleshop.mapper.MemberInfoMapper;
import com.project.doubleshop.service.MemberService;
import com.project.doubleshop.utils.SHA256EncryptionUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private MemberInfoMapper memberInfoMapper;
    private SHA256EncryptionUtil encryptionUtil;

    // 아이디 중복 검사
    @Override
    public boolean checkIdDuplicate(String userId) {
        return memberInfoMapper.checkId(userId) == 1;
    }

    // 이메일 중복 검사
    @Override
    public boolean checkEmailDuplicate(String email) {
        return memberInfoMapper.checkEmail(email) == 1;
    }

    // 회원가입
    @Override
    public void signUp(MemberSaveRequestDto requestDto) {
        if (checkIdDuplicate(requestDto.getUserId())) {
            throw new DuplicateIdException();
        }

        if (checkEmailDuplicate(requestDto.getEmail())) {
            throw new DuplicateEmailException();
        }

        requestDto.passwordEncryption(encryptionUtil);

        memberInfoMapper.register(requestDto);
    }

}
