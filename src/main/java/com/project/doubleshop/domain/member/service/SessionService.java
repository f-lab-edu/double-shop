package com.project.doubleshop.domain.member.service;

import org.springframework.stereotype.Service;

import com.project.doubleshop.web.config.security.SimpleToken;
import com.project.doubleshop.domain.member.repository.SessionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SessionService {
	private final SessionRepository sessionRepository;

	public void saveSession(String tokenKey, SimpleToken simpleToken) {
		sessionRepository.save(tokenKey, simpleToken);
	}

	public SimpleToken findBySessionId(String tokenKey) {
		return sessionRepository.findBySessionId(tokenKey);
	}

	public void resetExpiry(String tokenKey, SimpleToken simpleToken) {
		sessionRepository.updateSession(tokenKey, simpleToken);
	}

	public void invalidSession(String tokenKey) {
		sessionRepository.deleteSession(tokenKey);
	};
}
