package org.xumin.myshop.service;

import org.springframework.stereotype.Service;
import org.xumin.myshop.entity.Product;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public interface ReportService {
    void outputListProduct(ByteArrayOutputStream outputStream, List<Product> productList) throws IOException;
}
