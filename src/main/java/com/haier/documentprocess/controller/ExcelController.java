package com.haier.documentprocess.controller;

import com.haier.documentprocess.entity.TGoods;
import com.haier.documentprocess.service.GoodsService;
import com.haier.documentprocess.util.ExcelProcess;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.min;

/**
 * @author cherish
 * @date 2020/8/31
 * @description
 */
@Slf4j
@RestController
public class ExcelController {
    @Autowired
    GoodsService goodsService;

    public static final int PAGE_DATA_COUNT =30000;

    /**
     * Excel2DB
     * @param file
     * @throws IOException
     */
    @PostMapping("/goods")
    public void addGoods(@RequestParam("file") MultipartFile file) throws IOException {
        double begin = System.currentTimeMillis();

        ExcelProcess excelProcess = new ExcelProcess(goodsService);
        excelProcess.setInputStream(file.getInputStream());
        excelProcess.readFromExcel();

        double end = System.currentTimeMillis();
        log.info("******readFromExcel成功！用时：" + (end - begin) / 1000 + "s******");
    }

    /**
     * DB2Excel
     * @param httpServletResponse
     * @throws IOException
     */
    @GetMapping("/goods")
    public void getGoods(HttpServletResponse httpServletResponse) throws IOException {
        //设置文件基本属性
        httpServletResponse.setContentType("application/vnd.ms-excel");
        httpServletResponse.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("Goods_" + goodsService.getCount(), "UTF-8");
        httpServletResponse.setHeader("Content-Disposition", "attachment;filename*=UTF-8''" + fileName + ".xlsx");

        //读取数据库数据
        double begin = System.currentTimeMillis();

        List<TGoods> allGoods = new ArrayList<>();
        int count = goodsService.getCount();
        int pages = count % PAGE_DATA_COUNT == 0 ? count / PAGE_DATA_COUNT : count / PAGE_DATA_COUNT + 1;
        for (int page=1; page<pages+1; page++){
            allGoods.addAll(goodsService.getListByPage(page, PAGE_DATA_COUNT));
            double readTime = (System.currentTimeMillis()-begin)/1000;
            log.info("读取数据库" +min(page* PAGE_DATA_COUNT,count)+ "条数据用时："+ readTime+"s");
        }
        double getDataTime = System.currentTimeMillis();
        log.info("******读取数据成功！用时：" + (getDataTime - begin) / 1000 + "s******");
        //输出数据至Excel文件
        ExcelProcess excelProcess = new ExcelProcess(goodsService);
        excelProcess.setDatas(allGoods);
        excelProcess.setServletOutputStream(httpServletResponse.getOutputStream());
        excelProcess.writeToExcel();

        double end = System.currentTimeMillis();
        log.info("******writeToExcel成功！用时：" + (end - getDataTime) / 1000 + "s******");
        log.info("******总用时：" + (end - begin) / 1000 + "s******");
    }
}
