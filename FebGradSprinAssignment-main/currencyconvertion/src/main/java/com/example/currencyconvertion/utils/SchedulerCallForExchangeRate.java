package com.example.currencyconvertion.utils;

import com.example.currencyconvertion.dto.ExchangeRateData;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import okhttp3.OkHttpClient;

import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;


public class SchedulerCallForExchangeRate implements Callable<ExchangeRateData>{
	String st;


	public SchedulerCallForExchangeRate(String st) {
		this.st=st;
	}

	 public ExchangeRateData FetchApi() throws Exception {


		OkHttpClient client = new OkHttpClient().newBuilder().readTimeout(50000, TimeUnit.MILLISECONDS).build();
		if(st.contains("AED")){
			try {
				System.out.println("AED string detected going to sleep");
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if(st.contains("AED"))
			System.out.println("AED sleep done");
		RestCallService restCallService=new RestCallService();
		String response=restCallService.restTemplateCAll(st);
	    ObjectMapper objectMapper = new ObjectMapper();
	    objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	    objectMapper.setVisibility(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
		ExchangeRateData ratesObj=objectMapper.readValue(response, ExchangeRateData.class);
		ratesObj.setTimestamp(new Timestamp(new Date().getTime()));
		return ratesObj;
	}

	@Override
	public ExchangeRateData call() {
		try {
			return FetchApi();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	

}
