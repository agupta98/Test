package com.example.test;

import com.example.test.controller.RewardsPointsController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@SpringBootApplication
@EnableWebFlux
public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}

		@Bean
		RouterFunction<ServerResponse> routerFunctionRewards (RewardsPointsController rewardsPointsController) {
			return RouterFunctions.route(RequestPredicates.POST("/rewards/calculate"),
					rewardsPointsController::rewardCalc);
		}

	}


