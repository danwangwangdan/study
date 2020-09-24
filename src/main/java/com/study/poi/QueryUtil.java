package com.study.poi;

import com.common.model.ItemsInHis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Types;
import java.util.List;

@Component
public class QueryUtil {
    private static final Logger log = LoggerFactory.getLogger(QueryUtil.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ItemsInHis> getItemsInHis() {
        String sql = "select a.ID,a.编码, a.名称, b.现价\n" +
                "  from 收费细目 a\n" +
                " INNER JOIN 收费价目 b\n" +
                "    on a.ID = b.收费细目id\n" +
                "   and (b.终止日期 > sysdate or b.终止日期 is null)\n" +
                "   and b.执行日期 < sysdate\n" +
                "   and nvl(a.撤档时间,sysdate+1)>sysdate\n" +
                "   and a.类别 not in ('4', '5', '6', '7')";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ItemsInHis.class));
    }

    public int updatePrice(String itemId, String price) {
        String sql = "update 收费价目 set price = ?, 执行日期 = to_date('2020-10-01','YYYY-MM-DD') where 收费细目id = ? and (终止日期 > sysdate or 终止日期 is null) and b.执行日期 < sysdate";
        return jdbcTemplate.update(sql, new Object[]{price,itemId}, new int[]{Types.VARCHAR,Types.VARCHAR});
    }
}
