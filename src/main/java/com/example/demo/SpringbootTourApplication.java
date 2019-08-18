package com.example.demo;

import com.example.demo.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
//@ComponentScan(basePackages = "com.example.demo.config")
@RestController
public class SpringbootTourApplication {

	@Autowired
	public RedisUtil redisUtil;

	@Autowired
	public RedisTemplate<String, Object> redisTemplate;


	public static void main(String[] args) {
		SpringApplication.run(SpringbootTourApplication.class, args);
	}

	@GetMapping("/index")
	public String hello(){
		redisUtil.set("hello","hello");
		return redisUtil.get("hello").toString();
	}

}
