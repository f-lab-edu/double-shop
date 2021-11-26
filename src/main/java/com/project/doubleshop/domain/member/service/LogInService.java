package com.project.doubleshop.domain.member.service;

import com.project.doubleshop.domain.exception.MemberNotFoundException;
import com.project.doubleshop.domain.exception.NotAuthorizedException;
import com.project.doubleshop.domain.member.entity.Member;
import com.project.doubleshop.domain.member.repository.MemberInfoRepository;
import com.project.doubleshop.domain.utils.SHA256EncryptionUtil;
import com.project.doubleshop.web.member.dto.LogInRequestDto;
import com.project.doubleshop.web.member.dto.MemberInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

import static com.project.doubleshop.domain.utils.constants.MemberConstants.AUTH;
import static com.project.doubleshop.domain.utils.constants.MemberConstants.USER_ID;

@Deprecated
@Service
@RequiredArgsConstructor
public class LogInService {

    private final HttpSession session;

    private final MemberInfoRepository repository;

    private final SHA256EncryptionUtil encryptionUtil;

    public void logIn(LogInRequestDto requestDto) {
        checkUserIdAndPassword(requestDto);

        String userId = requestDto.getUserId();

        setMemberAuthority(userId);

        session.setAttribute(USER_ID, userId);
    }

    public void logOut() {
        session.removeAttribute(USER_ID);
    }

    public void checkUserIdAndPassword(LogInRequestDto requestDto) {
        requestDto.encryptPassword(encryptionUtil);

        String userId = requestDto.getUserId();
        String password = requestDto.getPassword();

        boolean isFound = repository.existsByUserIdAndPassword(userId, password);

        if (!isFound) {
            throw new MemberNotFoundException("아이디 또는 비밀번호가 일치하지 않습니다.");
        }
    }

    public String getLogInMember() {
        return (String) session.getAttribute(USER_ID);
    }

    public MemberInfoDto getCurrentMember(String userId) {
        // Optional 타입으로 값을 리턴하고, MemberInfoDto 객체로 변환한다.
        // 리턴값이 null이라면 MemberNotFoundException 예외를 발생시킨다.
        return repository.findByUserId(userId)
                .orElseThrow(() -> new MemberNotFoundException("존재하지 않는 사용자입니다."))
                .toMemberInfoDto();
    }

    public void setMemberAuthority(String userId) {
        Member member = repository.findByUserId(userId)
                .orElseThrow(() -> new MemberNotFoundException("존재하지 않는 사용자입니다."));

        checkIfBanned(member);
        session.setAttribute(AUTH, member.getAuthority());
    }

    private void checkIfBanned(Member member) {
        if (member.isBanned()) {
            throw new NotAuthorizedException("관리자에 의해 정지된 사용자입니다.");
        }
    }

}
