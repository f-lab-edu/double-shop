package com.project.doubleshop.member.infrastructure;

import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.project.doubleshop.exception.ServiceException;
import com.project.doubleshop.member.domain.Member;
import com.project.doubleshop.member.infrastructure.token.SimpleAuthentication;
import com.project.doubleshop.web.member.dto.MemberInfoRequest;
import com.project.doubleshop.web.member.dto.MemberResult;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberInfoManager {
	MemberCrudService memberCrudService;

	private final PasswordEncoder passwordEncoder;


	public MemberResult find(SimpleAuthentication authentication) {
		Long memberId = authentication.getId();
		Member member = memberCrudService.findById(memberId);
		return new MemberResult(member);
	}

	public MemberResult update(Long id, MemberInfoRequest requestBody) {
		Member member = memberCrudService.findById(id);
		member.updateProfile(requestBody.getUserId(), requestBody.getName(), requestBody.getEmail(),
			requestBody.getPhone());
		return new MemberResult(member);
	}

	public Boolean changePasswd(Long id, Map<String, String> requestMap) {
		String reqPasswd = "password";

		if (!requestMap.containsKey(reqPasswd))
			throw new ServiceException("Must use parameter 'password'.");

		Member member = memberCrudService.findById(id);
		String prevPasswd = member.getPassword();
		member.changePassword(passwordEncoder, requestMap.get(reqPasswd));
		String currentPasswd = member.getPassword();
		return !prevPasswd.equals(currentPasswd);
	}

	public Boolean isDuplicated(Map<String, String> requestMap) {
		return null;
	}
}
