package com.example.test.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "rewards.points")
@Configuration
@Data
public class RewardValues {

    Integer higher;
    Integer lower;
}
