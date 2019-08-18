package com.example.demo;

import com.example.demo.utils.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootTourApplicationTests {

	@Autowired
	public RedisTemplate<String, Object> redisTemplate;

	@Autowired
	RedisUtil redisUtil;

	@Test
	public void contextLoads() {

	}

	@Test
	public void test(){
//		redisTemplate.
		redisUtil.set("hello","world");

	}

}