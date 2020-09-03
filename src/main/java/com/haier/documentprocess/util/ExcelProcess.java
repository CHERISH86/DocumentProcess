package com.haier.documentprocess.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.haier.documentprocess.entity.TGoods;
import com.haier.documentprocess.listener.EasyExcelListener;
import com.haier.documentprocess.service.GoodsService;
import lombok.Data;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cherish
 * @date 2020/8/31
 * @description
 */
@Data
public class ExcelProcess {
    //写Excel参数
    private List<TGoods> datas = new ArrayList<>();
    private String PATH = "";
    //写Excel流
    private ServletOutputStream servletOutputStream;

    /*读Excel参数*/
    //读Excel流
    private InputStream inputStream;
    //写入至数据库所需
    final GoodsService goodsService;
    public ExcelProcess(GoodsService goodsService){
        this.goodsService = goodsService;
    }

    //读Excel数据至数据库
    public void readFromExcel() throws IOException {
        ExcelReader excelReader = EasyExcel.read(inputStream, TGoods.class, new EasyExcelListener(goodsService)).build();
        //获取所有sheet
        List<ReadSheet> sheets = excelReader.excelExecutor().sheetList();
        for (ReadSheet readSheet : sheets) {
            //设置sheet表头行数 对应多行表头
            readSheet.setHeadRowNumber(SetExcelUtil.HEAD_ROW_NUMBER);
            excelReader.read(readSheet);
        }
        excelReader.finish();
        inputStream.close();
    }

    //写Excel
    public void writeToExcel() throws IOException {
        int count = datas.size();
        int sheetNum = count % SetExcelUtil.SHEET_COUNT == 0 ? count / SetExcelUtil.SHEET_COUNT : count / SetExcelUtil.SHEET_COUNT + 1;

        //EasyExcel构造器构造写法
        ExcelWriter excelWriter = EasyExcel.write(servletOutputStream).registerWriteHandler(new LongestMatchColumnWidthStyleStrategy()).build();

        for (int page = 1; page < sheetNum + 1; page++) {
            //数据分页
            int beginIndex = (page - 1) * SetExcelUtil.SHEET_COUNT;
            int endIndex = Math.min(page * SetExcelUtil.SHEET_COUNT, datas.size());
            List<TGoods> datasListByPage = datas.subList(beginIndex, endIndex);
            //每一页重新创建一个sheet
            WriteSheet sheet = EasyExcel.writerSheet(page, "第{" + page + "}页数据").build();
            //动态设置sheet标题
            sheet.setHead(SetExcelUtil.createHead());
            excelWriter.write(datasListByPage, sheet);
        }
        excelWriter.finish();
        servletOutputStream.close();
    }
}
