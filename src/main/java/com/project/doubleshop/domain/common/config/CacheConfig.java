package com.project.doubleshop.domain.common.config;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.sf.ehcache.Cache;
import net.sf.ehcache.config.CacheConfiguration;

@Configuration
@EnableCaching
public class CacheConfig {
	@Bean
	public EhCacheManagerFactoryBean cacheManagerFactoryBean() {
		return new EhCacheManagerFactoryBean();
	}

	@Bean
	public CacheManager ehCacheCacheCategoryManager() {
		CacheConfiguration oEmbedCacheConfig = new CacheConfiguration()
			.eternal(true)
			.maxEntriesLocalHeap(100)
			.memoryStoreEvictionPolicy("LRU")
			.name("category");

		Cache categoryCache = new Cache(oEmbedCacheConfig);
		net.sf.ehcache.CacheManager cacheManager
			= Objects.requireNonNull(cacheManagerFactoryBean().getObject());

		cacheManager.addCache(categoryCache);
		return new EhCacheCacheManager(cacheManager);
	}

	@Bean
	@Qualifier("categoryCache")
	public org.springframework.cache.Cache categoryCache() {
		return ehCacheCacheCategoryManager().getCache("category");
	}
}