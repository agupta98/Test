package com.example.test.processor;


import com.example.test.config.RewardValues;
import com.example.test.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PointsCalulation {
    @Autowired
    private RewardValues rewardValues;
    public Mono<List<User>> acceptUser(User[] users) {

        return Mono.just(Arrays.stream(users).map(user -> pointsCalculation(user)).collect(Collectors.toList()));
    }
    public User pointsCalculation(User user) {

        int totalPoints = 0;
        if (user.getAmount() > rewardValues.getHigher()) {
            totalPoints += rewardValues.getLower() + (user.getAmount() - rewardValues.getHigher() )*2;
        }
        else if (user.getAmount() > rewardValues.getLower() && user.getAmount() <= rewardValues.getHigher()) {
            totalPoints += user.getAmount() - rewardValues.getLower();
        }
        user.setAmount(totalPoints);
        return user;

    }
}
