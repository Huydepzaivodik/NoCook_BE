package com.codegym.controller;

import com.codegym.model.Orders;
import com.codegym.service.ExcelFileService;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Vector;

@Controller
@CrossOrigin("*")
@RequestMapping("/excel")
public class DowloadExcelController {

    @Autowired
    private ExcelFileService excelFileService;

    @GetMapping("/dowload-excel-orders-file/{id}")
    public ResponseEntity<String> downloadExcelFile(@PathVariable Long id) throws IOException {
        List<Orders> orders = new Vector<>();

        // Export data to a ByteArrayInputStream
        ByteArrayInputStream byteArrayInputStream = excelFileService.export(orders, id);

        // Đường dẫn đến thư mục D:\Order
        Path directoryPath = Paths.get("D:/Order");

        // Kiểm tra và tạo thư mục nếu nó không tồn tại
        if (!Files.exists(directoryPath)) {
            Files.createDirectories(directoryPath);
        }

        // Đường dẫn đầy đủ đến tệp D:/Order/Order.xlsx
        Path filePath = directoryPath.resolve("Order.xlsx");

        // Write the ByteArrayInputStream data to a file
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath.toFile())) {
            IOUtils.copy(byteArrayInputStream, fileOutputStream);
        }

        // Kiểm tra nếu tệp đã tồn tại
        if (Files.exists(filePath)) {
            return ResponseEntity.ok("File saved successfully at " + filePath.toAbsolutePath().toString());
        } else {
            return ResponseEntity.status(500).body("Failed to save the file.");
        }

    }


}
