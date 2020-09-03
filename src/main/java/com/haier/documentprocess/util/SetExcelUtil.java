package com.haier.documentprocess.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cherish
 * @date 2020/8/28
 * @description
 */
public class SetExcelUtil {
    public static final int HEAD_ROW_NUMBER = 2;
    public static final int SHEET_COUNT = 5000;
    //动态创建表头
    public static List<List<String>> createHead(){
        List<List<String>> head = new ArrayList<>();
        //最小子列数对应多少
        List<String> headColumn1 = new ArrayList<>();
        List<String> headColumn2 = new ArrayList<>();
        List<String> headColumn3 = new ArrayList<>();
        List<String> headColumn4 = new ArrayList<>();
        List<String> headColumn5 = new ArrayList<>();

        headColumn1.add("商品名称");headColumn1.add("商品名称");
        headColumn2.add("生产属性");headColumn2.add("成本");
        headColumn3.add("生产属性");headColumn3.add("价格");
        headColumn4.add("生产属性");headColumn4.add("生产日期");
        headColumn5.add("商品编号");headColumn5.add("商品编号");

        head.add(headColumn1);
        head.add(headColumn2);
        head.add(headColumn3);
        head.add(headColumn4);
        head.add(headColumn5);

        return head;
    }
}
