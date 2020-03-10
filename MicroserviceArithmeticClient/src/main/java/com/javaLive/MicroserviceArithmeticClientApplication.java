package com.javaLive;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.client.RestClientException;

import com.javaLive.controller.AdditionServiceClientController;
import com.javaLive.controller.SubstractionServiceClientController;

@SpringBootApplication
public class MicroserviceArithmeticClientApplication {

	public static void main(String[] args) throws RestClientException, IOException {
		
		ApplicationContext ctx = SpringApplication.run(MicroserviceArithmeticClientApplication.class, args);

		AdditionServiceClientController additionServiceClientController = ctx.getBean(AdditionServiceClientController.class);
		additionServiceClientController.numberAddition();
		
		SubstractionServiceClientController substractionServiceClientController = ctx.getBean(SubstractionServiceClientController.class);
		substractionServiceClientController.numberSubstraction();
	}
}
