package com.project.doubleshop.common.config;

import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.expiry.ExpiryPolicy;
import org.ehcache.jsr107.Eh107Configuration;
import org.hibernate.cache.ehcache.ConfigSettings;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {
	@Bean
	javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration() {
		return Eh107Configuration
			.fromEhcacheCacheConfiguration(
				CacheConfigurationBuilder
					.newCacheConfigurationBuilder(Object.class, Object.class,
						ResourcePoolsBuilder.newResourcePoolsBuilder()
							.heap(100, EntryUnit.ENTRIES))
				.withExpiry(ExpiryPolicy.NO_EXPIRY)
			);
	}


	@Bean
	public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(CacheManager cacheManager) {
		return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
	}

	@Bean
	public JCacheManagerCustomizer cacheManagerCustomizer() {
		return cacheManager -> cacheManager.createCache("l2Cache", jcacheConfiguration());
	}
}