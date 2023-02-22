package com.example.currencyconvertion.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RestCallResponseDto {
    @JsonProperty("success")
    String success;
    @JsonProperty("result")
    float result;
    @JsonProperty("date")
    Date date;
    @JsonProperty("info")
    InfoDto info;
    @JsonProperty("query")
    QueryDto query;
    @JsonProperty("historical")
    @JsonIgnore
    boolean historical;
}
