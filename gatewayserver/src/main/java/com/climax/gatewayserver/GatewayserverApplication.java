package com.climax.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
//@EnableEurekaClient
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(
						p -> p
						.path("/climax/client/**")
						.filters(f -> f.rewritePath("/climax/client/(?<segment>.*)","/${segment}")
								.addResponseHeader("X-Response-Time",new Date().toString())
						)
						.uri("lb://CLIENT")
				).route(
						p -> p
								.path("/climax/fileprocess/**")
								.filters(f -> f.rewritePath("/climax/fileprocess/(?<segment>.*)","/${segment}")
										.addResponseHeader("X-Response-Time",new Date().toString())
								)
								.uri("lb://FILEPROCESS")
				).build();
	}
}
