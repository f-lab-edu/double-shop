package com.project.doubleshop.domain.member.service;

import static com.project.doubleshop.domain.utils.EmailUtils.*;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.doubleshop.domain.exception.NotFoundException;
import com.project.doubleshop.domain.exception.ServiceException;
import com.project.doubleshop.domain.member.entity.Member;
import com.project.doubleshop.domain.member.repository.legacy.AuthMemberRepository;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthMemberService {

	private final AuthMemberRepository authMemberRepository;

	private final PasswordEncoder passwordEncoder;

	@Transactional
	public Member login(String userId, String password) {
		Member member = findByUserId(userId).orElseThrow(
			() -> new NotFoundException(String.format("UserId [%s] NotFound", userId)));
		member.login(passwordEncoder, password);
		member.afterSuccessLogin();
		update(member);
		return member;
	}

	public Member findById(Long id) {
		return Optional.of(authMemberRepository.findById(id)).orElseThrow(
			() -> new NotFoundException(String.format("Id [%s] NotFound", id)));
	}

	private void update(Member member) {
		authMemberRepository.save(member);
	}

	public Optional<Member> findByUserId(String userId) {
		return Optional.ofNullable(authMemberRepository.findByUserId(userId));
	}

	public Optional<Member> findByEmail(String email) {
		return Optional.ofNullable(authMemberRepository.findByEmail(email));
	}

	@Transactional
	public Member join(String userId, String credential, String name, String email, String phone) {
		Member member = new Member(userId, passwordEncoder.encode(credential), name, email, phone);
		authMemberRepository.save(member);
		return member;
	}

	public Boolean checkDuplicate(Map<String, String> requestMap) {
		String requestUserId = "userId";
		String requestEmail = "email";

		if (requestMap.containsKey(requestUserId)) {
			String userId = requestMap.get(requestUserId);
			if (userId == null) {
				throw new ServiceException("Must use 'userId'.");
			}
			return findByUserId(userId).isPresent();
		} else {

			if (requestMap.containsKey(requestEmail)) {
				String email = requestMap.get(requestEmail);
				if (checkEmail(email)) {
					return findByEmail(email).isPresent();
				}
			}
		}

		throw new ServiceException("Must use 'userId' or 'email'. Otherwise, check your parameter");
	}

	@Transactional
	public Boolean updateProfile(Long id, String userId, String name, String email, String phone) {
		return authMemberRepository.saveProfile(
			Member.builder()
				.id(id)
				.userId(userId)
				.name(name)
				.email(email)
				.phone(phone)
				.build()
		);
	}

	@Transactional
	public Boolean changePassword(Long id, Map<String, String> requestMap) {

		String reqPassword = "password";

		if (!requestMap.containsKey(reqPassword)) {
			throw new ServiceException("Must use parameter 'password'");
		}

		return authMemberRepository.savePassword(
			Member.builder()
				.id(id)
				.password(passwordEncoder.encode(requestMap.get(reqPassword)))
				.build()
		);
	}
}
