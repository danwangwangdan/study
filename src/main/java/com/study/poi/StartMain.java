package com.study.poi;

import com.common.model.ItemsInExcel;
import com.common.model.ItemsInHis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class StartMain implements CommandLineRunner {

    @Autowired
    private MainService mainService;

    @Override
    public void run(String... args) throws Exception {
        List<ItemsInExcel> itemsInExcel = ExcelTools.readExcel("src/20200924.xlsx");
        List<ItemsInHis> itemsInHis = mainService.getItemsInHis();
        int effectCount = 0;
        for (int y = 0, itemsInExcelSize = itemsInExcel.size(); y < itemsInExcelSize; y++) {
            for (int i = 0, itemsInHisSize = itemsInHis.size(); i < itemsInHisSize; i++) {
                ItemsInExcel itemInExcel = itemsInExcel.get(y);
                ItemsInHis itemInHis = itemsInHis.get(i);
                if(itemInExcel.getCode().equals(itemInHis.getHisCode())){
                    // 标记Excel中已找到的项目，以便核查是否所有项目都已经修改了
                    itemInExcel.setIsUpdate(1);
                    if(!itemInExcel.getPrice().equals(itemInHis.getHisPrice())){
                        // 编码相同,价格不同，更新HIS价格
                        int z = mainService.updatePrice(itemInExcel.getCode(),itemInExcel.getPrice());
                        if (z>0) effectCount++;
                        // 标记HIS中已更新的项目及其对应Excel项目的其他信息，以便导出后查找未更新的项目
                        itemInHis.setIsUpdate(1);
                        itemInHis.setBaseExcelPrice(itemInExcel.getPrice());
                        itemInHis.setHisDetail(itemInExcel.getDetail());
                        itemInHis.setHisDoc(itemInExcel.getDoc());
                    }
                }

            }
        }
        System.out.println("更新了"+effectCount+"条记录！");
        //导出更新标记后的HIS项目
        Map<String,String> titleMap = new LinkedHashMap<String,String>();
        titleMap.put("code", "编码");
        titleMap.put("name", "项目名称");
        titleMap.put("detail", "项目内涵");
        titleMap.put("price", "2020二类价格");
        titleMap.put("doc", "说明");
        titleMap.put("isUpdate", "是否已更新");
        String sheetName = "信息导出";
        ExportExcel.excelExport(itemsInHis,titleMap,sheetName);

    }
}