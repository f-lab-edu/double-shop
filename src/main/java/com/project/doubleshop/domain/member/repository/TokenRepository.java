package com.project.doubleshop.domain.member.repository;

import com.project.doubleshop.web.config.security.SimpleToken;

public interface TokenRepository {

	void save(String sessionId, SimpleToken simpleToken);

	SimpleToken findBySessionId(String sessionId);

	Boolean updateSession(String sessionId, SimpleToken simpleToken);

	Boolean deleteSession(String sessionId);

}
