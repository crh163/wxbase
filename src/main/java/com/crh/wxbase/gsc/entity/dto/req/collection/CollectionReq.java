package com.crh.wxbase.gsc.entity.dto.req.collection;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CollectionReq {

    @ApiModelProperty("诗词标题")
    @NotBlank(message = "诗词标题不能为空")
    private String name;

    @ApiModelProperty("诗词id")
    @NotBlank(message = "诗词id不能为空")
    private Long collectId;

    @ApiModelProperty("所属收藏夹id")
    @NotBlank(message = "所属收藏夹id不能为空")
    private Long parentFolderId;

}
