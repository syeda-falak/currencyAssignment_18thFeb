package com.example.currencyconvertion.dto;

import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ExchangeRateData {
	String success;
	Timestamp timestamp;
	Boolean historical;
	String base;
	Date date;
	Rate rates;
}