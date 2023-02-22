package com.example.currencyconvertion.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExcelDto {
    private String baseCurrency;
    private String conversionCurrency;
    private double inputRate;
    private double convertedRate;
    private Date createTs;
}
