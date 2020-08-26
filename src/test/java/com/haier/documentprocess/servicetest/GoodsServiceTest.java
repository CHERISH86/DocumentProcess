package com.haier.documentprocess.servicetest;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.haier.documentprocess.DocumentProcessApplicationTests;
import com.haier.documentprocess.entity.TGoods;
import com.haier.documentprocess.listener.EasyExcelListener;
import com.haier.documentprocess.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author cherish
 * @date 2020/8/25
 * @description
 */
@Slf4j
public class GoodsServiceTest extends DocumentProcessApplicationTests {
    @Autowired
    GoodsService goodsService;

    /**
     * @description DB2Excel分页导出（50000）
     */
    @Test
    public void DB2Excel() {
        double begin = System.currentTimeMillis();

        //获取总记录数，按每个sheet50000条进行分页
        int count = goodsService.getCount();
        int sheetNum = count % 2 == 0 ? count / 2 : count / 2 + 1;
        String PATH = "C:\\Users\\20023648\\Desktop\\Excel文件\\Goods.xlsx";

        //EasyExcel构造器构造写法
        ExcelWriter excelWriter = EasyExcel.write(PATH, TGoods.class).build();
        for (int page = 1; page < sheetNum + 1; page++) {
            List<TGoods> goodsListByPage = goodsService.getListByPage(page);
            //每一页重新创建一个sheet
            WriteSheet sheet = EasyExcel.writerSheet(page, "第{" + page + "}页数据").build();
            excelWriter.write(goodsListByPage, sheet);
        }
        excelWriter.finish();
        double end = System.currentTimeMillis();
        log.info("恭喜 DB2Excel成功！所用时间：" + (end - begin) / 1000 + "s");
    }

    /**
     * @description Excel2DB测试
     */
    @Test
    public void Excel2DB(){
        String PATH = "C:\\Users\\20023648\\Desktop\\Excel文件\\Goods.xlsx";
        ExcelReader excelReader = EasyExcel.read(PATH,TGoods.class,new EasyExcelListener()).build();
        //获取所有sheet
        List<ReadSheet> sheets = excelReader.excelExecutor().sheetList();
        //ReadSheet readSheet = EasyExcel.readSheet(0).build();
        for (ReadSheet readSheet:sheets){
            excelReader.read(readSheet);
        }
        excelReader.finish();
    }
}
