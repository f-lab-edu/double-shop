package com.project.doubleshop.domain.member.service;

import com.project.doubleshop.domain.exception.MemberNotFoundException;
import com.project.doubleshop.domain.exception.UnauthenticatedMemberException;
import com.project.doubleshop.domain.member.entity.Member;
import com.project.doubleshop.web.member.dto.MemberFindResponseDto;
import com.project.doubleshop.web.member.dto.MemberSaveRequestDto;
import com.project.doubleshop.domain.mapper.MemberInfoMapper;
import com.project.doubleshop.domain.utils.SHA256EncryptionUtil;
import com.project.doubleshop.web.member.dto.PasswordChangeRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private MemberInfoMapper memberInfoMapper;

    private SHA256EncryptionUtil encryptionUtil;

    // 아이디 중복 검사
    @Override
    public boolean isIdDuplicate(String userId) {
        return memberInfoMapper.findSameUserId(userId) != null;
    }

    // 이메일 중복 검사
    @Override
    public boolean isEmailDuplicate(String email) {
        return memberInfoMapper.findSameEmail(email) != null;
    }

    // 회원가입
    @Override
    public void registerMember(MemberSaveRequestDto requestDto) {
        requestDto.encryptPassword(encryptionUtil);

        memberInfoMapper.register(requestDto);
    }

    @Override
    public MemberFindResponseDto getMemberResource(String userId) {
        return memberInfoMapper.findByUserId(userId)
                .orElseThrow(() -> new MemberNotFoundException("존재하지 않는 아이디입니다."))
                .toMemberFindResponseDto();
    }

    @Transactional
    @Override
    public void updatePassword(PasswordChangeRequestDto requestDto) {
        String userId = requestDto.getUserId();

        requestDto.encryptPassword(encryptionUtil);

        Member member = memberInfoMapper.findByUserId(userId)
                .orElseThrow(() -> new UnauthenticatedMemberException("인증되지 않은 사용자"));

        member.updatePassword(requestDto.getPassword());
    }

}
