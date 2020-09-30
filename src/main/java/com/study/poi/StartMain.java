package com.study.poi;

import com.alibaba.druid.util.StringUtils;
import com.common.model.ItemsInExcel;
import com.common.model.ItemsInHis;
import com.common.model.SkipItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class StartMain implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(StartMain.class);
    @Autowired
    private MainService mainService;

    public static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void run(String... args) throws Exception {
        List<ItemsInExcel> itemsInExcel = ExcelTools.readExcel("src/20200925.xlsx");
        List<ItemsInHis> itemsInHis = mainService.getItemsInHis();
        List<SkipItem> skipItemList = new ArrayList<>();
        String skipItemCode = "";
        int shouldUpdateCount = 0, effectedCount = 0;
        a:
        for (ItemsInExcel itemInExcel : itemsInExcel) {
            for (ItemsInHis itemInHis : itemsInHis) {
                try {
                    // 非数值的价格不更新
                    if (!isNumeric(itemInExcel.getPrice())) continue a;
                    if (itemInExcel.getCode().equals(itemInHis.getHisCode())) {
                        // 编码相同，内涵为空且说明为空，价格为空或者为未定/自主定价的，则不需要更新
                        if (StringUtils.isEmpty(itemInExcel.getDetail()) && StringUtils.isEmpty(itemInExcel.getDoc()) || (
                                StringUtils.isEmpty(itemInExcel.getPrice()) || itemInExcel.getPrice().equalsIgnoreCase("未定") || itemInExcel.getPrice().equalsIgnoreCase("自主定价"))) {
                            itemInExcel.setIsNotNeedUpdate(1);
                        }
                        // 编码相同，价格不同，更新HIS价格
                        if (!itemInExcel.getPrice().equals(itemInHis.getHisPrice())) {
                            // 设置已更新标记
                            itemInExcel.setIsUpdatePrice(1);
                            skipItemCode = itemInExcel.getCode();
                            shouldUpdateCount++;
                            // todo 更新前注意备份好 收费价目 表
                            int z = mainService.updatePrice(itemInHis.getHisId(), itemInExcel.getPrice());
                            if (z > 0) effectedCount++;
                            // 标记HIS中已更新的项目及其对应Excel项目的其他信息，以便导出后查找未更新的项目
                            //itemInHis.setIsUpdate(1);
                            //itemInHis.setBaseExcelPrice(itemInExcel.getPrice());
                            //itemInHis.setHisDetail(itemInExcel.getDetail());
                            //itemInHis.setHisDoc(itemInExcel.getDoc());
                        }
                    }
                } catch (Exception e) {
                    // 出现异常，需要记录跳过的项目编码，以便后续导出
                    log.warn("出现异常，记录跳过的项目编码：" + skipItemCode);
                    log.warn(e.toString());
                    SkipItem skipItem = new SkipItem();
                    skipItem.setCode(skipItemCode);
                    skipItem.setErrorReason(e.toString());
                    skipItemList.add(skipItem);

                }
            }
        }
        log.info("应更新" + shouldUpdateCount + "条记录，实际更新" + effectedCount + "条记录，因出现错误跳过了" + skipItemList.size() + "条。");
        // 导出出现错误而跳过的项目
        Map<String, String> errorMap = new LinkedHashMap<String, String>();
        errorMap.put("code", "编码");
        errorMap.put("errorReason", "错误原因");
        ExportExcel.exportExcel(skipItemList, errorMap, "跳过的项目");

        //Map<String,String> errorMap = new LinkedHashMap<String,String>();
        //errorMap.put("code", "编码");
        //errorMap.put("errorReason", "错误原因");
        //ExportExcel.exportExcel(skipItemList,errorMap,"");
        ////导出更新标记后的HIS项目
        //Map<String,String> titleMap = new LinkedHashMap<String,String>();
        //titleMap.put("code", "编码");
        //titleMap.put("name", "项目名称");
        //titleMap.put("detail", "项目内涵");
        //titleMap.put("price", "二类价格");
        //titleMap.put("doc", "说明");
        //titleMap.put("isUpdatePrice", "是否已更新价格");
        //titleMap.put("isNeedUpdate", "是否需要手工更新");
        //ExportExcel.exportExcel(itemsInExcel,titleMap,"");
    }
}