package com.example.currencyconvertion;

import com.example.currencyconvertion.utils.RestCallService;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Formatter;

@SpringBootTest
class CurrencyRateTest {

	@Test
	void check1() throws Exception {
		String st="https://api.apilayer.com/exchangerates_data/2020-08-10?symbols=USD&base=AED";
		RestCallService ob= new RestCallService();
		String response=ob.conversionAPIMethod(st);
		JSONObject jsonObject=new JSONObject(response);
		JSONObject jb= (JSONObject) jsonObject.get("rates");
		double f= (double) jb.get("USD");
		float f1=(float)f;
		Formatter fm = new Formatter();
		fm.format("%.6f", f1);
		Assertions.assertEquals("0.272242",fm.toString());
	}
	@Test
	void check2() throws Exception {
		String st="https://api.apilayer.com/exchangerates_data/2022-08-10?symbols=USD&base=CAD";
		RestCallService ob= new RestCallService();
		String response=ob.conversionAPIMethod(st);
		JSONObject jsonObject=new JSONObject(response);
		JSONObject jb= (JSONObject) jsonObject.get("rates");
		double f= (double) jb.get("USD");
		float f1=(float)f;
		Formatter fm = new Formatter();
		fm.format("%.6f", f1);
		Assertions.assertEquals("0.782687",fm.toString());
	}

	@Test
	void check3() throws Exception {
		String st="https://api.apilayer.com/exchangerates_data/2018-01-20?symbols=USD&base=EUR";
		RestCallService ob= new RestCallService();
		String response=ob.conversionAPIMethod(st);
		JSONObject jsonObject=new JSONObject(response);
		JSONObject jb= (JSONObject) jsonObject.get("rates");
		double f= (double) jb.get("USD");
		float f1=(float)f;
		Formatter fm = new Formatter();
		fm.format("%.6f", f1);
		Assertions.assertEquals("1.222637",fm.toString());
	}

	@Test
	void check4() throws Exception {
		String st="https://api.apilayer.com/exchangerates_data/2020-09-26?symbols=USD&base=INR";
		RestCallService ob= new RestCallService();
		String response=ob.conversionAPIMethod(st);
		JSONObject jsonObject=new JSONObject(response);
		JSONObject jb= (JSONObject) jsonObject.get("rates");
		double f= (double) jb.get("USD");
		float f1=(float)f;
		Formatter fm = new Formatter();
		fm.format("%.6f", f1);
		Assertions.assertEquals("0.013569",fm.toString());
	}

	@Test
	void check5() throws Exception {
		String st="https://api.apilayer.com/exchangerates_data/2021-03-11?symbols=USD&base=JPY";
		RestCallService ob= new RestCallService();
		String response=ob.conversionAPIMethod(st);
		JSONObject jsonObject=new JSONObject(response);
		JSONObject jb= (JSONObject) jsonObject.get("rates");
		double f= (double) jb.get("USD");
		float f1=(float)f;
		Formatter fm = new Formatter();
		fm.format("%.6f", f1);
		Assertions.assertEquals("0.009212",fm.toString());
	}
}

//"CAD": 0.74882,
//		"EUR": 0.231944,
//		"INR": 20.45017,
//		"JPY": 28.855656,
//		"USD": 0.272242