package com.example.currencyconvertion.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InfoDto {
    @JsonProperty("timestamp")
    long timeStamp;
    @JsonProperty("rate")
    float rate;
}
