package com.project.doubleshop.web.config.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.domain.member.entity.Member;
import com.project.doubleshop.domain.member.service.MemberService;
import com.project.doubleshop.domain.member.service.TokenService;
import com.project.doubleshop.web.member.dto.RequestRole;
import com.project.doubleshop.web.member.dto.ResultRole;

public class TokenRoleManager {

	private MemberService authMemberService;

	private SimpleTokenConfigurer simpleTokenConfigurer;

	private TokenService tokenService;

	@Value("${admin-key}")
	private String adminKey;

	@Autowired
	public void setAuthMemberService(MemberService authMemberService) {
		this.authMemberService = authMemberService;
	}

	@Autowired
	public void setSimpleTokenConfigurer(SimpleTokenConfigurer simpleTokenConfigurer) {
		this.simpleTokenConfigurer = simpleTokenConfigurer;
	}

	@Autowired
	public void setTokenService(TokenService tokenService) {
		this.tokenService = tokenService;
	}

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
