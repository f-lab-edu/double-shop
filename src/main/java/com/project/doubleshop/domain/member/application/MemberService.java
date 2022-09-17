package com.project.doubleshop.domain.member.application;

import static com.project.doubleshop.domain.utils.EmailUtils.*;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.project.doubleshop.domain.exception.DuplicateMemberException;
import com.project.doubleshop.domain.exception.NotFoundException;
import com.project.doubleshop.domain.exception.ServiceException;
import com.project.doubleshop.domain.member.domain.Member;
import com.project.doubleshop.domain.member.domain.MemberRepository;
import com.project.doubleshop.domain.member.infrastructure.jpa.JpaMemberRepository;
import com.project.doubleshop.web.member.dto.JoinRequest;

import lombok.RequiredArgsConstructor;

//TODO Member 로그인, 회원가입 리팩토링 후 해당 클래스 삭제
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Validated
public class MemberService {

	private final JpaMemberRepository memberRepository;

	private final PasswordEncoder passwordEncoder;

	@Transactional
	public Member login(String userId, String password) {
		Member member = findByUserId(userId);
		member.login(passwordEncoder, password);
		member.afterSuccessLogin();
		return member;
	}

	public Member findById(Long memberId) {
		return memberRepository.findById(memberId).orElseThrow(
			() -> new NotFoundException(String.format("Id [%d] NotFound", memberId)));
	}

	public Member findByUserId(String userId) {
		return memberRepository.findByUserId(userId).orElseThrow(
			() -> new NotFoundException(String.format("UserId [%s] NotFound", userId)));
	}

	@Transactional
	public Member join(@Valid JoinRequest requestBody) {
		String userId = requestBody.getUserId();
		String email = requestBody.getEmail();
		if (checkDuplicated(userId, email))
			throw new DuplicateMemberException(String.format("UserId [%s], email [%s] already exisits..", userId, email));
		Member member = new Member(requestBody.getUserId(), requestBody.getCredential(),
			requestBody.getName(),
			requestBody.getEmail(), requestBody.getPhone());
		member.encodePassword(passwordEncoder, member.getPassword());
		return memberRepository.save(member);
	}

	public boolean checkDuplicated(String userId, String email) {
		return checkDuplicatedUserId(userId) || checkDuplicatedEmail(email);
	}

	public boolean checkDuplicatedUserId(String userId) {
		return isExists(Map.of("userId", userId));
	}

	public boolean checkDuplicatedEmail(String email) {
		return isExists(Map.of("email", email));
	}

	public Boolean isExists(Map<String, String> requestMap) {
		String requestUserId = "userId";
		String requestEmail = "email";

		if (requestMap.containsKey(requestUserId)) {
			String userId = requestMap.get(requestUserId);
			if (userId == null) {
				throw new ServiceException("Must use 'userId'.");
			}
			return memberRepository.findByUserId(userId).isPresent();
		} else {
			if (requestMap.containsKey(requestEmail)) {
				String email = requestMap.get(requestEmail);
				if (checkEmail(email)) {
					return memberRepository.findByEmail(email).isPresent();
				}
			}
		}
		throw new ServiceException("Must use 'userId' or 'email'. Otherwise, check your parameter");
	}

	@Transactional
	public Member updateProfile(Long memberId, String userId, String name, String email, String phone) {
		Member member = findById(memberId);
		member.updateProfile(userId, name, email, phone);
		return member;
	}

	@Transactional
	public Boolean changePassword(Long memberId, Map<String, String> requestMap) {
		String reqPassword = "password";

		if (!requestMap.containsKey(reqPassword)) {
			throw new ServiceException("Must use parameter 'password'");
		}

		Member member = findById(memberId);
		String previousPassword = member.getPassword();
		member.changePassword(passwordEncoder, requestMap.get(reqPassword));
		String currentPassword = member.getPassword();
		return !previousPassword.equals(currentPassword);
	}
}
