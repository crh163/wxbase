package com.crh.wxbase.gsc.entity.dto.req.collection;

import com.crh.wxbase.gsc.entity.dto.req.common.BaseIdReq;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ReplaceCollectionReq extends BaseIdReq {

    @ApiModelProperty("新收藏夹id")
    @NotBlank(message = "新收藏夹id不能为空")
    private Long newCollectionId;

}
