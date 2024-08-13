package com.codegym.service.impl;

import com.codegym.model.Coupon;
import com.codegym.model.Orders;
import com.codegym.model.Shop;
import com.codegym.model.User;
import com.codegym.repository.OrdersRepository;
import com.codegym.service.ExcelFileService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ExcelFileServiceImpl implements ExcelFileService {

    @Autowired
    private OrdersRepository ordersRepository;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public ByteArrayInputStream export(List<Orders> ordersList, Long shop_id) {

        ordersList = ordersRepository.getAllOrdersByShopId(shop_id);

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Orders");

            Row row = sheet.createRow(0);

            // Define header cell style
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
            headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            // Creating header cells
            Cell cell = row.createCell(0);
            cell.setCellValue("Id");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(1);
            cell.setCellValue("Shipping Address");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(2);
            cell.setCellValue("Date");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(3);
            cell.setCellValue("Status");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(4);
            cell.setCellValue("Total");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(5);
            cell.setCellValue("Coupon");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(6);
            cell.setCellValue("Delivery");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(7);
            cell.setCellValue("Name Of Shop");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(8);
            cell.setCellValue("Name Of User");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(9);
            cell.setCellValue("Note");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(10);
            cell.setCellValue("Order Date");
            cell.setCellStyle(headerCellStyle);

            // Define cell style for dates
            CellStyle dateCellStyle = workbook.createCellStyle();
            CreationHelper creationHelper = workbook.getCreationHelper();
            dateCellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-mm-dd"));


            for (int i = 0; i < ordersList.size(); i++) {
                Row dataRow = sheet.createRow(i + 1);
                dataRow.createCell(0).setCellValue(ordersList.get(i).getId());
                dataRow.createCell(1).setCellValue(ordersList.get(i).getShippingAddress());
                dataRow.createCell(2).setCellValue(ordersList.get(i).getDate());
                dataRow.createCell(3).setCellValue(ordersList.get(i).getStatus());
                dataRow.createCell(4).setCellValue(ordersList.get(i).getTotal());


                String couponNames = ordersList.get(i).getCoupons().stream()
                        .map(counpon -> String.valueOf(counpon.getDiscount()))
                        .collect(Collectors.joining());
                dataRow.createCell(5).setCellValue("Giảm "+couponNames);

                String delivery_name = ordersList.get(i).getDelivery().getName();
                dataRow.createCell(6).setCellValue(delivery_name);

                String shopNames = ordersList.get(i).getShops().stream()
                        .map(Shop::getName)
                        .collect(Collectors.joining(""));
                dataRow.createCell(7).setCellValue(shopNames);

                String user_name = ordersList.get(i).getUser().getName();
                dataRow.createCell(8).setCellValue(user_name);

                dataRow.createCell(9).setCellValue(ordersList.get(i).getNote());

                Cell dateCell = dataRow.createCell(10);
                dateCell.setCellValue(ordersList.get(i).getDate());
                dateCell.setCellStyle(dateCellStyle);
            }

            // Making size of column auto resize to fit with data
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);
            sheet.autoSizeColumn(5);
            sheet.autoSizeColumn(6);
            sheet.autoSizeColumn(7);
            sheet.autoSizeColumn(8);
            sheet.autoSizeColumn(9);
            sheet.autoSizeColumn(10);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (IOException ex) {
            logger.error("Error during export Excel file", ex);
            return null;
        }
    }

    @Override
    public ByteArrayInputStream exportMultiSheet(List<Orders> ordersList) {
        return null;
    }
}
