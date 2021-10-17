package com.codeXie.utils;

import com.codeXie.pojo.Duty;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import java.io.OutputStream;
import java.util.List;

public class ExcelUtil {
    public static void  exportExcel(List<Duty> data, List<String> colNames, OutputStream outputStream ){
        // 创建一个Excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建一个工作表
        HSSFSheet sheet = workbook.createSheet("考勤信息表");
        // 添加表头行
        HSSFRow hssfRow = sheet.createRow(0);
        // 设置单元格格式居中
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);

        // 添加表头内容
        for(int i=0; i<colNames.size();i++){
            HSSFCell headCell = hssfRow.createCell(i);
            headCell.setCellValue(colNames.get(i));
            headCell.setCellStyle(cellStyle);
        }



        // 添加数据内容
        for (int i = 0; i < data.size(); i++) {
            hssfRow = sheet.createRow((int) i + 1);
            Duty duty = data.get(i);

            // 创建单元格，并设置值
            HSSFCell cell = hssfRow.createCell(0);
            cell.setCellValue(duty.getEmprid());
            cell.setCellStyle(cellStyle);

            cell = hssfRow.createCell(1);
            cell.setCellValue(duty.getEmployee().getRealname());
            cell.setCellStyle(cellStyle);

            cell = hssfRow.createCell(2);
            cell.setCellValue(duty.getDept().getDeptname());
            cell.setCellStyle(cellStyle);

            cell = hssfRow.createCell(3);
            cell.setCellValue(duty.getDtdate());
            cell.setCellStyle(cellStyle);

            cell = hssfRow.createCell(4);
            cell.setCellValue(duty.getSignintime());
            cell.setCellStyle(cellStyle);

            cell = hssfRow.createCell(5);
            cell.setCellValue(duty.getSignouttime());
            cell.setCellStyle(cellStyle);
        }

        // 保存Excel文件
        try {
            workbook.write(outputStream);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
