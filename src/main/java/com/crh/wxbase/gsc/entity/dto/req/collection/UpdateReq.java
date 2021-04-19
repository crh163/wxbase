package com.crh.wxbase.gsc.entity.dto.req.collection;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UpdateReq {

    @ApiModelProperty("收藏夹id")
    @NotBlank(message = "收藏夹id不能为空")
    private Long id;

    @ApiModelProperty("收藏夹新名称")
    @NotBlank(message = "新名称不能为空")
    private String name;

}
