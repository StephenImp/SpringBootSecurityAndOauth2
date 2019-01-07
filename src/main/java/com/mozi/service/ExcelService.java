package com.mozi.service;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by MOZi on 2018/11/16.
 */
public interface ExcelService {

     void exportList(String title, List data, OutputStream out) throws InvocationTargetException, IllegalAccessException;
}
