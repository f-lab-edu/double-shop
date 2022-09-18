package com.project.doubleshop.member.infrastructure.token;

import org.springframework.stereotype.Service;

import com.project.doubleshop.member.infrastructure.redis.TokenRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenService {
	private final TokenRepository tokenRepository;

	public void saveSession(String tokenKey, SimpleToken simpleToken) {
		tokenRepository.save(tokenKey, simpleToken);
	}

	public SimpleToken findBySessionId(String tokenKey) {
		return tokenRepository.findBySessionId(tokenKey);
	}

	public void updateSession(String tokenKey, SimpleToken simpleToken) {
		tokenRepository.updateSession(tokenKey, simpleToken);
	}

	public Boolean invalidSession(String tokenKey) {
		return tokenRepository.deleteSession(tokenKey);
	};
}
