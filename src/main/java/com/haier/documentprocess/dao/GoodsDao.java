package com.haier.documentprocess.dao;

import com.haier.documentprocess.entity.TGoods;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author cherish
 * @date 2020/8/24
 * @description
 */
@Component
public interface GoodsDao extends Mapper<TGoods> {
    int insertList(List<TGoods> tGoods);
}
