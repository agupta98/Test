package com.example.test.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.Map;

@Builder
@Data
@JsonSerialize
@ToString
public class RewardCalcResponse {


    private Map<String, Integer>  mapTotal;
    private Map<String, Integer> monthWise;
    private String message;

}
