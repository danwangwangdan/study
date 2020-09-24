package com.study.poi;

import com.common.model.Items;
import com.common.model.ItemsInHis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StartMain implements CommandLineRunner {

    @Autowired
    private MainService mainService;

    @Override
    public void run(String... args) throws Exception {
        List<Items> itemsInExcel = ExcelTools.readExcel("src/20200924.xlsx");
        List<ItemsInHis> itemsInHis = mainService.getItemsInHis();
        for (Items item : itemsInExcel) {
            // 1.根据编码比对两个列表，找出在HIS中存在但在EXCEL不存在的项目，并导出为另一份EXCEL
            // 2.根据编码匹配HIS中的项目，如果价格不同，则更新HIS中项目的价格与执行时间为10月1日；如果价格相同，则忽略

        }

    }
}