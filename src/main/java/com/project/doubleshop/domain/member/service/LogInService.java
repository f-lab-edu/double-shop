package com.project.doubleshop.domain.member.service;

import com.project.doubleshop.domain.exception.MemberNotFoundException;
import com.project.doubleshop.domain.member.repository.MemberInfoRepository;
import com.project.doubleshop.domain.utils.SHA256EncryptionUtil;
import com.project.doubleshop.web.member.dto.LogInRequestDto;
import com.project.doubleshop.web.member.dto.MemberInfoDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@AllArgsConstructor
public class LogInService {

    private final HttpSession session;

    private final MemberInfoRepository repository;

    private final SHA256EncryptionUtil encryptionUtil;

    public void logIn(LogInRequestDto requestDto) {
        existsByUserIdAndPassword(requestDto);

        String userId = requestDto.getUserId();

        session.setAttribute("userId", userId);
    }

    public void logOut() {
        session.removeAttribute("userId");
    }

    public void existsByUserIdAndPassword(LogInRequestDto requestDto) {
        requestDto.encryptPassword(encryptionUtil);

        String userId = requestDto.getUserId();
        String password = requestDto.getPassword();

        if (repository.findByUserIdAndPassword(userId, password) == null) {
            throw new MemberNotFoundException("아이디 또는 비밀번호가 일치하지 않습니다.");
        }
    }

    public String getLogInMember() {
        return (String) session.getAttribute("userId");
    }

    public MemberInfoDto getCurrentMember(String userId) {
        // Optional 타입으로 값을 리턴하고, MemberInfoDto 객체로 변환한다.
        // 리턴값이 null이라면 MemberNotFoundException 예외를 발생시킨다.
        return repository.findByUserId(userId)
                .orElseThrow(() -> new MemberNotFoundException("없는 사용자입니다."))
                .toMemberInfoDto();
    }

}
