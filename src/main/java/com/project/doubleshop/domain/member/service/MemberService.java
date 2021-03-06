package com.project.doubleshop.domain.member.service;

import static com.project.doubleshop.domain.utils.EmailUtils.*;

import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.doubleshop.domain.exception.NotFoundException;
import com.project.doubleshop.domain.exception.ServiceException;
import com.project.doubleshop.domain.member.entity.Member;
import com.project.doubleshop.domain.member.repository.MemberRepository;
import com.project.doubleshop.web.member.dto.JoinRequest;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;

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
	public Member join(JoinRequest requestBody) {
		Member member = new Member(requestBody.getUserId(), requestBody.getCredential(),
			requestBody.getName(),
			requestBody.getEmail(), requestBody.getPhone());
		member.encodePassword(passwordEncoder, member.getPassword());
		return memberRepository.save(member);
	}

	public Boolean checkDuplicate(Map<String, String> requestMap) {
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
