package com.crh.wxbase.gsc.entity.dto.req.collection;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DeleteReq {

    @ApiModelProperty("收藏夹id")
    @NotBlank(message = "收藏夹id不能为空")
    private Long id;

}
