package com.crh.wxbase.gen.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * @author rory.chen
 * @date 2021-01-07 14:39
 */
@Data
public class GenExcelModel extends BaseRowModel {

    /**
     * 参数名
     */
    @ExcelProperty(index = 0)
    private String columnName;

    /**
     * 类型
     */
    @ExcelProperty(index = 1)
    private String type;

    /**
     * 注释
     */
    @ExcelProperty(index = 2)
    private String annotation;

    /**
     * 备注说明
     */
    @ExcelProperty(index = 3)
    private String remark;

}
