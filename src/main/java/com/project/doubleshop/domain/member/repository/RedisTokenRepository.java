package com.project.doubleshop.domain.member.repository;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import com.project.doubleshop.web.config.security.SimpleToken;

import lombok.RequiredArgsConstructor;

@Repository
public class RedisTokenRepository implements TokenRepository {

	private final RedisTemplate<String, Object> redisTemplate;

	public RedisTokenRepository(
		@Qualifier("tokenRedisTemplate") RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@Value("${token.expirySeconds}")
	private int expirySeconds;

	@Override
	public void save(String sessionId, SimpleToken simpleToken) {
		ValueOperations<String, Object> ops = redisTemplate.opsForValue();
		ops.set(sessionId, simpleToken, Duration.ofSeconds(expirySeconds));
	}

	@Override
	public SimpleToken findBySessionId(String sessionId) {
		ValueOperations<String, Object> ops = redisTemplate.opsForValue();
		return (SimpleToken) ops.get(sessionId);
	}

	@Override
	public Boolean updateSession(String sessionId, SimpleToken simpleToken) {
		ValueOperations<String, Object> ops = redisTemplate.opsForValue();
		return ops.setIfPresent(sessionId, simpleToken, Duration.ofSeconds(expirySeconds));
	}

	@Override
	public Boolean deleteSession(String sessionId) {
		return redisTemplate.delete(sessionId);
	}

}
