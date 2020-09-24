package com.study.poi;

import com.common.model.Items;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ExcelRWTest {
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void readExcel() throws Exception {
        String path = "src/20200924.xlsx";
        List<Items> infoList = ExcelTools.readExcel(path);
        for (Items item : infoList) {
            System.out.println(item.toString());
        }
    }

}