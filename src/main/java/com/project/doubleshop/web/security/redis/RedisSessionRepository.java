package com.project.doubleshop.web.security.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import com.project.doubleshop.domain.member.entity.v2.Member;
import com.project.doubleshop.web.security.SimpleToken;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RedisSessionRepository implements SessionRepository {

	private final RedisTemplate<String, Object> redisTemplate;

	@Override
	public void save(String sessionId, SimpleToken simpleToken) {
		ValueOperations<String, Object> ops = redisTemplate.opsForValue();
		ops.set(sessionId, simpleToken);
	}

	@Override
	public SimpleToken findBySessionId(String sessionId) {
		ValueOperations<String, Object> ops = redisTemplate.opsForValue();
		return (SimpleToken) ops.get(ops);
	}

	@Override
	public Boolean updateSession(String sessionId, SimpleToken simpleToken) {
		ValueOperations<String, Object> ops = redisTemplate.opsForValue();
		return ops.setIfPresent(sessionId, simpleToken);
	}

	@Override
	public Boolean deleteSession(String sessionId) {
		return redisTemplate.delete(sessionId);
	}
}
