package com.vishop.service.shiro;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicInteger;

public class RetryLimitCredentialsMatcher extends HashedCredentialsMatcher {

	private static final Logger logger = Logger
			.getLogger(RetryLimitCredentialsMatcher.class);
	@Resource
	private CacheManager cacheManager;

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token,
			AuthenticationInfo info) {
		String username = (String) token.getPrincipal();
		Cache passwordRetryCache = cacheManager.getCache("passwordRetryCache");
		Element retry = passwordRetryCache.get(username);
		if (null == retry) {
			AtomicInteger retryCount = new AtomicInteger(0);
			passwordRetryCache.put(new Element(username, retryCount));
		}
		AtomicInteger retryCount = (AtomicInteger) retry.getObjectValue();
		if (retryCount.incrementAndGet() > 5) {
			logger.warn("username: " + username
					+ " tried to login more than 5 times in period");
			throw new ExcessiveAttemptsException("username: " + username
					+ " tried to login more than 5 times in period");
		}
		boolean matches = super.doCredentialsMatch(token, info);
		if (matches) {
			// clear retry data
			passwordRetryCache.remove(username);
		}
		return matches;
	}
}
