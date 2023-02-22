package com.example.currencyconvertion.utils;

import com.example.currencyconvertion.config.CurrencyProperties;
import com.example.currencyconvertion.model.AuditInfo;
import com.example.currencyconvertion.repository.AuditInfoRepository;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class UrlCreator {
    private static final Logger logger = LoggerFactory.getLogger(UrlCreator.class);

    @Autowired
    CurrencyProperties currencyProperties;

    public String callUrl(String fromValue, String toValue, int amount, String date) throws Exception {
        String url = currencyProperties.getApiUrl();
        logger.info(fromValue);
        if (date == null)
            url = url + "?to=" + toValue + "&from=" + fromValue +
                    "&amount=" + amount;
        else
            url = url + "?to=" + toValue + "&from=" + fromValue +
                    "&amount=" + amount + "&date=" + date;
        logger.info(url);
        return url;
    }

    public String callUrlForExchangeRate(Optional<String> s, String convert) {
        String url = currencyProperties.getExchangeApiUrl();
        if (s.isPresent()) {
            url = url + s.get() + "?symbols=USD&base=" + convert;
        } else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            url = url + simpleDateFormat.format(new Date()) + "?symbols=USD&base=" + convert;
        }
        return url;
    }
}

