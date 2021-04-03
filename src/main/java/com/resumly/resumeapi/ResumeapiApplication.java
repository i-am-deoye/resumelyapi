package com.resumly.resumeapi;

import org.dozer.DozerBeanMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ResumeapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResumeapiApplication.class, args);
	}


	@Bean
	BCryptPasswordEncoder getBCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CacheManager getCacheManager() {
		return new ConcurrentMapCacheManager();
	}

	@Bean
	DozerBeanMapper getDozerBeanMapper() {
		return new DozerBeanMapper();
	}

}
