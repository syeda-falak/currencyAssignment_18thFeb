package com.example.currencyconvertion.utils;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class RestCallService {

    public String restTemplateCAll(String url) throws  Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.add("apiKey","0EO3UheNFmCdv5faA0bmBoFM35o7JKkP");
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        RestTemplate restTemplate=new RestTemplate();
        String response=restTemplate.exchange(url, HttpMethod.GET,entity, String.class).getBody();
        return response;
    }
}
