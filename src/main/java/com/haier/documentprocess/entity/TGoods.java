package com.haier.documentprocess.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Data
@Getter
@Setter
public class TGoods {
  public TGoods(){
  }

  public TGoods(int id, String name, double cost, double price, Date manufactureDate,String serialNumber){
    this.id = id;
    this.name = name;
    this.cost = cost;
    this.price = price;
    this.manufactureDate = manufactureDate;
    this.serialNumber = serialNumber;
  }

//  @ExcelProperty(index = 0)
  private Integer id;

//  @ExcelProperty(index = 1)
  private String name;

//  @ExcelProperty(index = 2)
  private Double cost;

//  @ExcelProperty(index = 3)
  private Double price;

//  @DateTimeFormat(pattern = "yyyy年MM月dd日")
//  @ExcelProperty(index = 4)
  private Date manufactureDate;

//  @ExcelProperty(index = 5)
  private String serialNumber;
}
