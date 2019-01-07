package com.mozi.controller;

import com.mozi.entity.UserSecurityEntity;
import com.mozi.service.ExcelService;
import com.mozi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MOZi on 2018/11/16.
 */
@Controller
public class ExcelController {

    @Autowired
    ExcelService excelServiceImpl;

    @Autowired
    UserService userServiceImpl;

    @RequestMapping("excel")
    public void testExcel(HttpServletResponse response){
        try {
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment; filename=" + new String("Excel表格名".getBytes("gb2312"), "iso8859-1") + ".xls");


            UserSecurityEntity user = userServiceImpl.findByUsername("wpw");
            System.out.println(user);

            List<UserSecurityEntity> list = new ArrayList<>();
            list.add(user);

            excelServiceImpl.exportList("Excel表格名",list , response.getOutputStream());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
