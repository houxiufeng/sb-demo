package com.example.demo.service;

import com.example.demo.vos.AjaxResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;

/**
 * 可以将结果转化为xls后缀的excel文档并下载
 */
//@Service
public class ResponseToXlsConverter extends AbstractHttpMessageConverter<AjaxResponse> {

    public ResponseToXlsConverter() {
        super(MediaType.valueOf("application/vnd.ms-excel"));
    }
    @Override
    protected boolean supports(Class<?> clazz) {
        return clazz == AjaxResponse.class;
    }

    @Override
    protected AjaxResponse readInternal(Class<? extends AjaxResponse> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    protected void writeInternal(AjaxResponse result, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue(result.getCode());
        row.createCell(1).setCellValue(result.getMessage());
        row.createCell(2).setCellValue(result.getData().toString());
        workbook.write(outputMessage.getBody());
    }
}
