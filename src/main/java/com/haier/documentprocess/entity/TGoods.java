package com.haier.documentprocess.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Getter
@Setter
public class TGoods {
  @ExcelProperty(index = 0,value = "商品id")
  private String id;

  @ExcelProperty(index = 1,value = "商品名称")
  private String name;

  @ExcelProperty(index = 2,value = "成本")
  private String cost;

  @ExcelProperty(index = 3,value = "价格")
  private String price;

  //@DateTimeFormat(pattern = "yyyy/MM/dd")
  @ExcelProperty(index = 4,value = "生产日期")
  private String manufactureDate;

  @ExcelProperty(index = 5,value = "商品编号")
  private String serialNumber;
}
