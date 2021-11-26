package com.project.doubleshop.web.config.security.redis;

import com.project.doubleshop.web.config.security.SimpleToken;

public interface SessionRepository {

	void save(String sessionId, SimpleToken simpleToken);

	SimpleToken findBySessionId(String sessionId);

	Boolean updateSession(String sessionId, SimpleToken simpleToken);

	Boolean deleteSession(String sessionId);

}
