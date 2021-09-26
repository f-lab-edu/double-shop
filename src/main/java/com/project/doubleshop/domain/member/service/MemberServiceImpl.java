package com.project.doubleshop.domain.member.service;

import com.project.doubleshop.web.member.dto.MemberSaveRequestDto;
import com.project.doubleshop.domain.mapper.MemberInfoMapper;
import com.project.doubleshop.domain.utils.SHA256EncryptionUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private MemberInfoMapper memberInfoMapper;

    private SHA256EncryptionUtil encryptionUtil;

    // 아이디 중복 검사
    @Override
    public boolean isIdDuplicate(String userId) {
        return memberInfoMapper.getIdCount(userId) >= 1;
    }

    // 이메일 중복 검사
    @Override
    public boolean isEmailDuplicate(String email) {
        return memberInfoMapper.getEmailCount(email) >= 1;
    }

    // 회원가입
    @Override
    public void registerMember(MemberSaveRequestDto requestDto) {
        requestDto.encryptPassword(encryptionUtil);

        memberInfoMapper.register(requestDto);
    }

}
