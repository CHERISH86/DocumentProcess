package com.haier.documentprocess.service;

import com.haier.documentprocess.dao.GoodsDao;
import com.haier.documentprocess.entity.TGoods;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cherish
 * @date 2020/8/24
 * @description
 */
@Service
public class GoodsServiceImpl implements GoodsService{
    //构造器注入
    final GoodsDao goodsDao;
    public GoodsServiceImpl(GoodsDao goodsDao) {
        this.goodsDao = goodsDao;
    }

    @Override
    public Integer getCount() {
        return goodsDao.selectCount(new TGoods());
    }

    @Override
    public List<TGoods> getAll() {
        return goodsDao.selectAll();
    }

    /**
     *
     * @param pageNum
     * @param pageDatasCount
     * @description 起始索引offset小于0时默认读取第一条数据
     */
    @Override
    public List<TGoods> getListByPage(Integer pageNum, Integer pageDatasCount) {
        //每次读取Excel一个sheet的数据量
        Integer beginIndex = (pageNum-1)* pageDatasCount;
        List<TGoods> result = goodsDao.selectByRowBounds(new TGoods(),new RowBounds(beginIndex, pageDatasCount));
        return result;
    }

    @Override
    public int insertList(List<TGoods> tGoodsList) {
        return goodsDao.insertList(tGoodsList);
    }
}
