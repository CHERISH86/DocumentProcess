package com.haier.documentprocess.service;

import com.haier.documentprocess.dao.GoodsDao;
import com.haier.documentprocess.entity.TGoods;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cherish
 * @date 2020/8/24
 * @description
 */
@Service
public class GoodsServiceImpl implements GoodsService{
    final GoodsDao goodsDao;
    //构造器注入
    public GoodsServiceImpl(GoodsDao goodsDao) {
        this.goodsDao = goodsDao;
    }

    @Override
    public Integer getCount() {
        return goodsDao.selectCount(new TGoods());
    }

    @Override
    public List<TGoods> getListByPage(Integer pageNum) {
        Integer beginIndex = (pageNum-1)*50000+1;
        List<TGoods> result = goodsDao.selectByRowBounds(new TGoods(),new RowBounds(beginIndex, 50000));
        return result;
    }
}