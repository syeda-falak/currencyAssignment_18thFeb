package com.example.currencyconvertion.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class CurrencyProperties {
    @Value("${conversion.api.url}")
    private String apiUrl;

    @Value("${exchange.api.url}")
    private String exchangeApiUrl;
}
