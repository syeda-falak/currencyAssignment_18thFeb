package com.example.currencyconvertion.utils;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestResponse {
    String REQUEST_JSON;
    String RESPONSE_JSON;
}
