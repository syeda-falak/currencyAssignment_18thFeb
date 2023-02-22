package com.example.currencyconvertion.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QueryDto {
    @JsonProperty("from")
    String from;
    @JsonProperty("to")
    String to;
    @JsonProperty("amount")
    int amount;
}
