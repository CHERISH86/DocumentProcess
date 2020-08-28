package com.haier.documentprocess.service;

import com.haier.documentprocess.entity.TGoods;

import java.util.List;

/**
 * @author cherish
 * @date 2020/8/24
 * @description
 */
public interface GoodsService {
    Integer getCount();
    List<TGoods> getAll();
    List<TGoods> getListByPage(Integer pageon);
    int insertSelective(TGoods tGoods);
    int insertList(List<TGoods> tGoodsList);
}
