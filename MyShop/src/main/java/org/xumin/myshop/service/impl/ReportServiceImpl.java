package org.xumin.myshop.service.impl;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.xumin.myshop.entity.Product;
import org.xumin.myshop.service.ReportService;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Override
    public void outputListProduct(ByteArrayOutputStream outputStream, List<Product> list) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("products");
        int rowNum = 0;
        for (Product product : list) {
            XSSFRow row = sheet.createRow(rowNum++);
            int cellNum = 0;
            for (int i = 0; i< Product.class.getDeclaredFields().length; i++){
                Cell cell = row.createCell(cellNum++);
                if(cellNum==1) cell.setCellValue(product.getId());
                if(cellNum==2) cell.setCellValue(product.getName());
                if(cellNum==3) cell.setCellValue(product.getImage());
                if(cellNum==4) cell.setCellValue(product.getPrice());
                if(cellNum==5) cell.setCellValue(product.getQuantity());
                if(cellNum==6) cell.setCellValue(product.getDescription());
                if(cellNum==7) cell.setCellValue(product.getCategory().getName());
                if(cellNum==8) cell.setCellValue(product.getMaterial().getName());
            }
        }

//        File file = new File("test.xlsx");
//        try(
//                FileOutputStream fileOutputStream = new FileOutputStream(file);
//        ) {
//            workbook.write(fileOutputStream);
//            System.out.println("OK");
//        } catch (Exception e) {
//            System.out.println("Error");
//        }

        workbook.write(outputStream);
    }
}
