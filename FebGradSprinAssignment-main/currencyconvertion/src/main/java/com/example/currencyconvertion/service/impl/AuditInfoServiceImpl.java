package com.example.currencyconvertion.service.impl;

import com.example.currencyconvertion.dto.ExcelDto;
import com.example.currencyconvertion.dto.ExchangeRateData;
import com.example.currencyconvertion.dto.RestCallResponseDto;
import com.example.currencyconvertion.model.AuditInfo;
import com.example.currencyconvertion.model.AuditInfoConversion;
import com.example.currencyconvertion.repository.AuditInfoConversionRepository;
import com.example.currencyconvertion.repository.AuditInfoRepository;
import com.example.currencyconvertion.service.AuditInfoService;
import com.example.currencyconvertion.utils.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

@Service
class AuditInfoServiceImpl implements AuditInfoService {
    private static final Logger logger = LoggerFactory.getLogger(AuditInfoServiceImpl.class);
    @Autowired
    UrlCreator urlCreator;

    @Autowired
    CSVWritter csvWritter;

    @Autowired
    RestCallService restCallService;

    @Autowired
    AuditInfoConversionRepository auditInfoConversionRepository;

    @Autowired
    AuditInfoRepository auditInfoRepository;

    public String calculateCurrency(int amount, String inputDate) throws Exception {
        List<CurrencyCodes> list = new ArrayList<>(Arrays.asList(CurrencyCodes.AED, CurrencyCodes.CAD, CurrencyCodes.EUR, CurrencyCodes.INR, CurrencyCodes.JPY));
        String toValue = "USD";
        boolean check = false;
        for (CurrencyCodes fromValue : list) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MINUTE, 1);
            String url = urlCreator.callUrl(fromValue.toString(), toValue, amount, inputDate);
            String response = restCallService.restTemplateCAll(url);
            Calendar calendar1 = Calendar.getInstance();
            if (response != null && calendar1.getTimeInMillis() - calendar.getTimeInMillis() < (1000 * 60)) {
                logger.info("Session happened in time limit for currency code " + fromValue);
                ObjectMapper objectMapper = new ObjectMapper();
                RestCallResponseDto restCallResponseDto = objectMapper.readValue(response, RestCallResponseDto.class);
                ExcelDto excelDto = new ExcelDto();
                excelDto.setCreateTs(restCallResponseDto.getDate());
                excelDto.setInputRate(amount);
                excelDto.setConvertedRate(restCallResponseDto.getResult());
                excelDto.setBaseCurrency(restCallResponseDto.getQuery().getFrom());
                excelDto.setConversionCurrency(restCallResponseDto.getQuery().getTo());
                logger.info("Excel data" + restCallResponseDto.toString());
                boolean b = csvWritter.makeEntry(excelDto);
                if (b) {
                    logger.info("Entry made into the csv file");
                    AuditInfoConversion auditInfo = new AuditInfoConversion();
                    auditInfo.setResponse(response);
                    auditInfo.setRequest(url);
                    auditInfo.setStatus(Status.RECEIVED_RESPONSE.toString());
                    Date date = new Date();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    auditInfo.setCreateTs(simpleDateFormat.format(date));// yyyy-MM-dd HH:mm
                    simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    auditInfo.setUPDATE_TS(simpleDateFormat.format(date));
                    auditInfoConversionRepository.save(auditInfo);

                } else {
                    logger.error("Failed to make the entry in the file");
                    logger.error("Audit info failed to save");
                }
            } else {
                logger.error("Session Timed out or response failed");
            }

        }

        return "Completed";
    }

    @Override
    public String calculateExchangeRate(Optional<String> s) {
        List<CurrencyCodes> list = Arrays.asList(CurrencyCodes.INR, CurrencyCodes.JPY, CurrencyCodes.EUR, CurrencyCodes.CAD, CurrencyCodes.AED);
        FutureTask<ExchangeRateData>[] tasks = new FutureTask[6];
        AuditInfo[] infos = new AuditInfo[5];
        try {

            for (int i = 0; i < list.size(); i++) {
                String url = urlCreator.callUrlForExchangeRate(s, list.get(i).toString());
                Callable<ExchangeRateData> callable = new SchedulerCallForExchangeRate(url);
                tasks[i] = new FutureTask<>(callable);

                AuditInfo info = new AuditInfo();
                info.setRequest(url);
                info.setCreate_ts(new Timestamp(new Date().getTime()));
                info.setStatus("SENT_THE_REQ");
               infos[i]=info;

                Thread th = new Thread(tasks[i]);
                th.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }for (int j = 0; j < list.size(); j++)
        {
            try {
                AuditInfo info=infos[j];
                info.setResponse(tasks[j].get().toString());
                info.setUpdate_ts(tasks[j].get().getTimestamp());
                info.setStatus("RECEIVED_RESPONSE");
                csvWritter.makeEntryExchangeRate(tasks[j].get());
                auditInfoRepository.save(info);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "Exchange Rate Calculated";
    }
}
