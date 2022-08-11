package com.littlepay.transport.pricing;

import com.littlepay.transport.pricing.service.TapOnTapOffService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class PricingModuleApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(PricingModuleApplication.class, args);
		TapOnTapOffService tapOnTapOffService = configurableApplicationContext.getBean(TapOnTapOffService.class);
		tapOnTapOffService.start();

	}

}
