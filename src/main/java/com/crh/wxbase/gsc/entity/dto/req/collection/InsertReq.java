package com.crh.wxbase.gsc.entity.dto.req.collection;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class InsertReq {

    @ApiModelProperty("收藏夹名称")
    @NotBlank(message = "名称不能为空")
    private String name;

}
