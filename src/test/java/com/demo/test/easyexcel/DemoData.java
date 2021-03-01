package com.demo.test.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author scc
 * @date 2020/5/28 16:30
 */
@Data
public class DemoData {

    @ExcelProperty(index = 0)
    private String name;
    @ExcelProperty(index = 1)
    private int age;
}
