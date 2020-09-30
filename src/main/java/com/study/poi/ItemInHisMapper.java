package com.study.poi;

import com.common.model.ItemsInHis;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Description:
 * @Author HuangShiming
 * @Date 2020/9/25
 */
public class ItemInHisMapper implements RowMapper<ItemsInHis> {
    @Override
    public ItemsInHis mapRow(ResultSet resultSet, int i) throws SQLException {

        String id = resultSet.getString("ID");
        String code = resultSet.getString("编码");
        String name = resultSet.getString("名称");
        String price = resultSet.getString("现价");
        ItemsInHis itemsInHis = new ItemsInHis();
        itemsInHis.setHisCode(code);
        itemsInHis.setHisId(id);
        itemsInHis.setHisName(name);
        itemsInHis.setHisPrice(price);
        return itemsInHis;
    }
}
