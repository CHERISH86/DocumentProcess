package com.haier.documentprocess.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author cherish
 * @date 2020/8/20
 * @description
 */
@Data
@Getter
@Setter
public class TGoods{
    public TGoods() {
    }

    public TGoods(int id, String name, double cost, double price, Date manufactureDate, String serialNumber) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.price = price;
        this.manufactureDate = manufactureDate;
        this.serialNumber = serialNumber;
    }

    @ExcelIgnore
    private Integer id;

    @ExcelProperty(index = 0)
    private String name;

    @ExcelProperty(index = 1)
    private Double cost;

    @ExcelProperty(index = 2)
    private Double price;

    @DateTimeFormat("yyyy-MM-dd")
    @ExcelProperty(index = 3)
    private Date manufactureDate;

    @ExcelProperty(index = 4)
    private String serialNumber;
}
