package com.haier.documentprocess.service;

import com.haier.documentprocess.entity.TGoods;

import java.util.List;

/**
 * @author cherish
 * @date 2020/8/24
 * @description
 */
public interface GoodsService {
    public Integer getCount();
    public List<TGoods> getAll();
    public List<TGoods> getListByPage(Integer pageon);
}
