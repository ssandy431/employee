package com.app.emp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class RedisConfiguration {
	
	@Value("${redis.host}")
	private String redisHost;
	@Value("${redis.port}")
	private int port;
	@Value("${redis.password}")
	private String password;
	
	
	@Bean
	public JedisConnectionFactory jedisConnectionFactory()
	{
		RedisStandaloneConfiguration rsc = new RedisStandaloneConfiguration(redisHost, port);
		rsc.setPassword(RedisPassword.of(password));
		return new JedisConnectionFactory(rsc);
	}

	@Bean
	RedisTemplate<String, Object> redisTemplate()
	{
		final RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory());
		template.setKeySerializer(new StringRedisSerializer());
		template.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
		template.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
		return template;
	}
	
	@Bean
	public ObjectMapper objMapper()
	{
		return new ObjectMapper();
	}
}
