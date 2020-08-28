package com.haier.documentprocess.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.haier.documentprocess.entity.TGoods;
import com.haier.documentprocess.service.GoodsService;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cherish
 * @date 2020/8/26
 * @description
 */
@Slf4j
public class EasyExcelListener extends AnalysisEventListener<TGoods> {
    private static final int BATCH_COUNT = 3000;
    List<TGoods> tGoodsList = new ArrayList<TGoods>();

    final GoodsService goodsService;
    public EasyExcelListener(GoodsService goodsService){
        this.goodsService = goodsService;
    }

    @Override
    //读完每一行调用
    public void invoke(TGoods tGoods, AnalysisContext analysisContext) {
        //goodsService.insertSelective(tGoods);
        //log.info("商品信息（来源于Excel）：" + tGoods);
        tGoodsList.add(tGoods);
        //达到缓存数量后，去存储一次数据库
        if (tGoodsList.size() >= BATCH_COUNT){
            log.info("******插入了" + goodsService.insertList(tGoodsList) + "条数据！******");
            tGoodsList.clear();
        }
    }

    @Override
    //每个sheet读取完毕后调用
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        //将不足PATCH_COUNT的数据保存
        goodsService.insertList(tGoodsList);
        //每个sheet读完还要读其他sheet
        tGoodsList.clear();
        log.info("读取Excel中" + analysisContext.readSheetHolder().getSheetName() + "结束！");
    }
}
