package com.study.poi;

import com.common.model.ItemsInHis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author HuangShiming
 * @Date 2020/9/24
 */
@Service("mainService")
public class MainServiceImpl implements MainService {

    @Autowired
    private QueryUtil queueUtil;

    @Override
    public List<ItemsInHis> getItemsInHis() {
        List<ItemsInHis> itemsInHis = queueUtil.getItemsInHis();
        return itemsInHis;
    }
}
