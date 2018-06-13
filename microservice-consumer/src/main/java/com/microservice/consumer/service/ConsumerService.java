package com.microservice.consumer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConsumerService {
	@Autowired
	RestTemplate restTemplate;

	private final Logger logger = Logger.getLogger(getClass());

	@HystrixCommand(fallbackMethod = "consumerFallback")
	public String consumeProvider(){
		long start = System.currentTimeMillis();

		String result = restTemplate.getForObject("http://hello-service/pipipan",String.class);

		long end = System.currentTimeMillis();
		logger.info("Spend time : " + (end - start));
		return result;
	}

	public String consumerFallback(){
		return "error";
	}
}
