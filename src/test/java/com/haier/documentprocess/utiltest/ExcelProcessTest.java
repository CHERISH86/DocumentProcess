package com.haier.documentprocess.utiltest;

import com.haier.documentprocess.DocumentProcessApplicationTests;
import com.haier.documentprocess.service.GoodsService;
import com.haier.documentprocess.util.ExcelProcess;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * @author cherish
 * @date 2020/8/31
 * @description
 */
@Slf4j
public class ExcelProcessTest extends DocumentProcessApplicationTests {
    @Autowired
    GoodsService goodsService;

    @Test
    public void wirteToExcelTest() throws IOException {
        double begin = System.currentTimeMillis();

        ExcelProcess excelProcess = new ExcelProcess(goodsService);
        excelProcess.setPATH("C:\\Users\\20023648\\Desktop\\Excel文件\\Goods.xlsx");
        excelProcess.setDatas(goodsService.getAll());
        excelProcess.writeToExcel();

        double end = System.currentTimeMillis();
        log.info("恭喜 writeToExcel成功！所用时间：" + (end - begin) / 1000 + "s");
    }

    @Test
    public void readFromExcelTest() throws IOException {
        double begin = System.currentTimeMillis();

        ExcelProcess excelProcess = new ExcelProcess(goodsService);
        excelProcess.setPATH("C:\\Users\\20023648\\Desktop\\Excel文件\\Goods.xlsx");
        excelProcess.readFromExcel();

        double end = System.currentTimeMillis();
        log.info("恭喜 readFromExcel成功！所用时间：" + (end - begin) / 1000 + "s");
    }

}
