package com.haier.documentprocess.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.haier.documentprocess.entity.TGoods;
import lombok.extern.slf4j.Slf4j;

/**
 * @author cherish
 * @date 2020/8/26
 * @description
 */
@Slf4j
public class EasyExcelListener extends AnalysisEventListener<TGoods> {
    @Override
    //读完每一行调用
    public void invoke(TGoods tGoods, AnalysisContext analysisContext) {
        log.info("商品信息（来源于Excel）：" + tGoods);
    }

    @Override
    //每个sheet读取完毕后调用
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("读取Excel中" + analysisContext.readSheetHolder().getSheetName() + "结束！");
    }
}
