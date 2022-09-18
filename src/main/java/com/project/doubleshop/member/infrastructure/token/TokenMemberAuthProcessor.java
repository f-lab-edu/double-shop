package com.project.doubleshop.member.infrastructure.token;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.project.doubleshop.exception.NotFoundException;
import com.project.doubleshop.member.MemberAuthProcessor;
import com.project.doubleshop.member.domain.Member;
import com.project.doubleshop.member.domain.MemberRepository;
import com.project.doubleshop.member.infrastructure.redis.TokenRepository;
import com.project.doubleshop.utils.IPUtils;
import com.project.doubleshop.member.infrastructure.AuthenticationResult;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TokenMemberAuthProcessor implements MemberAuthProcessor<SimpleAuthenticationToken> {

	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	private final SimpleTokenConfigurer simpleTokenConfigurer;
	private final TokenRepository tokenRepository;
	private final HttpServletRequest httpServletRequest;

	@Override
	@Transactional
	public SimpleAuthenticationToken login(String userId, String password) {
		Member member = memberRepository.findByUserId(userId).orElseThrow(
			() -> new NotFoundException(String.format("UserId [%s] NotFound", userId)));
		member.login(passwordEncoder, member.getPassword());

		SimpleAuthenticationToken authenticated =
			new SimpleAuthenticationToken(member.getId(), null, AuthorityUtils.createAuthorityList(Role.USER.value()));

		long now = System.nanoTime();
		int expirySeconds = simpleTokenConfigurer.getExpirySeconds();

		String tokenKey = UUID.randomUUID().toString();
		String authClientAddress = IPUtils.getClientIpAddress(httpServletRequest);
		SimpleToken tokenValue = new SimpleToken(member.getId(), member.getUserId(), member.getName(),
			member.getEmail(), now, now + expirySeconds * 1_000_000_000L, authClientAddress, new String[] {Role.USER.value()});

		tokenRepository.save(tokenKey, tokenValue);
		authenticated.setDetails(new AuthenticationResult(tokenKey, tokenValue));

		member.afterSuccessLogin();
		return authenticated;
	}
}
