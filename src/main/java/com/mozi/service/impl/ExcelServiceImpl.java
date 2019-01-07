package com.mozi.service.impl;

import com.mozi.annotation.ExcelTitle;
import com.mozi.service.ExcelService;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by MOZi on 2018/11/16.
 */
@Service
public class ExcelServiceImpl implements ExcelService {

    @Override
    public void exportList(String title, List data, OutputStream out) throws InvocationTargetException, IllegalAccessException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(title);
        if(data.size() < 1){
            return;
        }

        //获取导出对象的方法
        Method[] methods = data.get(0).getClass().getDeclaredMethods();
        List<Method> getterMethods = new ArrayList<Method>();
        for(Method method : methods) {
            method.getDeclaredAnnotations();
            if(method.getName().contains("get") && method.isAnnotationPresent(ExcelTitle.class)) {   //过滤方法，只获取ExcelTitle注解的get方法
                getterMethods.add(method);
            }
        }
        Collections.sort(getterMethods, new Comparator<Method>() {
            @Override
            public int compare(Method o1, Method o2) {
                int i = o1.getAnnotation(ExcelTitle.class).order() - o2.getAnnotation(ExcelTitle.class).order();
                return i;
            }
        });
        //设置表头
        int index = 0;
        HSSFRow row = sheet.createRow(index);
        for(int i = 0; i < getterMethods.size(); i++) {
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString textString = new HSSFRichTextString(getterMethods.get(i).getAnnotation(ExcelTitle.class).value());
            cell.setCellValue(textString);
        }

        //表格中填充数据
        for(Object obj : data) {
            index++;
            row = sheet.createRow(index);
            for(int i =0; i < getterMethods.size(); i++) {
                HSSFCell cellData = row.createCell(i);
                HSSFRichTextString textData = null;
                String value = new String(getterMethods.get(i).invoke(obj, null) + "");
                if(!value.equals("null")) {
                    textData = new HSSFRichTextString(value);
                } else {
                    textData = new HSSFRichTextString("");
                }
                cellData.setCellValue(textData);
            }
        }
        try {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
