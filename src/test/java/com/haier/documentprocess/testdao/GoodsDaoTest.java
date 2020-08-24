package com.haier.documentprocess.testdao;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.haier.documentprocess.DocumentProcessApplicationTests;
import com.haier.documentprocess.dao.GoodsDao;
import com.haier.documentprocess.entity.TGoods;
import com.haier.documentprocess.service.GoodsService;
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

    @Autowired
    GoodsService goodsService;

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
     */
    @Test
    public void selectByRowBoundsTest(){
        TGoods tGood = new TGoods();
        RowBounds rowBounds = new RowBounds(0,2);
        List<TGoods> tGoods = goodsDao.selectByRowBounds(tGood,rowBounds);
        for (TGoods good : tGoods) {
            log.info("商品信息：" + good);
        }
    }

    /**
     * @description DB2Excel分页导出（50000）
     */
    @Test
    public void DB2Excel(){

        Integer pages = goodsService.getCount()/50000==0 ? goodsService.getCount()/50000 : goodsService.getCount()/50000+1;
        List<TGoods> goodsListByPage = goodsService.getListByPage(pages);
        String PATH = "C:\\Users\\20023648\\Desktop\\Excel文件\\Goods.xlsx";
        //EasyExcel构造器构造写法
        ExcelWriter excelWriter = EasyExcel.write(PATH,TGoods.class).build();
        WriteSheet sheet = EasyExcel.writerSheet("第一页").build();
        excelWriter.write(goodsListByPage,sheet);
        excelWriter.finish();
        log.info("恭喜 DB2Excel成功！");
    }
}
