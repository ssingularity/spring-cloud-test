package com.microservice.provider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.apache.log4j.Logger;


@RestController
public class HelloController {
	private final Logger logger = Logger.getLogger(getClass());
	@Autowired
	DiscoveryClient discoveryClient;
	@RequestMapping(value="/hello", method = RequestMethod.GET)
	public String hello(){
		ServiceInstance serviceInstance = discoveryClient.getLocalServiceInstance();
		logger.info("/hello, host:" + serviceInstance.getHost() + ", service_id:" + serviceInstance.getServiceId());
		return "hello";
	}
}
