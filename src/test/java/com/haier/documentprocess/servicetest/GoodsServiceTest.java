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
import com.haier.documentprocess.service.GoodsServiceImpl;
import com.haier.documentprocess.util.SetExcelUtil;
import javafx.scene.effect.SepiaTone;
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
     * @result 5001条||161KB||1.718s(07)1.198s(03)
     */
    @Test
    public void DB2Excel() {
        double begin = System.currentTimeMillis();

        //获取总记录数，按每个sheet50000条进行分页
        int count = goodsService.getCount();
        int sheetNum = count % 5000 == 0 ? count / 5000 : count / 5000 + 1;
        String PATH = "C:\\Users\\20023648\\Desktop\\Excel文件\\Goods.xlsx";

        //EasyExcel构造器构造写法
        ExcelWriter excelWriter = EasyExcel.write(PATH, TGoods.class).build();
        for (int page = 1; page < sheetNum + 1; page++) {
            List<TGoods> goodsListByPage = goodsService.getListByPage(page);
            //每一页重新创建一个sheet
            WriteSheet sheet = EasyExcel.writerSheet(page, "第{" + page + "}页数据").build();
            //动态设置sheet标题
            sheet.setHead(SetExcelUtil.createHead());
            excelWriter.write(goodsListByPage, sheet);
        }
        excelWriter.finish();
        double end = System.currentTimeMillis();
        log.info("恭喜 DB2Excel成功！所用时间：" + (end - begin) / 1000 + "s");
    }

    /**
     * @description Excel2DB测试
     * @result 5001条||161KB||285.638s-319.98s(07 逐条写入)
     *                       3.442s(07 增加缓存BATCH_COUNT)
     */
    @Test
    public void Excel2DB(){
        double begin = System.currentTimeMillis();

        String PATH = "C:\\Users\\20023648\\Desktop\\Excel文件\\Goods.xlsx";
        ExcelReader excelReader = EasyExcel.read(PATH,TGoods.class,new EasyExcelListener(goodsService)).build();
        //获取所有sheet
        List<ReadSheet> sheets = excelReader.excelExecutor().sheetList();
        //获取一个sheet
        //ReadSheet readSheet = EasyExcel.readSheet(0).build();
        for (ReadSheet readSheet:sheets){
            //设置sheet表头行数
            readSheet.setHeadRowNumber(SetExcelUtil.headRowNumber);
            excelReader.read(readSheet);
        }
        excelReader.finish();

        double end = System.currentTimeMillis();
        log.info("恭喜 Excel2DB成功！所用时间：" + (end - begin) / 1000 + "s");
    }
}
