package com.project.doubleshop.member.infrastructure.redis;

import com.project.doubleshop.member.infrastructure.token.SimpleToken;

public interface TokenRepository {

	void save(String sessionId, SimpleToken simpleToken);

	SimpleToken findBySessionId(String sessionId);

	Boolean updateSession(String sessionId, SimpleToken simpleToken);

	Boolean deleteSession(String sessionId);

}
