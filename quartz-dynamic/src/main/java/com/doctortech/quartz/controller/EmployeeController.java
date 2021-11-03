package com.doctortech.quartz.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.doctortech.quartz.entity.EmployeeEntity;
import com.doctortech.quartz.listener.EmployeeEntityListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: foo
 * @Date: 2021-10-26 14:57
 * @description:
 */
@RestController
public class EmployeeController {

    @GetMapping("/excel")
    public void excel() {
        // 写法4：
        String fileName = "/Users/onepiecekevin/Downloads/广州博士信息技术研究院有限公司_员工导出记录-202110251555292.xlsx";
        // 一个文件一个reader
        ExcelReader excelReader = null;
        try {
            excelReader = EasyExcel.read(fileName, EmployeeEntity.class, new EmployeeEntityListener()).build();
            // 构建一个sheet 这里可以指定名字或者no
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            // 读取一个sheet
            excelReader.read(readSheet);
        } finally {
            if (excelReader != null) {
                // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
                excelReader.finish();
            }
        }
    }

}
