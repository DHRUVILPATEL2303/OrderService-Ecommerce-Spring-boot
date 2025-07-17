package com.example.OrderService_Ecommerce_Spring_boot;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class OrderServiceEcommerceSpringBootApplication {

	public static void main(String[] args) {
		Dotenv dotenv=Dotenv.configure().load();
		dotenv.entries().forEach(dotenvEntry -> System.setProperty(
				dotenvEntry.getKey(),dotenvEntry.getValue()
		));
		SpringApplication.run(OrderServiceEcommerceSpringBootApplication.class, args);
	}

}
