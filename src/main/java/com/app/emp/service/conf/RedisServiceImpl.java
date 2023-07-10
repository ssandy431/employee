package com.app.emp.service.conf;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Service
public class RedisServiceImpl {

	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private RedisTemplate<String, Object> template;
	
	public synchronized List<String> getKeys(final String pattern){
		template.setHashValueSerializer(new StringRedisSerializer());
		template.setValueSerializer(new StringRedisSerializer());
		Set<String> redisKeys = template.keys(pattern);
		List<String> keys = new ArrayList<>();
		Iterator<String> itr = redisKeys.iterator();
		while(itr.hasNext())
		{
			String data = itr.next();
			keys.add(data);
		}
		return keys;
	}
	
	public synchronized boolean isKeyExists(String key)
	{
		System.out.println(getKeys(key)+" key name");
		return template.hasKey(key);
	}
	
	
	public synchronized Object getValue(String key)
	{
		template.setHashValueSerializer(new StringRedisSerializer());
		template.setValueSerializer(new StringRedisSerializer());
		
		return template.opsForValue().get(key);
	}
	
	
	public synchronized Object getValue(String key,Class<?> clazz)
	{
		template.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
		template.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
		
		Object obj = template.opsForValue().get(key);
		
		return objectMapper.convertValue(obj, clazz);
	}
	
	public void setValue(String key,Object value)
	{
		setValue(key, value,TimeUnit.MINUTES,5,false);
	}
	
	
	public void setValue(String key,Object value,long timeout)
	{
		setValue(key, value,TimeUnit.SECONDS,timeout,false);
	}
	
	public void setValue(String key,Object value,TimeUnit timeUnit,long timeout)
	{
		setValue(key, value,timeUnit,timeout,false);
	}
	
	
	
	public void setValue(String key,Object value,TimeUnit unit,long timeout,boolean marshal)
	{
		
		if(marshal)
		{
			template.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
			template.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
		}
		else
		{
			template.setHashValueSerializer(new StringRedisSerializer());
			template.setValueSerializer(new StringRedisSerializer());
		}
		
		template.opsForValue().set(key, value);
		template.expire(key, timeout, unit);
	}
	
	public void setValue(String key,Object value,boolean marshal)
	{
		
		if(marshal)
		{
			template.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
			template.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
		}
		else
		{
			template.setHashValueSerializer(new StringRedisSerializer());
			template.setValueSerializer(new StringRedisSerializer());
		}
		
		template.opsForValue().set(key, value);
		template.expire(key, 5, TimeUnit.MINUTES);
	}
}
