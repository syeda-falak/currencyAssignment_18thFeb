package com.example.currencyconvertion.service;

import java.util.Optional;

public interface AuditInfoService {
    String calculateCurrency(int amount,String inputDate) throws Exception;

    String calculateExchangeRate(Optional<String> s);
}
