package com.haier.documentprocess.daotest;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.haier.documentprocess.DocumentProcessApplicationTests;
import com.haier.documentprocess.dao.GoodsDao;
import com.haier.documentprocess.entity.TGoods;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author cherish
 * @date 2020/8/24
 * @description
 */
@Slf4j
public class GoodsDaoTest extends DocumentProcessApplicationTests {
    @Autowired
    private GoodsDao goodsDao;

    /**
     * @description GoodsDao获取数据库总记录数测试
     */
    @Test
    public void selectCountTest(){
        TGoods tGoods = new TGoods();
        int tGoodsNum = goodsDao.selectCount(tGoods);
        log.info("数据库总记录数" + tGoodsNum);
    }

    /**
     * @description 分页获取商品信息,打印至控制台
     * @param offset-起始位置、limit-读取条数
     */
    @Test
    public void selectByRowBoundsTest(){
        TGoods tGood = new TGoods();
        RowBounds rowBounds = new RowBounds(0,2);
        List<TGoods> tGoods = goodsDao.selectByRowBounds(tGood,rowBounds);
        for (TGoods goods : tGoods) {
            log.info("商品信息：" + goods);
        }
    }

    /**
     * @description 从数据库中读取复杂类型数据：生产日期、商品编号、布尔类型
     */
    @Test
    public void readAllTest(){
        List<TGoods> result =  goodsDao.selectAll();
        for (TGoods goods : result){
            log.info("商品信息(包含日期等)：" + goods);
        }
    }
}
