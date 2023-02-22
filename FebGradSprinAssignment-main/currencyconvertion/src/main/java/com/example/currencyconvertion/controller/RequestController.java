package com.example.currencyconvertion.controller;

import com.example.currencyconvertion.service.AuditInfoService;
import net.bytebuddy.build.Plugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/currency")
public class RequestController {

    @Autowired
    AuditInfoService auditInfoService;

    @GetMapping("/currencyConversion")
    public ResponseEntity<String> convertingValues(@RequestParam int amount,String date) throws Exception {

        return ResponseEntity.ok(this.auditInfoService.calculateCurrency(amount,date));
    }

    @GetMapping("/exchangeRate")
    public ResponseEntity<String> exchangeValues(@RequestParam Optional<String> date)throws Exception{
         return ResponseEntity.ok(auditInfoService.calculateExchangeRate(date));
    }

}
