package com.study.poi;

import com.common.model.ItemsInHis;

import java.util.List;

/**
 * @Description:
 * @Author HuangShiming
 * @Date 2020/9/24
 */
public interface MainService {

    List<ItemsInHis> getItemsInHis();


    int updatePrice(String code,String price);
}
