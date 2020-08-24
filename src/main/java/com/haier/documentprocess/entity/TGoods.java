package com.haier.documentprocess.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class TGoods {
  @ExcelProperty(index = 0,value = "商品id")
  private String id;

  @ExcelProperty(index = 1,value = "商品名称")
  private String name;

  @ExcelProperty(index = 2,value = "成本")
  private String cost;

  @ExcelProperty(index = 3,value = "价格")
  private String price;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getCost() {
    return cost;
  }

  public void setCost(String cost) {
    this.cost = cost;
  }


  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

}
