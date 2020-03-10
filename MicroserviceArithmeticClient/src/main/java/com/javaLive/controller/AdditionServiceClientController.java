package com.javaLive.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


@Controller
public class AdditionServiceClientController {
	@Autowired
	private DiscoveryClient discoveryClient;

	public void numberAddition() throws RestClientException, IOException {
		
		System.out.println(discoveryClient.toString());

		List<ServiceInstance> instances = discoveryClient.getInstances("additionservice");
		ServiceInstance serviceInstance = instances.get(0);

		String baseUrl = serviceInstance.getUri().toString();
		
		System.out.println(baseUrl);

		baseUrl = baseUrl + "/add/"+10+"/"+20;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Double> response=null;
		try {
			response = restTemplate.exchange(baseUrl, HttpMethod.GET, getHeaders(), Double.class);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		System.out.println("Answer for addition operation is "+response.getBody());
	}

	private static HttpEntity<?> getHeaders() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(headers);
	}
}
