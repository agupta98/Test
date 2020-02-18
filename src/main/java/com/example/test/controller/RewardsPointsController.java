package com.example.test.controller;

import com.example.test.dto.User;
import com.example.test.processor.PointsCalulation;
import com.example.test.response.RewardCalcResponse;

import com.sun.tools.javac.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class RewardsPointsController {

    @Autowired
    PointsCalulation pointsCalulation;


    public Mono<ServerResponse> rewardCalc(ServerRequest request) {

        return request.bodyToMono(User[].class)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Request Body Cannot Be Null")))
                .flatMap(user -> {
                    return pointsCalulation.acceptUser(user);
                })
                .flatMap(this::mapToResponse);

    }

    public Mono<ServerResponse> mapToResponse(List<User> users) {
        if (users == null) return ServerResponse.noContent().build();

        Map<String, Integer> mapTotal = users.stream().collect(Collectors.groupingBy(User::getId))
                .entrySet().stream().collect(Collectors.toMap(entry -> "UserId "+ entry.getKey(),
                entry -> entry.getValue().stream().collect(Collectors.summingInt(user -> user.getAmount()))));

        Map<Pair<String, Integer>, List<User>> pairListMap =
                users.stream().collect(Collectors.groupingBy(p -> Pair.of(p.getId(), p.getDate().getMonthValue())));
        Map<String, Integer> monthWise = new HashMap<>();
        for (Map.Entry<Pair<String, Integer>, List<User>> user: pairListMap.entrySet()) {
            Integer amount = user.getValue().stream().collect(Collectors.summingInt( amt -> amt.getAmount()));
            Pair<String, Integer> p = user.getKey();
            monthWise.put("User Id "+ p.fst+ " -> Month Value Is "+ p.snd,  amount);
        }

        RewardCalcResponse response = RewardCalcResponse.builder()
                    .mapTotal(mapTotal)
                    .monthWise(monthWise)
                    .message("Success")
                    .build();


        return ServerResponse.ok().body(Mono.just(response), RewardCalcResponse.class);
    }
}
