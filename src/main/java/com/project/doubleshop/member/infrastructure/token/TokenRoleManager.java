package com.project.doubleshop.member.infrastructure.token;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.project.doubleshop.common.Status;
import com.project.doubleshop.member.domain.Member;
import com.project.doubleshop.member.application.MemberService;
import com.project.doubleshop.web.member.dto.RequestRole;
import com.project.doubleshop.web.member.dto.ResultRole;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TokenRoleManager {

	private final MemberService authMemberService;

	private final SimpleTokenConfigurer simpleTokenConfigurer;

	private final TokenService tokenService;

	@Value("${admin-key}")
	private String adminKey;

	public ResultRole manage(HttpServletRequest request, Long memberId, RequestRole requestRole) {
		Member member = authMemberService.findById(memberId);
		if (member.getStatus().equals(Status.ACTIVATED)) {
			if (requestRole.getAdminKey().equals(adminKey)) {
				String tokenKey = request.getHeader(simpleTokenConfigurer.getHeader());
				SimpleToken simpleToken = tokenService.findBySessionId(tokenKey);
				simpleToken.addRole(requestRole.getRole());
				simpleToken.resetExpiry(simpleTokenConfigurer.getExpirySeconds() * 99);
				tokenService.updateSession(tokenKey, simpleToken);
				return new ResultRole(simpleToken.getUserId(), requestRole.getRole());
			} else {
				throw new IllegalArgumentException("Invalid key provided.");
			}
		} else {
			throw new IllegalArgumentException("This member is not activated.");
		}
	}
}
