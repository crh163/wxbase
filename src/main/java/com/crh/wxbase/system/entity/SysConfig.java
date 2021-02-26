package com.crh.wxbase.system.entity;

import com.crh.wxbase.common.entity.base.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

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
    @ApiModelProperty("系统属性名")
    @NotBlank(message = "系统属性名不能为空")
    private String propName;

    /**
     * 系统属性值
     */
    @ApiModelProperty("系统属性值")
    @NotBlank(message = "系统属性值不能为空")
    private String propValue;

    /**
     * 系统属性描述
     */
    @ApiModelProperty("系统属性描述")
    private String propDesc;

}
