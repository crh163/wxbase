package com.crh.wxbase.gsc.entity;

import com.crh.wxbase.common.base.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author rory.chen
 * @date 2021-01-12 18:30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysConfig extends BaseModel {

    /**
     * 系统属性名
     */
    private String propName;

    /**
     * 系统属性值
     */
    private String propValue;

    /**
     * 系统属性描述
     */
    private String propDesc;

}
