package org.xumin.myshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xumin.myshop.entity.Product;
import org.xumin.myshop.service.ProductService;
import org.xumin.myshop.service.ReportService;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Controller
@RequestMapping("admin/report")
public class ReportController {

    private final ReportService reportService;
    private final ProductService productService;

    @Autowired
    public ReportController(ReportService reportService, ProductService productService) {
        this.reportService = reportService;
        this.productService = productService;
    }

    @GetMapping("/excel")
    public ResponseEntity<ByteArrayResource> outputExcelListProduct() {
        try (ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
            String filename = "product.xlsx";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(new MediaType("application", "vnd.ms-excel"));
            headers.set(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename="+filename);

            List<Product> products = this.productService.findAll();
            reportService.outputListProduct(stream, products);
            return new ResponseEntity<>(new ByteArrayResource(stream.toByteArray()), headers, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
