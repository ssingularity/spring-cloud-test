package com.microservice.consumer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConsumerService {
	@Autowired
	RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "consumerFallback")
	public String consumeProvider(){
		return restTemplate.getForObject("http://hello-service/pipipan",String.class);
	}

	public String consumerFallback(){
		return "error";
	}
}
