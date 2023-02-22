package com.example.currencyconvertion.utils;

import com.example.currencyconvertion.dto.ExcelDto;
import com.example.currencyconvertion.dto.ExchangeRateData;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

@Component
public class CSVWritter {
    private static final Logger logger= LoggerFactory.getLogger(CSVWritter.class);

    public boolean makeEntry(ExcelDto excelDto) throws Exception {
        File file=new File("currencyOutputFile.xlsx");
        FileInputStream fip ;
        Workbook workbook;
        Sheet spreadsheet;
        if(!file.exists()){
            workbook=new XSSFWorkbook();
            spreadsheet=workbook.createSheet("currencyOutputFile.xlsx");
            int rowCount=0;
            //Creating new row from the next row count
            Row row1 = spreadsheet.createRow(rowCount++);
            CellStyle dateCellStyle = workbook.createCellStyle();
            CreationHelper createHelper = workbook.getCreationHelper();
            dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
            CellStyle style = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBoldweight((short) 2);
            style.setFont(font);
            Cell cell1=row1.createCell(0);
            cell1.setCellStyle(style);
            cell1.setCellValue("BASE CURRENCY");
            Cell cell2=row1.createCell(1);
            cell2.setCellStyle(style);
            cell2.setCellValue("CONVERSION_CURRENCY");
            Cell cell3=row1.createCell(2);
            cell3.setCellStyle(style);
            cell3.setCellValue("INPUT RATE");
            Cell cell4=row1.createCell(3);
            cell4.setCellStyle(style);
            cell4.setCellValue("CONVERTED RATE");
            Cell cell5=row1.createCell(4);
            cell5.setCellStyle(style);
            cell5.setCellValue("CREATE_TS");
            FileOutputStream os = new FileOutputStream(file);
            workbook.write(os);
            os.close();
        }
        fip= new FileInputStream(file);
        workbook= WorkbookFactory.create(fip);
        spreadsheet=workbook.getSheetAt(0);
        int rowCount=spreadsheet.getLastRowNum();
        //Creating new row from the next row count
        Row row = spreadsheet.createRow(++rowCount);
        CellStyle dateCellStyle = workbook.createCellStyle();
        CreationHelper createHelper = workbook.getCreationHelper();
        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
        row.createCell(0).setCellValue(excelDto.getBaseCurrency());
        row.createCell(1).setCellValue(excelDto.getConversionCurrency());
        row.createCell(2).setCellValue(excelDto.getInputRate());
        row.createCell(3).setCellValue(excelDto.getConvertedRate());
        Cell date=row.createCell(4);
        date.setCellStyle(dateCellStyle);
        date.setCellValue(excelDto.getCreateTs());
        FileOutputStream os = new FileOutputStream(file);
        workbook.write(os);
        fip.close();
        os.close();
        logger.info("File Updated Successfully");
        return true;
    }
    public void makeEntryExchangeRate(ExchangeRateData exchangeRateData) throws Exception {
        File file=new File("exchangeRateOutputFile.xlsx");
        FileInputStream fip ;
        Workbook workbook;
        Sheet spreadsheet;
        if(!file.exists()){
            workbook=new XSSFWorkbook();
            spreadsheet=workbook.createSheet("exchangeRateOutputFile.xlsx");
            int rowCount=0;
            //Creating new row from the next row count
            Row row1 = spreadsheet.createRow(rowCount++);
            CellStyle dateCellStyle = workbook.createCellStyle();
            CreationHelper createHelper = workbook.getCreationHelper();
            dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
            CellStyle style = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBoldweight((short) 5);
            style.setFont(font);
            Cell cell1=row1.createCell(0);
            cell1.setCellStyle(style);
            cell1.setCellValue("BASE CURRENCY");
            Cell cell2=row1.createCell(1);
            cell2.setCellStyle(style);
            cell2.setCellValue("CONVERSION_CURRENCY");
            Cell cell3=row1.createCell(2);
            cell3.setCellStyle(style);
            cell3.setCellValue("RATE");
            Cell cell5=row1.createCell(3);
            cell5.setCellStyle(style);
            cell5.setCellValue("CREATE_TS");
            FileOutputStream os = new FileOutputStream(file);
            workbook.write(os);
            os.close();
        }
        fip= new FileInputStream(file);
        workbook= WorkbookFactory.create(fip);
        spreadsheet=workbook.getSheetAt(0);
        int rowCount=spreadsheet.getLastRowNum();
        //Creating new row from the next row count
        Row row = spreadsheet.createRow(++rowCount);
        CellStyle dateCellStyle = workbook.createCellStyle();
        CreationHelper createHelper = workbook.getCreationHelper();
        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
        row.createCell(0).setCellValue(exchangeRateData.getBase());
        row.createCell(1).setCellValue("USD");
        row.createCell(2).setCellValue(exchangeRateData.getRates().getUSD());
        Cell date=row.createCell(3);
        date.setCellStyle(dateCellStyle);
        date.setCellValue(exchangeRateData.getTimestamp().toString());
        FileOutputStream os = new FileOutputStream(file);
        workbook.write(os);
        fip.close();
        os.close();
        logger.info("File Updated Successfully");
    }
}
