package com.codegym.service;

import com.codegym.model.Orders;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface ExcelFileService {
    ByteArrayInputStream export(List<Orders> ordersList, Long shop_id);

    ByteArrayInputStream exportMultiSheet(List<Orders> ordersList);

}
