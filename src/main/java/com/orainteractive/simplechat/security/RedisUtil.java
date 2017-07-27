package com.orainteractive.simplechat.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public enum RedisUtil {
	INSTANCE;

	private final JedisPool pool;
	private final Logger logger = LoggerFactory.getLogger(RedisUtil.class);
	
	@Value("${spring.redis.host}")
	private String serverName;
	@Value("${spring.redis.port}")
	private int serverPort;

	RedisUtil() {
		logger.debug("Instatiate RedisUtil........");
		pool = new JedisPool(new JedisPoolConfig(), serverName, serverPort);
		logger.debug("Succesfully create pool........");
	}

	/**
	 * Add members to a set stored at the key
	 * @param key
	 * @param value
	 */
	public void sadd(String key, String value) {
		Jedis jedis = null;
		logger.debug("RedisUtil:: Calling sadd........");
		try {
			jedis = pool.getResource();
			jedis.sadd(key, value);
		} 
		catch (Exception e) {
			logger.debug(e.getMessage());
		}
		finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}
	
	/**
	 * Remove the specified member from the set stored at the key
	 * @param key
	 * @param value
	 */	 	
	public void srem(String key, String value) {
		Jedis jedis = null;
		logger.debug("RedisUtil:: Calling srem........");
		
		try {
			jedis = pool.getResource();
			jedis.srem(key, value);
		}
		catch (Exception e) {
			logger.debug(e.getMessage());
		}
		finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	/**
	 * @param key
	 * @param value
	 * @return check if an element that already exists in the set stored at the key or not 
	 */
	public boolean sismember(String key, String value) {
		Jedis jedis = null;
		logger.debug("RedisUtil:: Calling sismember........");
		
		try {
			jedis = pool.getResource();
			return jedis.sismember(key, value);
		}
		catch (Exception e) {			
			logger.debug(e.getMessage());
			return false;
		}
		finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}
}
